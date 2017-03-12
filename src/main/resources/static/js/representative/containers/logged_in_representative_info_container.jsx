var LoggedInRepresentativeInfoContainer = React.createClass( {

    getInitialState: function() {
        return {
            currentUser: {},
            district: {},
            constituencyOfDistrict: {}
        };
    },

    propPreloader: function() {

        var self = this;
        var p1 = new Promise( function( resolve, reject ) {

            var currentUser = self.props.currentUser;
            self.setState( { currentUser: currentUser });

            resolve( 'Success!' );
            // or
            // reject ("Error!");
        });
        return p1;

    },

    componentWillMount: function() {
        var self = this;

        self.propPreloader().then( function() {

            var districtId = self.state.currentUser != null ? self.state.currentUser.districtId : null;

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