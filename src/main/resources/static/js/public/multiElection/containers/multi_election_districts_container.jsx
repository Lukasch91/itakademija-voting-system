var MultiElectionsDistrictsResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: [],
            parties: [],
            constituency: {},
        };
    },

    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;
        axios.get( '/api/PUBLIC/multidistlist/' + conId )
            .then( function( response ) {
                self.setState( {
                    districts: response.data,
                });
            }).then( function() {
                axios.get( '/api/PUBLIC/multicons/' + conId )
                    .then( function( response ) {
                        self.setState( {
                            parties: response.data,
                        });
                    }).then( function() {
                        axios.get( '/api/PUBLIC/constresults/' + conId )
                            .then( function( response ) {
                                self.setState( {
                                    constituency: response.data
                                });
                            })
                    })
            })
    },

    handleCancel: function() {
        var self = this;
        self.context.router.push( '/multiresults/' );
    },

    handleAdministerDistricts: function( id ) {
        var self = this;
        return function() {
            self.context.router.push( '/onedisresult/' + id );
        }
    },

    render: function() {
        return (
            <div>
                <MultiElectionsDistrictResultsComponent
                    districts={this.state.districts}
                    parties={this.state.parties}
                    constituency={this.state.constituency}
                    onCancel={this.handleCancel}
                    onAdministerDistricts={this.handleAdministerDistricts} />
            </div>
        )
    }
});

SingleElectionsDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.MultiElectionsDistrictsResultsContainer = MultiElectionsDistrictsResultsContainer;