var RepresentativeInfoContainer = React.createClass({
    
    getInitialState: function() {
        return {
            representative: []
        };
    },
    
    componentWillMount: function() {
        var self = this;
        var repId = this.props.params.repId;
        axios.get('/api/representative/' + repId)
        .then(function (response) {
            self.setState({ 
                representative: response.data
            });
        });
        

    },
    
    handleDelete: function() {
        var self = this; 
        var repId = this.props.params.repId;
         
          axios.delete('/api/representative/'+ repId).then(function(response) { 
              console.log('item deleted');            
              var district = self.state.representative.districtId;
              console.log(district);
              self.context.router.push( '/dis/' +  self.props.params.conId);
          });       
    },
    
    handleCancel: function() {
        var self = this; 
              self.context.router.push( '/dis/' +  self.props.params.conId);
 
        
    },
    
    render: function() {
        return <RepresentativeInfoComponent 
        representative={this.state.representative}
        onDelete={this.handleDelete}
        onCancel={this.handleCancel}
        />
    }
});

RepresentativeInfoContainer.contextTypes = {
        router: React.PropTypes.object.isRequired,
};

window.RepresentativeInfoContainer = RepresentativeInfoContainer;