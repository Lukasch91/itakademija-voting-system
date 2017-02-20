var TestRegisterVotesMultiContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            singleResults: [], //multi
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
                    axios.get( '/api/candidate/' + response.data.districtId ), //party
                    axios.get( '/api/singleelection' ), //multi
                    axios.get( 'api/invalid-votes/type/' + true ) //true
                ] )
                    .then( axios.spread( function( candidateResponse, singleElectionResponse, spoiltVotesResponse ) { //multi party

                        var spoiltVote = null;
                        for ( var i = 0; i < spoiltVotesResponse.data.length; i++ ) {
                            if ( spoiltVotesResponse.data[i].district.id == self.state.currentDistrictId ) {
                                spoiltVote = spoiltVotesResponse.data[i];
                            }
                        }
                        self.setState( {
                            candidates: candidateResponse.data, //party
                            singleResults: singleElectionResponse.data, //multi
                            spoiltVote: spoiltVote
                        });
                    }) )
                    .then( function() {
                        if ( self.state.singleResults[0] == null ) { //multi
                            var resultsTemplate = [];
                            for ( var i = 0; i < self.state.candidates.length; i++ ) { //party

                            /*multi*/    resultsTemplate.push( { singleId: null, singleCandidate: { candidateID: self.state.candidates[i].candidateID }, singleDistrict: { id: self.state.currentDistrictId } })
                            }
                            self.setState( { enteredResults: resultsTemplate });
                        }
                    });
            });
    },

    handleSpoiltVotesChange: function( districtId, event ) {
        var self = this;
        var spoiltVoteObject = ( { id: null, typeMulti: true, district: { id: districtId }, votes: event.target.value }); //true
        self.setState( { enteredSpoiltVote: spoiltVoteObject });

    },

    handleSingleVotesChange: function( passCandId, event ) { //multi
        var self = this;
        var updateResults = self.state.enteredResults;
        for ( var i = 0; i < updateResults.length; i++ ) {
            if ( updateResults[i].singleCandidate.candidateID == passCandId ) { //party
                updateResults[i].singleVotes = event.target.value; //multi
            }
        }
        self.setState( { enteredResults: updateResults });
    },

    handleExport: function() {
        var self = this;

        axios.all( [
            axios.post( '/api/singleelection', self.state.enteredResults ), //multi
            axios.post( '/api/invalid-votes', self.state.enteredSpoiltVote )

        ] )
            .then( axios.spread( function( singleElectionResponse, spoiltVotesResponse ) { //multi
                console.log( "sent" );
                console.log( singleElectionResponse ); //multi
                console.log( spoiltVotesResponse );

                self.componentWillMount();
            }) );
    },

    render: function() {
        var self = this;

        if ( ( self.state.singleResults[0] == null ) && ( self.state.spoiltVote == null ) ) { //multi


            var candidatesList = this.state.candidates.map( function( candidate, index ) { //party
                return (

                    <tr key={'row' + index}>
                        <td>{candidate.candidateName} {candidate.candidateSurname}</td> //party
                        <td>
                            <input key={'input' + index}
                                type="number"
                                className="form-control"
                                onChange={self.handleSingleVotesChange.bind( this, candidate.candidateID )} /> //party
                        </td>
                    </tr>
                );
            });

            return (
                <div>
                    <h3>Daugiamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    <div>
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th>Partijos</th>
                                    <th>Balsai</th>
                                </tr>
                            </thead>
                            <tbody>
                                {candidatesList} //party
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
            var singleElectionResultsList = this.state.singleResults.map( function( single, index ) { //multi
                return (
                    <tr key={'multi' + index}> //multi
                        <td> {single.singleCandidate.candidateName} </td> //multi
                        <td> {single.singleCandidate.candidateSurname} </td> //multi
                        <td> {single.singleVotes} </td> //multi
                    </tr>
                );
            });

            return (
                <form>
                    <h3>Daugiamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Partijos pavadinimas</th>
                                <th>Partijos trumpinys</th>
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

window.TestRegisterVotesMultiContainer = TestRegisterVotesMultiContainer;