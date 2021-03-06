var RegisterVotesMultiContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            multiResults: [],
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
                    axios.get( '/api/REPRES/party/' ),
                    axios.get( '/api/REPRES/reg-votes-multi' ),
                    axios.get( '/api/REPRES/invalid-votes/type/' + true )
                ] )
                    .then( axios.spread( function( partyResponse, multiElectionResponse, spoiltVotesResponse ) {

                        //Find spoiltVote in a list of spoiltVotes by district id 
                        var spoiltVote = null;
                        for ( var i = 0; i < spoiltVotesResponse.data.length; i++ ) {
                            if ( spoiltVotesResponse.data[i].district.id == self.state.currentDistrictId ) {
                                spoiltVote = spoiltVotesResponse.data[i];
                            }
                        }
                        //Find multiResults in a list of multiResults by district id
                        var multiVotesAll = [];

                        for ( var i = 0; i < multiElectionResponse.data.length; i++ ) {
                            if ( multiElectionResponse.data[i].district.id == self.state.currentDistrictId ) {
                                multiVotesAll.push( multiElectionResponse.data[i] );
                            }
                        }


                        self.setState( {
                            parties: partyResponse.data,
                            multiResults: multiVotesAll,
                            spoiltVote: spoiltVote
                        });
                    }) )
                    .then( function() {
                        //If there are no results create initial objects for entering results
                        if ( self.state.multiResults[0] == null ) {
                            var resultsTemplate = [];
                            for ( var i = 0; i < self.state.parties.length; i++ ) {
                                resultsTemplate.push( {
                                    id: null,
                                    party: { id: self.state.parties[i].id },
                                    district: { id: self.state.currentDistrictId },
                                    votes: ""
                                })

                            }
                            self.setState( { enteredResults: resultsTemplate });

                            var spoiltVoteObject = null;
                            spoiltVoteObject = ( {
                                id: null,
                                typeMulti: true,
                                district: { id: self.state.currentDistrictId },
                                votes: ""
                            });
                            self.setState( { enteredSpoiltVote: spoiltVoteObject });
                        }
                    });
            });
    },

    handleSpoiltVotesChange: function( districtId, event ) {
        var self = this;
        var spoiltVoteUpdate = self.state.enteredSpoiltVote;
        spoiltVoteUpdate.votes = event.target.value;
        self.setState( { enteredSpoiltVote: spoiltVoteUpdate, hasErrors: "" });

    },

    handleMultiVotesChange: function( passPartyId, event ) {
        var self = this;
        var updateResults = self.state.enteredResults;
        for ( var i = 0; i < updateResults.length; i++ ) {
            if ( updateResults[i].party.id == passPartyId ) {
                updateResults[i].votes = event.target.value;
            }
        }
        self.setState( { enteredResults: updateResults, hasErrors: "" });
    },

    handleExport: function() {
        var self = this;

        var multiVotesPackage = [];
        multiVotesPackage = self.state.enteredResults.slice();
        multiVotesPackage.push( self.state.enteredSpoiltVote );

        axios.post( '/api/REPRES/reg-votes-multi', multiVotesPackage )
            .then( function( response ) {

                console.log( "sent" );
                console.log( response );
                if ( response.status == 200 ) {
                    self.componentWillMount();
                } else {
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
        var show;

        if ( ( self.state.multiResults[0] == null ) && ( self.state.spoiltVote == null ) ) {
            var partiesList = this.state.parties.map( function( party, index ) {
                return (
                    <tbody key={'body' + index}>
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
                        <ValidateVotesMultiContainer key={"validation" + party.id} party={party}
                            isSpoilt={false} validation={self.state.validationArray} />
                    </tbody>
                );
            });
            show = (
                <form>
                    <div className="col-sm-6 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th className="col-sm-7">Partijos</th>
                                    <th className="col-sm-1">Partijos trumpinys</th>
                                    <th className="col-sm-4">Balsai</th>
                                </tr>
                            </thead>
                            {partiesList}

                            <tbody>
                                <tr>
                                    <td>Sugadinti balsai</td>
                                    <td></td>
                                    <td>
                                        <input key={'input-spoilt'} type="number" className="form-control" onChange={self.handleSpoiltVotesChange.bind( self, self.state.currentDistrictId )} />
                                    </td>
                                </tr>
                            </tbody>
                            <tbody>
                                <ValidateVotesMultiContainer key={'spoiltMultivote'} party={null}
                                    isSpoilt={true} validation={self.state.validationArray} />
                            </tbody>
                        </table>
                    </div>

                    <div style={{ textAlign: 'center' }}>
                        <p>{self.state.hasErrors}</p>
                        <button type="button" className="btn btn-success" onClick={this.handleExport}>Siųsti rezultatus</button>
                    </div>

                </form> );
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
            show = (
                <div>
                    <div className="col-sm-6 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th className="col-sm-9">Partijos pavadinimas</th>
                                    <th className="col-sm-1">Partijos trumpinys</th>
                                    <th className="col-sm-2">Balsai</th>
                                </tr>
                            </thead>
                            <tbody>
                                {multiElectionResultsList}
                                <tr><td>Sugadinti balsai</td>
                                    <td></td>
                                    <td>{self.state.spoiltVote.votes}</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div> );
        }

        return (
            <div>
                <div style={{ textAlign: 'center', paddingBottom: '10px' }}>
                    <h3>Balsavimo rezultatai daugiamandatėse apygardose</h3>
                </div>
                <LoggedInRepresentativeInfoContainer />
                <div>{show}</div>
            </div>
        );
    }
});

window.RegisterVotesMultiContainer = RegisterVotesMultiContainer;
