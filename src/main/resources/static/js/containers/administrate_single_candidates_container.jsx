var AdministrateSingleCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            constituencies: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/constituency' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            });

    },
    //===========================================================
    handleAddDistrictCandidates: function( e ) {
        e.preventDefault();
        var self = this;




//
//        axios.post( '/api/representative', {
//            name: this.state.representative.name,
//            surname: this.state.representative.surname,
//            loginName: this.state.representative.loginName,
//            password: this.state.representative.password,
//            email: this.state.representative.email,
//            districtId: this.props.params.disId
//
//        })
//            .then( function( response ) {
//                success = 1;
//                console.log( response );
//                console.log( "representative added" );
//            })

    },
    //===========================================================

    render: function() {
        return (
            <div>
                <AdministrateSingleCandidatesComponent
                    constituencies={this.state.constituencies}
                    onAddDistrictCandidates={this.handleAddDistrictCandidates} />
            </div>
        )
    }
});

window.AdministrateSingleCandidatesContainer = AdministrateSingleCandidatesContainer;