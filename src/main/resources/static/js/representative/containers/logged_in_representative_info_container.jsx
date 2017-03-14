var LoggedInRepresentativeInfoContainer = React.createClass( {

    getInitialState: function() {
        return {
            currentUser: {},
            district: {},
            constituencyOfDistrict: {}
        };
    },

    componentWillMount: function() {
        var self = this;

        axios.get( '/currentuser' )
            .then( function( response ) {
                self.setState( { currentUser: response.data });
                var districtId = response.data.districtId;

                if ( districtId != null ) {
                    axios.all( [
                        axios.get( '/api/REPRES/district/' + districtId ),
                        axios.get( '/api/PUBLIC/constresultsdis/' + districtId )
                    ] )
                        .then( axios.spread( function( districtResponse, constituencyResponse ) {
                            self.setState( {
                                district: districtResponse.data,
                                constituencyOfDistrict: constituencyResponse.data
                            });
                        }) );
                }
            });
    },

    render: function() {
        return (
            <LoggedInRepresentativeInfoComponent
                user={this.state.currentUser}
                district={this.state.district}
                constituency={this.state.constituencyOfDistrict}
                />
        )
    }
});

window.LoggedInRepresentativeInfoContainer = LoggedInRepresentativeInfoContainer;