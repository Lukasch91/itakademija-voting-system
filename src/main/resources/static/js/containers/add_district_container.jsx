var AddDistrictContainer = React.createClass( {

    getInitialState: function() {
        return {
            district: {
                title: '',
                numberOfVoters: '',
                address: ''
            },
            validationArray: []
        }
    },

    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var district = self.state.district;
            district[fieldName] = e.target.value;
            self.setState( { district: district });
        };
    },

    handleAddDistrict: function( e ) {
        e.preventDefault();
        var self = this;
        var success = 0;

        axios.post( '/api/district', {
            title: this.state.district.title,
            numberOfVoters: this.state.district.numberOfVoters,
            address: this.state.district.address,
            constituencyId: this.props.params.conId
        }).then( function( response ) {
            success = 1;
            console.log( "-----------------" + success );
            console.log( "sagg_test_02:" );
            console.log( response );
            console.log( "district added" );
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
                    console.log( 'district added' );
                    self.context.router.push( '/dis/' + self.props.params.conId );//ar reikia self.props.params.conId
                }
            });
    },


    handleCancel: function() {
        this.context.router.push( '/dis/' + this.props.params.conId );
    },

    render: function() {
        return <AddDistrictComponent
            district={this.state.district}
            onFieldChange={this.handleFieldChange}
            onAddClick={this.handleAddDistrict}
            onCancel={this.handleCancel}
            validationArray={this.state.validationArray}
            />

    }
});

AddDistrictContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.AddDistrictContainer = AddDistrictContainer;