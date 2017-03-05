var SingleElectionsOneDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            info: {}
        };
    },

    componentWillMount: function() {
        var self = this;
        var disId = this.props.params.disId;
        axios.get( '/api/PUBLIC/districtdetails/' + disId )
            .then( function( response ) {
                self.setState( {
                    candidates: response.data,
                })

            })
        axios.get( '/api/PUBLIC/singleDistrictDetails/' + disId )
            .then( function( response ) {
                self.setState( {
                    info: response.data,
                })
            })

    },

    handleCancel: function() {
        var self = this;
        self.context.router.push( '/disresult/' + this.state.info.constituencyId );
    },


    render: function() {
        return (
            <div>
                <SingleElectionsOneDistrictResultsComponent
                    candidates={this.state.candidates}
                    onCancel={this.handleCancel}
                    info={this.state.info} />
            </div>
        )
    }
});

SingleElectionsOneDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.SingleElectionsOneDistrictResultsContainer = SingleElectionsOneDistrictResultsContainer;