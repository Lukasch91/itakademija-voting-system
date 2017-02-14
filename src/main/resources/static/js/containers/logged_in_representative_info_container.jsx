var LoggedInRepresentativeInfoContainer = React.createClass( {

    getInitialState: function() {
        return {
            currentUserName: null,
            currentUserSurname: null,
            currentDistrictId: 0,
            districtName: null
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.all([
                    axios.get( '/currentuser' ),
                    axios.get( '/api/district/' + self.state.currentDistrictId )
                   ])
                   .then( axios.spread(function( userResponse, districtResponse ) {
                self.setState( {
                    currentUserName: userResponse.data.name,
                    currentUserSurname: userResponse.data.surname,
                    currentDistrictId: userResponse.data.districtId,
                    districtName: districtResponse.data.title
                });
            }));
/*        axios.get( '/currentuser' )
            .then( function( response ) {
                self.setState( {
                    currentUserName: response.data.name,
                    currentUserSurname: response.data.surname,
                    currentDistrictId: response.data.districtId
                });
            });
        var distId = self.state.currentDistrictId;
        if ( distId != 0 ) {
            axios.get( '/api/district/' + distId )
                .then( function( response ) {
                    self.setState( {
                        districtName: response.data.title
                    });
                });
        }*/
    },

    render: function() {
        return (
            <LoggedInRepresentativeInfoComponent
                currentUserName={this.state.currentUserName}
                currentUserSurname={this.state.currentUserSurname}
                currentDistrictId={this.state.currentDistrictId}
                districtName={this.state.districtName}
                />
        )
    }
});

window.LoggedInRepresentativeInfoContainer = LoggedInRepresentativeInfoContainer;