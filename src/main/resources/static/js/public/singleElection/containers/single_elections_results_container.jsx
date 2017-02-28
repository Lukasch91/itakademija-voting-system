var SingleElectionsResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            consituencies: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/constresults' )
            .then( function( response ) {
                self.setState( {
                    consituencies: response.data
                });
            });

    },

    handleAdministerDistricts: function(id) {
        var self = this;
        return function() {
            self.context.router.push('/disresult/' + id );
        }
    },

    render: function() {
        return (

            <div>
                <SingleElectionsResultsComponent consituencies={this.state.consituencies}
                onAdministerDistricts={this.handleAdministerDistricts}
                    />

            </div>
        )
    }
});

SingleElectionsResultsContainer.contextTypes = {
        router: React.PropTypes.object.isRequired
    };

window.SingleElectionsResultsContainer = SingleElectionsResultsContainer;