var SingleElectionsDistrictResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: []
        };
    },
    
    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;
        axios.get('/api/districtresults/' + conId)
        .then(function (response) {
            self.setState({ 
                districts: response.data,
            });
        });
    },
   
    
    render: function() {
        return (
            <div>
                  <SingleElectionsDistrictResultsComponent 
                districts={this.state.districts} />
            </div>
        )
    }
});

SingleElectionsDistrictResultsContainer.contextTypes = {
        router: React.PropTypes.object.isRequired
};

window.SingleElectionsDistrictResultsContainer = SingleElectionsDistrictResultsContainer;