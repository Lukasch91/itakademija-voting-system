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

var NavigationRepComponent = React.createClass( {
    render: function() {
        return (
            <nav className="navbar navbar-inverse">
                <div className="container-fluid">
                    <ul className="nav navbar-nav">
                        <NavLink to="/home" onlyActiveOnIndex>Home</NavLink>
                        <NavLink id="multiVoteRegistration" to="/reg-votes-multi">Balsų registravimas (daug.)</NavLink>
                        <NavLink id="singleVoteRegistration" to="/reg-votes-single">Balsų registravimas (vien.)</NavLink>
                     </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <NavLink id="changeAdminPassword" to="/change-pass">Info</NavLink>
                        <NavLink id="logout" to="/home" onClick={this.props.onLogoutClick}>Atsijungti</NavLink>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationRepComponent = NavigationRepComponent;