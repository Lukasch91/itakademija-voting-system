var TestRegisterVotesMultiContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            multiResults: [],
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
                    axios.get( '/api/party/'),
                    axios.get( '/api/reg-votes-multi' ), 
                    axios.get( 'api/invalid-votes/type/' + true )
                ] )
                    .then( axios.spread( function( partyResponse, multiElectionResponse, spoiltVotesResponse ) { 

                        var spoiltVote = null;
                        for ( var i = 0; i < spoiltVotesResponse.data.length; i++ ) {
                            if ( spoiltVotesResponse.data[i].district.id == self.state.currentDistrictId ) {
                                spoiltVote = spoiltVotesResponse.data[i];
                            }
                        }
                        self.setState( {
                            parties: partyResponse.data,
                            multiResults: multiElectionResponse.data,
                            spoiltVote: spoiltVote
                        });
                    }) )
                    .then( function() {
                        if ( self.state.multiResults[0] == null ) { 
                            var resultsTemplate = [];
                            for ( var i = 0; i < self.state.parties.length; i++ ) {

                            resultsTemplate.push( { id: null, party: { id: self.state.parties[i].id }, district: { id: self.state.currentDistrictId } })
                            }
                            self.setState( { enteredResults: resultsTemplate });
                        }
                    });
            });
    },

    handleSpoiltVotesChange: function( districtId, event ) {
        var self = this;
        var spoiltVoteObject = ( { id: null, typeMulti: true, district: { id: districtId }, votes: event.target.value });
        self.setState( { enteredSpoiltVote: spoiltVoteObject });

    },

    handleMultiVotesChange: function( passPartyId, event ) {
        var self = this;
        var updateResults = self.state.enteredResults;
        for ( var i = 0; i < updateResults.length; i++ ) {
            if ( updateResults[i].party.id == passPartyId ) {
                updateResults[i].votes = event.target.value;
            }
        }
        self.setState( { enteredResults: updateResults });
    },

    handleExport: function() {
        
        /*
        function useNull() {
  return null;
}

axios.all([
      axios.request(options[ 0 ]).catch(useNull),
    , axios.request(options[ 1 ]).catch(useNull),
    , axios.request(options[ 2 ]).catch(useNull)
]).then(axios.spread(function (res1, res2, res3) {
    // res1, res2, and res3 contains the response or null if they failed
}));
        
    */    
        
        var self = this;
        axios.all( [
            axios.post( '/api/reg-votes-multi', self.state.enteredResults ),
            axios.post( '/api/invalid-votes', self.state.enteredSpoiltVote )

        ] )
            .then( axios.spread( function( multiElectionResponse, spoiltVotesResponse ) {
                console.log( "sent" );
                console.log( multiElectionResponse ); 
                console.log( spoiltVotesResponse );

                self.componentWillMount();
            }) )
            .catch( function( error ) {
                if ( error.response.status == 400 ) {
                    
                    console.log( "___Error messages:___" );
                    console.log(error.response.data);
//                    for ( var i = 0; i < ( error.response.data.errors.length ); i++ ) {
//                        console.log( ">>>" + error.response.data.errors[i] );
//                    }

//                    var validationArray = self.state.validation;
//                    self.setState( { validationArray: error.response.data.errors });
                }
                else {
                    console.log( "___Error response object___" );
                    console.log( error.response );
                }

            });
    },

    render: function() {
        var self = this;

        if ( ( self.state.multiResults[0] == null ) && ( self.state.spoiltVote == null ) ) { 


            var partiesList = this.state.parties.map( function( party, index ) {
                return (
                    <tr key={'row' + index}>
                        <td>{party.title}</td>
                        <td>{party.party_abbreviation}</td>
                        <td>
                            <input key={'input' + index}
                                type="number"
                                className="form-control"
                                onChange={self.handleMultiVotesChange.bind( self, party.id )} />
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
                                    <th>Partijos trumpinys</th>
                                    <th>Balsai</th>
                                </tr>
                            </thead>
                            <tbody>
                                {partiesList}
                                <tr>
                                    <td>Sugadinti balsai</td>
                                    <td></td>
                                    <td>
                                        <input key={'input-spoilt'} type="number" className="form-control" onChange={self.handleSpoiltVotesChange.bind( self, self.state.currentDistrictId )} />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <br />
                    <button type="button" className="btn btn-success" onClick={this.handleExport}>Siųsti rezultatus</button>
                </div>
            )
        } else {
            var multiElectionResultsList = this.state.multiResults.map( function( multi, index ) {
                return (
                    <tr key={'multi' + index}>
                        <td> {multi.party.title} </td>
                        <td> {multi.party.party_abbreviation} </td>
                        <td> {multi.votes} </td>
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
                            {multiElectionResultsList}
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
