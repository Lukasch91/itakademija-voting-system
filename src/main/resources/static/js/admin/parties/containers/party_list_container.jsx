var PartyListContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/REPRES/party' )
            .then( function( response ) {
                self.setState( {
                    parties: response.data
                });
            });

    },

    handleRemoveItem: function( party ) {
        var self = this;
        return function() {
            axios.put( '/api/ADMIN/party/' + party.id ).then( function( response ) {
                console.log( 'item deleted' );
                axios.get( '/api/REPRES/party' )
                    .then( function( response ) {
                        self.setState( {
                            parties: response.data
                        });
                    });
            });
        };
    },

    render: function() {
        return (
            <div>
                <PartyListComponent parties={this.state.parties} onRemoveItem={this.handleRemoveItem} />
                <AddNewContainer redirectTo={'/add-party'} />
            </div>
        )
    }
});

window.PartyListContainer = PartyListContainer;