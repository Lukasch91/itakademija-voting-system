var VoteFormMultiContainer = React.createClass( {

    getInitialState: function() {
        return {
            election: {
                votes: ''
            }
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
        axios.post( '/api/reg-votes-multi', [{
            votes: this.state.election.votes,
            party: { id: this.props.partyId },
            district: { id: '1' },
            enetered_date: Date.now()
        }])
            .then( function() {
                console.log( 'vote added' );
            });
        window.location.reload();


    },

    render: function() {
        return <VoteFormMultiComponent

            election={this.state.election}
            onFieldChange={this.handleFieldChange}
            onVoteClick={this.handleVoteClick}
            />
    }
});


window.VoteFormMultiContainer = VoteFormMultiContainer;