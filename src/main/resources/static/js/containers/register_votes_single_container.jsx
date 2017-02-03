var RegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            elections: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/candidate' )
            .then( function( response ) {
                self.setState( {
                    candidates: response.data
                });
            });
        axios.get( '/api/singleelection' )
            .then( function( response ) {
                self.setState( {
                    elections: response.data
                });
            });
    },

    handlePublishVotes: function() {
        axios.post( '/api/singleelectiondistrict/1' ).then( function( response ) {
            window.location.reload();
        });
        console.log( 'votes published' );
    },

    render: function() {
        return <RegisterVotesSingleComponent
            candidates={this.state.candidates}
            elections={this.state.elections}
            onPublishVotes={this.handlePublishVotes}
            />
    }
});

window.RegisterVotesSingleContainer = RegisterVotesSingleContainer;