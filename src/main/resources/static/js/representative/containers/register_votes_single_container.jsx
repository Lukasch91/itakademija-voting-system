var RegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            singleResults: [],
            spoiltVote: {},

            enteredResults: [],
            enteredSpoiltVote: {},

            currentDistrictId: 0,
            validationArray: []
        };
    },

    componentWillMount: function() {
        var self = this;
        //bad design??? maybe pass a prop by rendering this element 
        //from <LoggedInRepresentativeInfoContainer /> as it knows the districtId
        //also maybe possible to split into 2 lifecycle methods
        axios.get( '/currentuser' )
            .then( function( response ) {
                self.setState( { currentDistrictId: response.data.districtId });
                axios.all( [
                    axios.get( '/api/REPRES/candidate/' + response.data.districtId ),
                    axios.get( '/api/REPRES/singleelection' ),
                    axios.get( '/api/REPRES/invalid-votes/type/' + false )
                ] )
                    .then( axios.spread( function( candidateResponse, singleElectionResponse, spoiltVotesResponse ) {

                        //Find spoiltVote in a list of spoiltVotes by district id
                        var spoiltVote = null;
                        for ( var i = 0; i < spoiltVotesResponse.data.length; i++ ) {
                            if ( spoiltVotesResponse.data[i].district.id == self.state.currentDistrictId ) {
                                spoiltVote = spoiltVotesResponse.data[i];
                            }
                        }
                        //Find singleResults in a list of singleResults by district id
                        var singleVotesAll = [];

                        for ( var i = 0; i < singleElectionResponse.data.length; i++ ) {
                            if ( singleElectionResponse.data[i].singleDistrict.id == self.state.currentDistrictId ) {
                                singleVotesAll.push( singleElectionResponse.data[i] );
                            }
                        }


                        self.setState( {
                            candidates: candidateResponse.data,
                            singleResults: singleVotesAll,
                            spoiltVote: spoiltVote
                        });
                    }) )
                    .then( function() {
                        //If there are no results create initial objects for entering results
                        if ( self.state.singleResults[0] == null ) {
                            var resultsTemplate = [];
                            for ( var i = 0; i < self.state.candidates.length; i++ ) {
                                resultsTemplate.push( {
                                    singleId: null,
                                    singleCandidate: { candidateID: self.state.candidates[i].candidateID },
                                    singleDistrict: { id: self.state.currentDistrictId }
                                })

                            }
                            self.setState( { enteredResults: resultsTemplate });

                            var spoiltVoteObject = null;
                            spoiltVoteObject = ( {
                                singleId: null,
                                typeMulti: false,
                                singleDistrict: { id: self.state.currentDistrictId }
                            });
                            self.setState( { enteredSpoiltVote: spoiltVoteObject });
                        }
                    });
            });
    },

    handleSpoiltVotesChange: function( districtId, event ) {
        var self = this;
        var spoiltVoteUpdate = self.state.enteredSpoiltVote;
        spoiltVoteUpdate.singleVotes = event.target.value;
        self.setState( { enteredSpoiltVote: spoiltVoteUpdate });

    },

    handleSingleVotesChange: function( passCandId, event ) {
        var self = this;
        var updateResults = self.state.enteredResults;
        for ( var i = 0; i < updateResults.length; i++ ) {
            if ( updateResults[i].singleCandidate.candidateID == passCandId ) {
                updateResults[i].singleVotes = event.target.value;
            }
        }
        self.setState( { enteredResults: updateResults });
    },

    handleExport: function() {
        var self = this;

        var singleVotesPackage = [];
        singleVotesPackage = self.state.enteredResults.slice();
        singleVotesPackage.push( self.state.enteredSpoiltVote );

        axios.post( '/api/REPRES/singleelection', singleVotesPackage )
            .then( function( response ) {

                console.log( "sent" );
                console.log( response );
                if ( response.status == 201 ) {
                    self.componentWillMount();
                } else {
                }

            })
            .catch( function( error ) {
                if ( error.response.status == 400 ) {
                    self.setState( { validationArray: error.response.data });
                    console.log( "___Error messages:___" );
                    console.log( error.response.data );
                }
                else {
                    console.log( "___FATALITY___" );
                    console.log( error.response );
                }
            });
    },

    render: function() {
        var self = this;

        if ( ( self.state.singleResults[0] == null ) && ( self.state.spoiltVote == null ) ) {


            var candidatesList = this.state.candidates.map( function( candidate, index ) {
                return (

                    <tr key={'row' + index}>
                        <td>{candidate.candidateName} {candidate.candidateSurname}</td>
                        <td>
                            <input key={'input' + index}
                                type="number"
                                className="form-control"
                                onChange={self.handleSingleVotesChange.bind( self, candidate.candidateID )} />
                        </td>
                        <ValidateVotesSingleContainer key={"validation" + candidate.candidateID} candidate={candidate} isSpoilt={false} validation={self.state.validationArray} />
                    </tr>
                );
            });

            return (
                <div>
                    <h3>Vienamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    <div>
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th>Kandidatai</th>
                                    <th>Balsai</th>
                                </tr>
                            </thead>
                            <tbody>
                                {candidatesList}
                                <tr><td>Sugadinti balsai</td>
                                    <td>
                                        <input key={'input-spoilt'} type="number" className="form-control" onChange={self.handleSpoiltVotesChange.bind( self, self.state.currentDistrictId )} />
                                    </td>
                                    <ValidateVotesSingleContainer key={'spoiltSinglevote'} candidate={null} isSpoilt={true} validation={self.state.validationArray} />
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <br />
                    <button type="button" className="btn btn-xs btn-success" onClick={this.handleExport}>Siųsti rezultatus</button>
                </div>
            )
        } else {
            var singleElectionResultsList = this.state.singleResults.map( function( single, index ) {
                return (
                    <tr key={'single' + index}>
                        <td> {single.singleCandidate.candidateName} </td>
                        <td> {single.singleCandidate.candidateSurname} </td>
                        <td> {single.singleVotes} </td>
                    </tr>
                );
            });

            return (
                <form>
                    <h3>Vienamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Kandidato Vardas</th>
                                <th>Kandidato Pavardė</th>
                                <th>Balsai</th>
                            </tr>
                        </thead>
                        <tbody>
                            {singleElectionResultsList}
                            <tr><td>Sugadinti balsai</td>
                                <td></td>
                                <td>{self.state.spoiltVote.votes}</td></tr>
                        </tbody>
                    </table>
                </form>
            )
        }
    }
});

window.RegisterVotesSingleContainer = RegisterVotesSingleContainer;
