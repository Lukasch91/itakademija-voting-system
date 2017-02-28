var SingleElectionsDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: [],
            candidates: [],
            constituency: {},
        };
    },

    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;
        axios.get( '/api/PUBLIC/districtresults/' + conId )
            .then( function( response ) {
                self.setState( {
                    districts: response.data,
                });
            }).then( function() {
                axios.get( '/api/PUBLIC/candidatesresults/' + conId )
                    .then( function( response ) {
                        self.setState( {
                            candidates: response.data,
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
        self.context.router.push( '/results/' );
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
                <SingleElectionsDistrictResultsComponent
                    districts={this.state.districts}
                    candidates={this.state.candidates}
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

window.SingleElectionsDistrictResultsContainer = SingleElectionsDistrictResultsContainer;