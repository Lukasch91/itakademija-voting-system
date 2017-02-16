var AdministrateSingleCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            constituencies: []
        };
    },

    componentWillMount: function() {

        var self = this;

        axios.get( '/api/constituencyExtended' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            });
    },

    render: function() {
        return (
            <div>
                <AdministrateSingleCandidatesComponent
                    constituencies={this.state.constituencies}
                    />
            </div>
        )
    }
});

window.AdministrateSingleCandidatesContainer = AdministrateSingleCandidatesContainer;

