var AdministrateMultiCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            parties: []
        };
    },

    loadData: function() {
        console.log( "reload3" );
        var self = this;
        axios.get( '/api/ADMIN/partyExtended' )
            .then( function( response ) {
                self.setState( {
                    parties: response.data
                });
            });
    },

    componentWillMount: function() {
        this.loadData();
    },


    render: function() {
        return (
            <div>
                <AdministrateMultiCandidatesComponent
                    reload3={this.loadData}
                    parties={this.state.parties} />
            </div>
        )
    }
});

window.AdministrateMultiCandidatesContainer = AdministrateMultiCandidatesContainer;