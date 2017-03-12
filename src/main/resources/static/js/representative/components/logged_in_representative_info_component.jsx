var LoggedInRepresentativeInfoComponent = React.createClass( {
    render: function() {
        var self = this;
        var district = this.props.district != null ? this.props.district : {};
        var user = this.props.user != null ? this.props.user : {};
        var constituency = this.props.constituency != null ? this.props.constituency : {};

        return (
            <div className="col-sm-4 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                <div >
                    <ul className="list-group">
                        <li className="list-group-item"><b>Apygarda:</b> {constituency.title} </li>
                        <li className="list-group-item"><b>Apylinkė:</b> {district.title}</li>
                        <li className="list-group-item"><b>Rinkėjų skaičius apylinkėje:</b> {district.numberOfVoters}</li>
                        <li className="list-group-item"><b>Apylinkės atstovas:</b>  {user.name} {user.surname}</li>
                    </ul>
                </div>
            </div>
        )

    }
});

window.LoggedInRepresentativeInfoComponent = LoggedInRepresentativeInfoComponent;