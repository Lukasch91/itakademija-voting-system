var VoteFormMultiContainer = React.createClass( {

    getInitialState: function() {
        return {
            election: {
                votes: ''
            },
            validationArray: []
        };
    },


    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var election = self.state.election;
            election[fieldName] = e.target.value;
            self.setState( { election: election });
        };
    },

    handleVoteClick: function( e ) {
        e.preventDefault();
        var self = this;
        var success = 0;

        axios.post( '/api/reg-votes-multi', {
            votes: this.state.election.votes,
            party: { id: this.props.partyId },
            district: { id: '1' },
            entered_date: Date.now()
        })
            .then( function( response ) {
                success = 1;
                console.log( "-----------------" + success );
                console.log( "sagg_test_02:" );
                console.log( response );
                console.log( "party added" );
            })
            .catch( function( error ) {
                if ( error.response.status == 400 ) {
                    console.log( "sagg_test_03: error response message" );
                    console.log( error.response.data.message );
                    console.log( "sagg_test_04: error response object" );
                    console.log( error.response );
                    console.log( "sagg_test_05: error response status:" );
                    console.log( "ErrorStatus: " + error.response.status );

                    console.log( "sagg_test_06: error message:" );
                    for ( var i = 0; i < ( error.response.data.errors.length ); i++ ) {
                        console.log( ">>>" + error.response.data.errors[i].defaultMessage );
                    }
                    var validationArray = self.state.validation;
                    self.setState( { validationArray: error.response.data.errors });
                }
                else {
                    console.log( "___Error response object___" );
                    console.log( error.response );
                }
            }).then( function() {
                if ( success == 1 ) {
                    self.context.router.push( '/reg-votes-multi' );
                }
            });
        window.location.reload();
    },

    render: function() {
        return <VoteFormMultiComponent

            election={this.state.election}
            validationArray={this.state.validationArray}
            onFieldChange={this.handleFieldChange}
            onVoteClick={this.handleVoteClick}
            />
    }
});

VoteFormMultiContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};


window.VoteFormMultiContainer = VoteFormMultiContainer;