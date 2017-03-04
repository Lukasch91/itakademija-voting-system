var MultiElectionsOneDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            info: {}
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
        axios.get( '/api/PUBLIC/multiDistrictDetails/' + disId )
            .then( function( response ) {
                self.setState( {
                    info: response.data,
                })
            })
    },

    handleCancel: function() {
        var self = this;
        self.context.router.push( '/multidisresult/' + this.state.info.constituencyId );
    },


    render: function() {
        return (
            <div>
                <MultiElectionsOneDistrictResultsComponent

                    parties={this.state.parties}
                    info={this.state.info}
                    onCancel={this.handleCancel} />
            </div>
        )
    }
});

MultiElectionsOneDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.MultiElectionsOneDistrictResultsContainer = MultiElectionsOneDistrictResultsContainer;