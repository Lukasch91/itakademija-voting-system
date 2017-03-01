var SingleElectionsResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            consituencies: [],
            info: {}
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/constresults' )
            .then( function( response ) {
                self.setState( {
                    consituencies: response.data
                });
            })
        axios.get( 'api/PUBLIC/singledetails/' )
            .then( function( response ) {
                self.setState( {
                    info: response.data
                });
            });

    },

    handleAdministerDistricts: function( id ) {
        var self = this;
        return function() {
            self.context.router.push( '/disresult/' + id );
        }
    },

    render: function() {
        return (

            <div>
                <SingleElectionsResultsComponent
                    consituencies={this.state.consituencies}
                    info={this.state.info}
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