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
        axios.get( '/api/districtresults/' + conId )
            .then( function( response ) {
                self.setState( {
                    districts: response.data,
                });
            }).then( function() {
                axios.get( '/api/candidatesresults/' + conId )
                    .then( function( response ) {
                        self.setState( {
                            candidates: response.data,
                        });
                    }).then( function() {
                        axios.get( '/api/constresults/' + conId )
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

    render: function() {
        return (
            <div>
                <SingleElectionsDistrictResultsComponent
                    districts={this.state.districts}
                    candidates={this.state.candidates}
                    constituency={this.state.constituency}
                    onCancel={this.handleCancel} />
            </div>
        )
    }
});

SingleElectionsDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.SingleElectionsDistrictResultsContainer = SingleElectionsDistrictResultsContainer;