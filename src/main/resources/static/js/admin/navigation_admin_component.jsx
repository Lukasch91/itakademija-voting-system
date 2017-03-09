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

var NavigationAdminComponent = React.createClass( {
    render: function() {
        return (
            <nav className="navbar navbar-inverse">
                <div className="container-fluid">
                    <ul className="nav navbar-nav">
                        <NavLink to="/home" onlyActiveOnIndex>Pradinis</NavLink>
                        <NavLink id="constituency" to="/con">Apygardos/Apylinkės</NavLink>
                        <NavLink id="party" to="/parties">Partijos</NavLink>
                        <li id="addCandidates" className="dropdown">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Kandidatų įkėlimas <span className="caret"></span></a>
                        <ul className="dropdown-menu">
                        <NavLink id="singleMember" to="/upload-single-cadidates">Apygardoms</NavLink>
                        <NavLink id="multiMember" to="/upload-multi-cadidates">Partijoms</NavLink>
                        </ul>
                      </li>
                        
                        
                        <NavLink id="resultDeletePage" to="/publish-delete-votes">Rezultatai</NavLink>
                    </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <NavLink id="changeAdminPassword" to="/change-pass">Info</NavLink>
                        <li>
                        <Link id="logout" href="/" onClick={this.props.onLogoutClick}>Atsijungti</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationAdminComponent = NavigationAdminComponent;