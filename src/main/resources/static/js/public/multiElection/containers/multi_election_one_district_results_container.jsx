var MultiElectionsOneDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
        };
    },

    componentWillMount: function() {
        var self = this;
        var disId = this.props.params.disId;
        axios.get( '/api/PUBLIC/multidis/' + disId )
            .then( function( response ) {
                self.setState( {
                    parties: response.data,
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
                <MultiElectionsOneDistrictResultsComponent
                    parties={this.state.parties}
                    onCancel={this.handleCancel} />
            </div>
        )
    }
});

MultiElectionsOneDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.MultiElectionsOneDistrictResultsContainer = MultiElectionsOneDistrictResultsContainer;