var ChangePasswordContainer = React.createClass( {

    getInitialState: function() {
        return {
            representative: {
                newPass: '',
                newPassCheck: '',
                changeStatus: '',
                redirectTo: ''
            }

        }
    },

    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var representative = self.state.representative;
            representative[fieldName] = e.target.value;
            self.setState( { representative: representative });
        };
    },

    handleChangePass: function() {
        var self = this;
        if ( this.state.representative.newPass == this.state.representative.newPassCheck ) {
            axios.post( 'http://localhost:8080/api/REPRES/changepass?password=' + this.state.representative.newPass ).then( function() {
                self.setState( { changeStatus: 'Slaptažodis sėkmingai pakeistas', redirectTo: '/' });
            });
        } else {
            self.setState( { changeStatus: 'Slaptažodžiai nesutampa!', redirectTo: '/change-pass'  });
        }

    },

    handlePassChangeRedirect: function() {
        this.context.router.push( this.state.redirectTo );
    },

    render: function() {
        return (
                <div className="col-md-4">
            <ChangePasswordComponent
                representative={this.state.representative}
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