var AdministrateRepresentativeContainer = React.createClass( {



    getInitialState: function() {
        return {
            representative: {
                name: '',
                surname: '',
                loginName: '',
                email: ''
            },
            passwordList: [],
            password: null,
            passwordHashed: null,
            passwordTest: '666',
            validationArray: []
        }
    },
    
    componentWillMount: function() {
        var self = this;
        axios.post( '/api/ADMIN/password/gen' )
        .then( function( response ) {
            self.setState( {
                passwordList: response.data
            });
            
        });
        
    },
    
    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var representative = self.state.representative;
            representative[fieldName] = e.target.value;
            self.setState( { representative: representative });
        };
    },

    handleCancel: function() {
        this.context.router.push( '/dis/' + this.props.params.conId );
    },

    handleAddRepresentative: function( e ) {
        e.preventDefault();
        var self = this;
        var success = 0;         
      
        this.state.password = this.state.passwordList[0];
        axios.post('http://localhost:8080/api/ADMIN/password/hash?password=' + this.state.passwordList[0]).then( function( response ) {
            self.setState( {
                passwordHashed: response.data
            });
        
        
        axios.post( '/api/ADMIN/representative', {
            name: self.state.representative.name,
            surname: self.state.representative.surname,
            loginName: self.state.representative.loginName,
            password: self.state.passwordHashed,
            email: self.state.representative.email,
            districtId: self.props.params.disId

        })
            .then( function( response ) {
                axios.post('api/ADMIN/mail?toMail=' + self.state.representative.email + '&password=' + self.state.passwordList[0] + '&loginName=' + self.state.representative.loginName);
                success = 1;
            })
            .catch( function( error ) {
                if ( error.response.status == 400 ) {

                    console.log( "___Error messages:___" );
                    for ( var i = 0; i < ( error.response.data.errors.length ); i++ ) {
                        console.log( ">>>" + error.response.data.errors[i].defaultMessage );
                    }

                    var validationArray = self.state.validation;
                    self.setState( { validationArray: error.response.data.errors });
                }
                else {
                    console.log( "___Error response object___" );
                    console.log( error.response );
                }

            }).then( function() {
                if ( success == 1 ) {
                    self.context.router.push( '/dis/' + self.props.params.conId );
                }
            });
        });
    },

    render: function() {
        return <AdministrateRepresentativeComponent
            representative={this.state.representative}
            validationArray={this.state.validationArray}
            onFieldChange={this.handleFieldChange}
            onAddRepresentative={this.handleAddRepresentative}
            onCancel={this.handleCancel}
            password={this.state.password}
        />
    }
});

AdministrateRepresentativeContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.AdministrateRepresentativeContainer = AdministrateRepresentativeContainer;