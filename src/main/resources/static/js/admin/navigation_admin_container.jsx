var NavigationAdminContainer = React.createClass({
    
    handleLogout: function() {
        axios.get('/logout');
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