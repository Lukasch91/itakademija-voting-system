var DistrictListContainer = React.createClass({

    getInitialState: function() {
        return {
            districts: [] ,
            constit: []
        };
    },
    
    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;
        axios.get('/api/constituency/' + conId)
        .then(function (response) {
            self.setState({ 
                districts: response.data.districts,
                constit: response.data
            });
        });

    },
    
    handleAdministerRepresentative: function(district) {
        var self = this;
        return function() {
                self.context.router.push('/repres/' + district.constituencyId + '/' + district.representatives[0].id);
        }
    },
    
    handleAddRepresentative: function(district) {
        var self = this;
        return function() {
                self.context.router.push('/add-rep/' + district.constituencyId + '/' + district.id);
        }
        
    },
    
    handleRemoveItem: function(district) { 
        var self = this; 
        return function() { 
          axios.put('/api/district/'+ district.id).then(function(response) { 
              console.log('item deleted'); 
              var conId = self.props.params.conId;
              axios.get('/api/constituency/' + conId) 
              .then(function (response) { 
                  self.setState({  
                      districts: response.data.districts,
                      constit: response.data  
                  }); 
              }); 
          }); 
        }; 
      },
      
      handleGoBack: function() {
          console.log('click');
          this.context.router.push('/con');
      },
      
    render: function() {
        return (
        <div>
        <DistrictListComponent 
            districts={this.state.districts} 
            constit={this.state.constit}
            representative={this.state.representative}   
            onAdministerRepresentative={this.handleAdministerRepresentative} 
            onAddRepresentative={this.handleAddRepresentative}
            onRemoveItem={this.handleRemoveItem}
            />
        <AddNewContainer redirectTo={'/add-dis/' + this.state.constit.id}/>
        <button type="button" className="btn btn-default" onClick={this.handleGoBack}>Back</button>
        </div>
        )
  }
});

DistrictListContainer.contextTypes = {
        router: React.PropTypes.object.isRequired
};


window.DistrictListContainer = DistrictListContainer;