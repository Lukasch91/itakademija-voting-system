var App = React.createClass( {
    render: function() {
        return (
            <div style={{ paddingTop: '10px' }}>
                <a href="/"><img style={{ paddingBottom: '10px' }} src="images/vrk-logo-lt.png" alt="vrk logo" /></a>
                <NavigationRepContainer />

                {this.props.children}
                <div className="container footer navbar-fixed-bottom">&copy; KALM, 2017</div>
            </div>
        );
    }
});

var NoMatch = React.createClass( {
    render: function() {
        return <div>Route did not match</div>;
    }
});

var Router = ReactRouter.Router;
var Route = ReactRouter.Route;
var IndexRoute = ReactRouter.IndexRoute;
var hashHistory = ReactRouter.hashHistory;

ReactDOM.render((
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={HomeComponent} />
            <Route path="/home" component={HomeComponent} />
            <Route path="/reg-votes-multi" component={RegisterVotesMultiContainer} />
            <Route path="/reg-votes-single" component={RegisterVotesSingleContainer} />
            <Route path="/change-pass" component={ChangePasswordContainer} />
            <Route path="*" component={NoMatch} />
        </Route>
    </Router>
), document.getElementById( 'root_rep' ) );
