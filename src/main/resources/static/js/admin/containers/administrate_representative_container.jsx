var AdministrateRepresentativeContainer = React.createClass( {



    getInitialState: function() {
        return {
            representative: {
                name: '',
                surname: '',
                loginName: '',
                email: ''
            },
            password: null,
            disabledTest: false,
            validationArray: []
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

    handleCancel: function() {
        this.context.router.push( '/dis/' + this.props.params.conId );
    },

    handleAddRepresentative: function( e ) {
        e.preventDefault();
        var self = this;
        var success = 0;
/*        axios.post('/api/ADMIN/mail', {
            toMail: 'hhu@myud.bounceme.net',
            password: '123',
            loginName: 'Jonka'
        });*/
        axios.post( '/api/ADMIN/representative', {
            name: this.state.representative.name,
            surname: this.state.representative.surname,
            loginName: this.state.representative.loginName,
            password: this.state.password,
            email: this.state.representative.email,
            districtId: this.props.params.disId

        })
            .then( function( response ) {
                success = 1;
                console.log( response );
                console.log( "representative added" );
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
    },
    
    handleGeneratePass: function() {
        this.state.password = '666';
        console.log(this.state.passwordTest);
    },

    render: function() {
        return <AdministrateRepresentativeComponent
            representative={this.state.representative}
            validationArray={this.state.validationArray}
            onFieldChange={this.handleFieldChange}
            onAddRepresentative={this.handleAddRepresentative}
            onCancel={this.handleCancel}
            onGeneratePass={this.handleGeneratePass}
            password={this.state.password}
            disabledTest={this.state.disabledTest}
        />
    }
});

AdministrateRepresentativeContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.AdministrateRepresentativeContainer = AdministrateRepresentativeContainer;