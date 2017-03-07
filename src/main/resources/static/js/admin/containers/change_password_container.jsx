var ChangePasswordContainer = React.createClass({
    
    getInitialState: function() {
        return {
            admin: {
                newPass: '',
                newPassCheck: '',
                passwordError: ''
            }          
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
        var self = this;
        if (this.state.admin.newPass == this.state.admin.newPassCheck) {
        axios.post( 'http://localhost:8080/api/ADMIN/changepass?password=' +  this.state.admin.newPass).then(function() {
            alert('Slaptažodis sėkmingai pakeistas!');
            self.context.router.push( '/');
        });
        } else {
            alert('Slaptažodžiai nesutampa!');
        }
    },
    
    
    render: function() {
        return (
    <div className="col-md-4">
        <ChangePasswordComponent 
                admin={this.state.admin}
                passwordError={this.state.passwordError}
                onChangePassClick={this.handleChangePass}
                onFieldChange={this.handleFieldChange}
                />
                </div>
                )
    }
});

ChangePasswordContainer.contextTypes = {
        router: React.PropTypes.object.isRequired,
    };

window.ChangePasswordContainer = ChangePasswordContainer;