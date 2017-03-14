var PartyListContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/consolidatedResults/' )
            .then( function( response ) {
                self.setState( {
                   parties: response.data,
                });

            })
    },

    render: function() {
        return (
            <div>
                <PartyListComponent

                    parties={this.state.parties}
                    />
            </div>
        )
    }
});

window.PartyListContainer = PartyListContainer;