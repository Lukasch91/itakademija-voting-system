var ChangePasswordContainer = React.createClass( {

    getInitialState: function() {
        return {
            admin: {
                newPass: '',
                newPassCheck: '',
                changeStatus: '',
                redirectTo: '',
                passwordValidation: ''
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
        if ( this.state.admin.newPass != this.state.admin.newPassCheck ) {
            self.setState( { changeStatus: 'Slaptažodžiai nesutampa!', redirectTo: '/change-pass' });
            
        } else {
            axios.post( 'http://localhost:8080/api/ADMIN/changepass?password=' + this.state.admin.newPass ).then( function( response ) {
                self.setState( { passwordValidation: response.data });
                console.log(self.state.passwordValidation);
                if ( self.state.passwordValidation == false ) {
                    self.setState( { changeStatus: 'Slaptažodis per silpnas!', redirectTo: '/change-pass' });      
                } else {
                    self.setState( { changeStatus: 'Slaptažodis sėkmingai pakeistas', redirectTo: '/' });
                }
            });
        }
    },
    
    handlePassChangeRedirect: function() {
        this.context.router.push( this.state.redirectTo );
    },


    render: function() {
        return (
            <div className="col-md-4">
                <ChangePasswordComponent
                    admin={this.state.admin}
                    changeStatus={this.state.changeStatus}
                    onChangePassClick={this.handleChangePass}
                    onFieldChange={this.handleFieldChange}
                    onPassChangeRedirect={this.handlePassChangeRedirect}
                    />
            </div>
        )
    }
});

ChangePasswordContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.ChangePasswordContainer = ChangePasswordContainer;