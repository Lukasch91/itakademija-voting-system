var NavigationPubContainer = React.createClass( {

    getInitialState: function() {
        return {
            onLink: '/login',
            currentUserRole: '',
            loginText: 'Prisijungti'
        }

    },

    componentWillMount: function() {
        var self = this;
        axios.get('/currentuser')
        .then(function (response) {
            self.setState({ 
                currentUserRole: response.data.authorities[0].authority
            });
        });
    },
    
    render: function() {
        var self = this;

        if (this.state.currentUserRole == 'ROLE_ADMIN') {
                this.state.onLink = '/admin';
                this.state.loginText = 'Administratoriui'
        } else if (this.state.currentUserRole == 'ROLE_REPRESENTATIVE') {
                this.state.onLink = '/rep';
                this.state.loginText = 'Atstovui';
        }

        return (
            <NavigationPubComponent
                onLink={this.state.onLink}
                loginText={this.state.loginText}
                />
        );
    }
});


window.NavigationPubContainer = NavigationPubContainer;