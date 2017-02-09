var NavigationContainer = React.createClass({
    
    handleLogout: function() {
        axios.get('/logout').then(function () {
            window.location.reload()
          });

    },
      
    render: function() {
        return (
            <NavigationComponent
                onLogoutClick={this.handleLogout}
                />
        );
    }
});

window.NavigationContainer = NavigationContainer;