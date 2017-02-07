var NavigationRepContainer = React.createClass({
    
    handleLogout: function(e) {
        axios.get('/logout').then(function () {
            window.location.reload()
          });

    },
      
    render: function() {
        return (
            <NavigationRepComponent
                onLogoutClick={this.handleLogout}
                />
        );
    }
});

window.NavigationRepContainer = NavigationRepContainer;