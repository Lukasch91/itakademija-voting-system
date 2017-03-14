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
            <nav className="navbar navbar-inverse" style={{backgroundColor: '#006600', borderStyle: 'none'}}>
                <div className="container-fluid">
                    <ul className="nav navbar-nav">
                        <NavLink to="/home" onlyActiveOnIndex>Pradinis</NavLink>
                        <NavLink id="singleVoteRegistration" to="/reg-votes-single">Balsų registravimas (vien.)</NavLink>
                        <NavLink id="multiVoteRegistration" to="/reg-votes-multi">Balsų registravimas (daug.)</NavLink>                       
                     </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <NavLink id="changeAdminPassword" to="/change-pass"><span className="glyphicon glyphicon-cog"></span></NavLink>
                        <li>
                        <Link id="logout" href="/" onClick={this.props.onLogoutClick}>Atsijungti</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationRepComponent = NavigationRepComponent;