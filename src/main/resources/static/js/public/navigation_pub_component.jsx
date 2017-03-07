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
                        <NavLink to="/home" onlyActiveOnIndex>Pradinis</NavLink>
                        <NavLink id="/candidates" to="/candidates">Kandidatai</NavLink>
                        <li id="results" className="dropdown">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Rezultatai <span className="caret"></span></a>
                        <ul className="dropdown-menu">
                        <NavLink id="singleresults" to="/results">Vienmandatės rezultatai</NavLink>
                        <NavLink id="multiresults" to="/multiresults">Daugiamandatės rezultatai</NavLink>
                        </ul>
                      </li>
                        
                    </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <li>
                        <Link id="login" href="/login">Prisijungti</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationPubComponent = NavigationPubComponent;