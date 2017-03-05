var NavigationAdminContainer = React.createClass({
    
    handleLogout: function() {
        axios.get('/logout').then(function () {
            window.location.reload()
          });

    },
      
    render: function() {
        return (
            <NavigationAdminComponent
                onLogoutClick={this.handleLogout}
                />
        );
    }
});

window.NavigationAdminContainer = NavigationAdminContainer;