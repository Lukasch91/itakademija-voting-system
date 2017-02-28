var AdministrateMultiCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            parties: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/ADMIN/partyExtended' )
            .then( function( response ) {
                self.setState( {
                    parties: response.data
                });
            });
    },


    render: function() {
        return (
            <div>
                <AdministrateMultiCandidatesComponent parties={this.state.parties} />
            </div>
        )
    }
});

window.AdministrateMultiCandidatesContainer = AdministrateMultiCandidatesContainer;