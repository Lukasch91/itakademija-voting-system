var TestRegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            singleResults: [],
            spoiltVote: {},
            enteredResults: [],
            enteredSpoiltVote: {},
            currentDistrictId: 0
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
                    axios.get( '/api/candidate/' + response.data.districtId ),
                    axios.get( '/api/singleelection' ),
                    axios.get( 'api/invalid-votes/type/' + false )
                ] )
                    .then( axios.spread( function( candidateResponse, singleElectionResponse, spoiltVotesResponse ) {

                        var spoiltVote = null;
                        for ( var i = 0; i < spoiltVotesResponse.data.length; i++ ) {
                            if ( spoiltVotesResponse.data[i].district.id == self.state.currentDistrictId ) {
                                spoiltVote = spoiltVotesResponse.data[i];
                            }
                        }
                        self.setState( {
                            candidates: candidateResponse.data,
                            singleResults: singleElectionResponse.data,
                            spoiltVote: spoiltVote
                        });
                    }) )
                    .then( function() {
                        if ( self.state.singleResults[0] == null ) {
                            var resultsTemplate = [];
                            for ( var i = 0; i < self.state.candidates.length; i++ ) {

                                resultsTemplate.push( { singleId: null, singleCandidate: { candidateID: self.state.candidates[i].candidateID }, singleDistrict: { id: self.state.currentDistrictId } })
                            }
                            self.setState( { enteredResults: resultsTemplate,  reload: false });
                        }
                    });
            });
    },

    handleSpoiltVotesChange: function( districtId, event ) {
        var self = this;
        var spoiltVoteObject = ( { id: null, typeMulti: false, district: { id: districtId }, votes: event.target.value });
        self.setState( { enteredSpoiltVote: spoiltVoteObject });

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

        axios.all( [
            axios.post( '/api/singleelection', self.state.enteredResults ),
            axios.post( '/api/invalid-votes', self.state.enteredSpoiltVote )

        ] )
            .then( axios.spread( function( singleElectionResponse, spoiltVotesResponse ) {
                   console.log("sent");
                   console.log(singleElectionResponse);
                   console.log(spoiltVotesResponse);
                   
                   self.componentWillMount();
            }) );
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
                                onChange={self.handleSingleVotesChange.bind( this, candidate.candidateID )} />
                        </td>
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
                                        <input key={'input-spoilt'} type="number" className="form-control" onChange={self.handleSpoiltVotesChange.bind( this, self.state.currentDistrictId )} />
                                    </td></tr>
                            </tbody>
                        </table>
                    </div>
                    <br />
                    <button type="button" className="btn btn-success" onClick={this.handleExport}>Siųsti rezultatus</button>
                </div>
            )
        } else {
            var singleElectionResultsList = this.state.singleResults.map( function( single, index ) {
                return (
                    <tr key={'single' + index}>
                        <td>{single.singleCandidate.candidateName}</td>
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

window.TestRegisterVotesSingleContainer = TestRegisterVotesSingleContainer;
