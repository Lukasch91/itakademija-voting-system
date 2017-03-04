var ChangePasswordContainer = React.createClass({
    
    getInitialState: function() {
        return {
            admin: {newPass: ''}
            
        }
    },
    
    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var admin = self.state.admin;
            admin[fieldName] = e.target.value;
            self.setState( { admin: admin });
        };
    },
    
    handleChangePass: function() {
        axios.post( 'http://localhost:8080/api/ADMIN/changepass?password=' +  this.state.admin.newPass);
    },
    
    
    render: function() {
        return (
    
        <ChangePasswordComponent 
                admin={this.state.admin}
                onChangePassClick={this.handleChangePass}
                onFieldChange={this.handleFieldChange}
                />
                )
    }
});

window.ChangePasswordContainer = ChangePasswordContainer;