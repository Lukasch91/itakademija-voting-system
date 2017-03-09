var AddPartyContainer = React.createClass( {

    getInitialState: function() {
        return {
            party: {
                title: '',
                party_abbreviation: ''
            },
            validationArray: []
        }
    },

    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var party = self.state.party;
            party[fieldName] = e.target.value;
            self.setState( { party: party });
        };
    },

    handleAddParty: function( e ) {
        e.preventDefault();
        var self = this;
        var success = 0;

        axios.post( '/api/ADMIN/party', {
            title: this.state.party.title,
            party_abbreviation: this.state.party.party_abbreviation
        })
            .then( function( response ) {
                success = 1;
                console.log( "-----------------" + success );
                console.log( "sagg_test_02:" );
                console.log( response );
                console.log( "party added" );
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
                    self.context.router.push( '/parties' );//ar reikia self.props.params.conId
                }
            });
    },

    handleCancel: function() {
        this.context.router.push( '/parties' );
    },


    render: function() {
        return (
            <div className="col-md-4">
                <AddPartyComponent
                    party={this.state.party}
                    validationArray={this.state.validationArray}
                    onFieldChange={this.handleFieldChange}
                    onAddClick={this.handleAddParty}
                    onCancel={this.handleCancel} />
            </div>
        )
    }
});


AddPartyContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.AddPartyContainer = AddPartyContainer;