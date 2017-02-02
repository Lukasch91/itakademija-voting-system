var AdministrateRepresentativeContainer = React.createClass( {

    
    
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
        
        console.log( "sagg_test_01:" );
        axios.post( '/api/representative', {
            name: this.state.representative.name,
            surname: this.state.representative.surname,
            loginName: this.state.representative.loginName,
            password: this.state.representative.password,
            email: this.state.representative.email,
            districtId: this.props.params.disId
            
                  })
/*=================*/
            .then( function( response ) {
                success = 1;
                console.log("-----------------"+success);
                console.log( "sagg_test_02:" );
                console.log( response );
                console.log( "representative added" );
            })
            .catch( function( error ) {
                if(error.response) {
                    console.log( "sagg_test_03: error response message" );
                    console.log( error.response.data.message );
                    console.log( "sagg_test_04: error response object" );
                    console.log( error.response );
                    console.log( "sagg_test_05: error response status:" );
                    console.log( "ErrorStatus: " + error.response.status );

                    console.log( "sagg_test_06: error message:" );
                    for ( var i = 0; i < ( error.response.data.errors.length ); i++ ) {
                        console.log( ">>>" + error.response.data.errors[i].defaultMessage );
                    }
                }
             }).then(function() {
                 if (success == 1) {
                     self.context.router.push( '/dis/' + self.props.params.conId );
                 }
             });



            

        
/*=================*/
        
    },

    render: function() {
        return <AdministrateRepresentativeComponent
            representative={this.state.representative}
            onFieldChange={this.handleFieldChange}
            onAddRepresentative={this.handleAddRepresentative}
            onCancel={this.handleCancel} />
    }
});

AdministrateRepresentativeContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.AdministrateRepresentativeContainer = AdministrateRepresentativeContainer;