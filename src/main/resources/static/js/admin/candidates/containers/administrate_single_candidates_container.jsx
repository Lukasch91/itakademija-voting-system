var AdministrateSingleCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            constituencies: []
        };
    },

    loadData: function() {
        console.log( "reload3" );
        var self = this;
        axios.get( '/api/ADMIN/constituencyExtended' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            });
    },

    componentWillMount: function() {
        this.loadData();
    },

    render: function() {
        return (
            <div>
                <AdministrateSingleCandidatesComponent
                    reload3={this.loadData}
                    constituencies={this.state.constituencies}
                    />
            </div>
        )
    }
});

window.AdministrateSingleCandidatesContainer = AdministrateSingleCandidatesContainer;

