var MultiElectionsResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            consituencies: [],
            parties: [],
            info: {}

        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/multiconslist' )
            .then( function( response ) {
                self.setState( {
                    consituencies: response.data
                });
            })
        axios.get( '/api/PUBLIC/multicons' ).then( function( response ) {
            self.setState( {
                parties: response.data
            });
        })
        axios.get( 'api/PUBLIC/multiDetails/' )
            .then( function( response ) {
                self.setState( {
                    info: response.data
                });
            });


    },

    handleAdministerDistricts: function( id ) {
        var self = this;
        return function() {
            self.context.router.push( '/multidisresult/' + id );
        }
    },

    render: function() {
        return (

            <div>
                <MultiElectionsResultsComponent
                    consituencies={this.state.consituencies}
                    onAdministerDistricts={this.handleAdministerDistricts}
                    parties={this.state.parties}
                    info={this.state.info}
                    />

            </div>
        )
    }
});

MultiElectionsResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.MultiElectionsResultsContainer = MultiElectionsResultsContainer;