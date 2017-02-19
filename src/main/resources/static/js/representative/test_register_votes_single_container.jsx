var TestRegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            singleResults: [],
            enteredResults: [],
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
                    axios.get( '/api/singleelection' )
                ] )
                    .then( axios.spread( function( candidateResponse, singleElectionResponse ) {
                        self.setState( {
                            candidates: candidateResponse.data,
                            singleResults: singleElectionResponse.data
                        });
                    }) )
                    .then( function() {
                        if ( self.state.singleResults[0] == null ) {
                            var resultsTemplate = [];
                            for ( var i = 0; i < self.state.candidates.length; i++ ) {

                                resultsTemplate.push( { singleId: null, singleCandidate: { candidateID: self.state.candidates[i].candidateID }, singleDistrict: { id: self.state.currentDistrictId } })
                            }
                            self.setState( { enteredResults: resultsTemplate });
                        }
                    });
            });
    },

    handleSpoiltVotesChange: function( districtId, event ) {

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        console.log( "I'm spoilt :@" );
        console.log( event.target.value );
        console.log( districtId );

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
        
        axios.post( '/api/singleelection', self.state.enteredResults )
        .then( function( response ) {
            console.log( response );
        });
        

    },

    render: function() {
        var self = this;

        if ( self.state.singleResults[0] == null ) {


            var candidatesList = this.state.candidates.map( function( candidate, index ) {
                return (

                    <tr key={'row' + index}>
                        <td>{candidate.candidateName} {candidate.candidateSurname}</td>
                        <td>
                            <input key={'input' + index}
                                type="text"
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
                                        <input key={'input-spoilt'} type="text" className="form-control" onChange={self.handleSpoiltVotesChange.bind( this, self.state.currentDistrictId )} />
                                    </td></tr>
                            </tbody>
                        </table>
                    </div>
                    <br />
                    <button type="button" className="btn btn-success" onClick={this.handleExport}>JSON</button>
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
                            <tr><td>Sugadinti balsai</td></tr>
                        </tbody>
                    </table>
                </form>
            )
        }
    }
});

window.TestRegisterVotesSingleContainer = TestRegisterVotesSingleContainer;
