var RegisterVotesMultiEasyContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            election: {
                votes: ''
            }
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/party' )
            .then( function( response ) {
                self.setState( {
                    parties: response.data
                });
            });
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

        /*e.preventDefault();*/
            axios.post( '/api/reg-votes-multi', {
                votes: this.state.election.votes,
                party: {id: e.id},
                district: { id: '18' },
                enetered_date: Date.now()
            })
                .then( function() {
                    console.log( 'vote added' );
                });


    },

    render: function() {
        return <RegisterVotesMultiEasyComponent
            parties={this.state.parties}
            election={this.state.election}
            onFieldChange={this.handleFieldChange}
            onVoteClick={this.handleVoteClick}
            />
    }
});

RegisterVotesMultiEasyContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.RegisterVotesMultiEasyContainer = RegisterVotesMultiEasyContainer;