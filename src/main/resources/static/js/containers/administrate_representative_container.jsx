var AdministrateRepresentativeContainer = React.createClass({
   
    getInitialState: function() {
        return {
            representative: {
                name: '',
                surname: '',
                loginName: '',
                password: '',
                email: ''              
            }
        }
    },
    
    handleFieldChange: function(fieldName) {
        var self = this;
        return function(e) {
          var representative = self.state.representative;
          representative[fieldName] = e.target.value;
          self.setState({ representative: representative });
        };
    },
    
    handleCancel: function() {
        this.context.router.push('/dis/' + this.props.params.conId);
    },
    
    handleAddRepresentative: function(e) {
        e.preventDefault();
        var self = this;
        axios.post('/api/representative', {
            name: this.state.representative.name,
            surname: this.state.representative.surname,
            loginName: this.state.representative.loginName,
            password: this.state.representative.password,
            email: this.state.representative.email,
            districtId: this.props.params.disId
        }).then(function () {
            console.log('representative added');
            /*self.context.router.push('/dis/' + this.props.params.conId);*/
          });
        this.context.router.push('/dis/' + this.props.params.conId);
    },
    
    render: function() {
        return <AdministrateRepresentativeComponent 
        representative={this.state.representative}
        onFieldChange={this.handleFieldChange}
        onAddRepresentative={this.handleAddRepresentative}
        onCancel={this.handleCancel}/>
    }
});

AdministrateRepresentativeContainer.contextTypes = {
        router: React.PropTypes.object.isRequired,
};

window.AdministrateRepresentativeContainer = AdministrateRepresentativeContainer;