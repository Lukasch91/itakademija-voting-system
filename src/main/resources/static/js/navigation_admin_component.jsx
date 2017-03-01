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
                        <NavLink to="/home" onlyActiveOnIndex>Home</NavLink>
                        <NavLink id="constituency" to="/con">Apygardos/Apylinkės</NavLink>
                        <NavLink id="party" to="/parties">Partijos</NavLink>
                        <NavLink id="singleMember" to="/upload-single-cadidates">Kandidatų įkėlimas (vien.)</NavLink>
                        <NavLink id="multiMember" to="/upload-multi-cadidates">Kandidatų įkėlimas (daug.)</NavLink>
                        <NavLink id="resultDeletePage" to="/publish-delete-votes">Rezultatų publikavimas/anuliavimas</NavLink>

                    </ul>
                    <ul className="nav navbar-nav navbar-right">
                        <button id="logout" type="button" className="btn btn-default" onClick={this.props.onLogoutClick}>Atsijungti</button>
                    </ul>
                </div>
            </nav>
        );
    }
});

window.NavigationAdminComponent = NavigationAdminComponent;