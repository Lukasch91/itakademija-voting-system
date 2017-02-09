var SingleElectionsResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            consituencies: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/constresults' )
            .then( function( response ) {
                self.setState( {
                    consituencies: response.data
                });
            });

    },



    render: function() {
        return (
            <div>
                <SingleElectionsResultsComponent consituencies={this.state.consituencies} />>
            </div>
        )
    }
});

window.SingleElectionsResultsContainer = SingleElectionsResultsContainer;