var RegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            singleResults: [],
            spoiltVote: {},

            enteredResults: [],
            enteredSpoiltVote: {},

            currentDistrictId: 0,
            validationArray: [],
            currentUser: {},
            hasErrors: ""
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/currentuser' )
            .then( function( response ) {
                self.setState( { currentDistrictId: response.data.districtId, currentUser: response.data });
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
                                    singleDistrict: { id: self.state.currentDistrictId },
                                    singleVotes: ""
                                })

                            }
                            self.setState( { enteredResults: resultsTemplate });

                            var spoiltVoteObject = null;
                            spoiltVoteObject = ( {
                                singleId: null,
                                typeMulti: false,
                                singleDistrict: { id: self.state.currentDistrictId },
                                singleVotes: ""
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
        self.setState( { enteredSpoiltVote: spoiltVoteUpdate, hasErrors: "" });
    },

    handleSingleVotesChange: function( passCandId, event ) {
        var self = this;
        var updateResults = self.state.enteredResults;
        for ( var i = 0; i < updateResults.length; i++ ) {
            if ( updateResults[i].singleCandidate.candidateID == passCandId ) {
                updateResults[i].singleVotes = event.target.value;
            }
        }
        self.setState( { enteredResults: updateResults, hasErrors: "" });
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
                }
            })
            .catch( function( error ) {
                if ( error.response.status == 400 ) {
                    self.setState( { validationArray: error.response.data, hasErrors: "Balsai turi klaidų, peržiūrėkite sąraša!" });
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

                    <tbody key={'body' + index}>
                        <tr key={'row' + index}>
                            <td>{candidate.candidateName} {candidate.candidateSurname}</td>
                            <td>
                                <input key={'input' + index}
                                    type="number"
                                    className="form-control"
                                    onChange={self.handleSingleVotesChange.bind( self, candidate.candidateID )} />
                            </td>
                        </tr>
                        <ValidateVotesSingleContainer key={"validation" + candidate.candidateID} candidate={candidate}
                            isSpoilt={false} validation={self.state.validationArray} />
                    </tbody>
                );
            });

            return (
                <form>
                    <div style={{ textAlign: 'center', paddingBottom: '10px' }}>
                        <h3>Balsavimo rezultatų įvedimas vienmandatėse apygardose</h3>
                    </div>
                    <LoggedInRepresentativeInfoContainer currentUser={self.state.currentUser}/>
                    <div className="col-sm-6 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th className="col-sm-8">Kandidatai</th>
                                    <th className="col-sm-4">Balsai</th>
                                </tr>
                            </thead>
                            {candidatesList}
                            <tbody>
                                <tr>
                                    <td>Sugadinti balsai</td>
                                    <td>
                                        <input key={'input-spoilt'} type="number" className="form-control"
                                            onChange={self.handleSpoiltVotesChange.bind( self, self.state.currentDistrictId )} />
                                    </td>
                                </tr>
                            </tbody>
                            <tbody>
                                <ValidateVotesSingleContainer key={'spoiltSinglevote'} candidate={null}
                                    isSpoilt={true} validation={self.state.validationArray} />
                            </tbody>
                        </table>
                    </div>
                    <div style={{ textAlign: 'center' }}>
                        <p>{self.state.hasErrors}</p>
                        <button type="button" className="btn btn-success" onClick={this.handleExport}>Siųsti rezultatus</button>
                    </div>

                </form>
            )
        } else {
            /*
             * 
             * nepagauna situ funkciju, bet atvaizduoja wtf?
             * 
             * 
             */
            console.log("1");console.log(self.state.currentUser.name);
             /* 
             * 
             * 
             * 
             * 
             */
            var singleElectionResultsList = this.state.singleResults.map( function( single, index ) {
             
                return (
                    <tr key={'single' + index}>
                        <td> {single.singleCandidate.candidateName} {single.singleCandidate.candidateSurname}</td>
                        <td> {single.singleVotes} </td>
                    </tr>
                );
            });
            
            return (
                <div>
                    <div style={{ textAlign: 'center', paddingBottom: '10px' }}>
                        <h3>Balsavimo rezultatai vienmandatėse apygardose</h3>
                    </div>
                    <LoggedInRepresentativeInfoContainer currentUser={self.state.currentUser}/>
                    <div className="col-sm-6 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th>Kandidatas</th>
                                    <th>Balsai</th>
                                </tr>
                            </thead>
                            <tbody>
                                {singleElectionResultsList}
                                <tr><td>Sugadinti balsai</td>
                                    <td>{self.state.spoiltVote.votes}</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            )
        }
    }
});

window.RegisterVotesSingleContainer = RegisterVotesSingleContainer;
