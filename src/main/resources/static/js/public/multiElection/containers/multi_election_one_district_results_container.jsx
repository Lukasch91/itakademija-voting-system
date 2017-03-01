var MultiElectionsOneDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            constituency: {}
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
            axios.get( '/api/PUBLIC/constresultsdis/' + disId )
            .then( function( response ) {
                self.setState( {
                    constituency: response.data,
                })
            })
    },

    handleCancel: function() {
        var self = this;
        self.context.router.push( '//multidisresult//' );
    },


    render: function() {
        return (
            <div>
                <MultiElectionsOneDistrictResultsComponent
                
                    parties={this.state.parties}
                constituency={this.state.constituency}
                    onCancel={this.handleCancel} />
            </div>
        )
    }
});

MultiElectionsOneDistrictResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.MultiElectionsOneDistrictResultsContainer = MultiElectionsOneDistrictResultsContainer;