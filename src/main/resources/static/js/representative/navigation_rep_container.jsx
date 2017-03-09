var NavigationRepContainer = React.createClass({
    
    handleLogout: function() {
        axios.get('/logout');
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