var AddConstituencyContainer = React.createClass( {

    getInitialState: function() {
        return {
            constituency: {
                title: ''
            },
            validationArray: []
        }
    },

    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var constituency = self.state.constituency;
            constituency[fieldName] = e.target.value;
            self.setState( { constituency: constituency });
        };
    },

    handleAddConstituency: function( e ) {
        e.preventDefault();
        var self = this;
        var success = 0;
        axios.post( '/api/constituency', this.state.constituency )
            .then( function( response ) {
                success = 1;
                console.log( "-----------------" + success );
                console.log( "sagg_test_02:" );
                console.log( response );
                console.log( "constituency added" );
            })
            .catch( function( error ) {
                if ( error.response.status == 400 ) {
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
                    var validationArray = self.state.validation;
                    self.setState( { validationArray: error.response.data.errors });
                }
                else {
                    console.log( "___Error response object___" );
                    console.log( error.response );
                }
            }).then( function() {
                if ( success == 1 ) {
                    console.log( 'constituency added' );
                    self.context.router.push( '/con' );
                }
            });
    },




    handleCancel: function() {
        this.context.router.push( '/con' );
    },


    render: function() {
        return <AddConstituencyComponent
            constituency={this.state.constituency}
            validationArray={this.state.validationArray}
            onFieldChange={this.handleFieldChange}
            onAddClick={this.handleAddConstituency}
            onCancel={this.handleCancel}
            />
    }
});


AddConstituencyContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.AddConstituencyContainer = AddConstituencyContainer;