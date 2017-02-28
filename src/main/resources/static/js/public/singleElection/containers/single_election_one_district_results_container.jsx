var SingleElectionsOneDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
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

    },

    handleCancel: function() {
        var self = this;
        self.context.router.push( '/results/' );
    },


    render: function() {
        return (
            <div>
                <SingleElectionsOneDistrictResultsComponent
                    candidates={this.state.candidates}
                    onCancel={this.handleCancel} />
            </div>
        )
    }
});

SingleElectionsOneDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.SingleElectionsOneDistrictResultsContainer = SingleElectionsOneDistrictResultsContainer;