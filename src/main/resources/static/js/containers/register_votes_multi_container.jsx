var RegisterVotesMultiContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            elections: []
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
        axios.get( '/api/reg-votes-multi' )
            .then( function( response ) {
                self.setState( {
                    elections: response.data
                });
            });
    },

    handlePublishVotes: function() {
        axios.post( '/api/multielectiondistrict/1' ).then( function( response ) {
            window.location.reload();
        });
        console.log( 'votes published' );
    },

    render: function() {
        return <RegisterVotesMultiComponent
            parties={this.state.parties}
            elections={this.state.elections}
            onPublishVotes={this.handlePublishVotes}
            />
    }
});

window.RegisterVotesMultiContainer = RegisterVotesMultiContainer;