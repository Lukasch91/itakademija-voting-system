var LoggedInRepresentativeInfoContainer = React.createClass( {

    getInitialState: function() {
        return {
            currentUser: {},
            district: {}
        };
    },

    componentWillMount: function() {
        var self = this;

        axios.get( '/currentuser' )
            .then( function( responseU ) {
                self.setState( { currentUser: responseU.data });
            })
            .then( function( e ) {
                axios.get( '/api/REPRES/district/' + self.state.currentUser.districtId )
                    .then( function( responseD ) {
                        self.setState( { district: responseD.data });
                    });
            });
    },

    render: function() {
        return (
            <LoggedInRepresentativeInfoComponent
                
                user={this.state.currentUser}
                district={this.state.district}
                />
        )
    }
});

window.LoggedInRepresentativeInfoContainer = LoggedInRepresentativeInfoContainer;