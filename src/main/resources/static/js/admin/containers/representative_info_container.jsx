var RepresentativeInfoContainer = React.createClass({
    
    getInitialState: function() {
        return {
            representative: [],
            constituency: 'test'
        };
    },
    
    componentWillMount: function() {
        var self = this;
        var repId = this.props.params.repId;
        var conId = this.props.params.conId;
        
        axios.all([axios.get('/api/ADMIN/representative/' + repId), axios.get('/api/ADMIN/constituency/' + conId)])
        .then(axios.spread(function (repResponse, constResponse) {
            self.setState({ 
                representative: repResponse.data,
                constituency: constResponse.data
            });
        }));

        

    },
    
    handleDelete: function() {
        var self = this; 
        var repId = this.props.params.repId;
         
          axios.delete('/api/ADMIN/representative/'+ repId).then(function(response) { 
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
        constituency={this.state.constituency}
        onDelete={this.handleDelete}
        onCancel={this.handleCancel}
        />
    }
});

RepresentativeInfoContainer.contextTypes = {
        router: React.PropTypes.object.isRequired,
};

window.RepresentativeInfoContainer = RepresentativeInfoContainer;