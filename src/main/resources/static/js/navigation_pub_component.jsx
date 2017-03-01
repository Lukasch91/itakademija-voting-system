var Link = window.ReactRouter.Link;

var NavLink = function( props, context ) {
    var isActive = context.router.isActive( props.to );
    var className = isActive ? 'active' : '';

    return (
        <li className={className}>
            <Link {...props}>
                {props.children}
            </Link>
        </li>
    );
};

NavLink.contextTypes = {
    router: React.PropTypes.object,
};

NavLink.propTypes = {
    children: React.PropTypes.node.isRequired,
    to: React.PropTypes.string.isRequired,
};

var NavigationPubComponent = React.createClass( {
    render: function() {
        return (
            <nav className="navbar navbar-inverse">
                <div className="container-fluid">
                    <ul className="nav navbar-nav">
                        <NavLink to="/" onlyActiveOnIndex>Home</NavLink>
                        <NavLink id="candidates" to="/candidates">Kandidatai</NavLink>
                        <NavLink id="results" to="/results">Vienmandatės rezultatai</NavLink>
                        <NavLink id="multiresults" to="/multiresults">Daugiamandatės rezultatai</NavLink>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationPubComponent = NavigationPubComponent;