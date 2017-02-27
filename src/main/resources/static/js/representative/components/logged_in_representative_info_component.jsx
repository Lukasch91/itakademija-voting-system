var LoggedInRepresentativeInfoComponent = React.createClass( {
    render: function() {
        var self = this;
        var district = this.props.district != null ? this.props.district : {};
        var user = this.props.user != null ? this.props.user : {};
        return (
            <div>
                <h3>Apylinkė: {district.title} - balsuotojų: {district.numberOfVoters}</h3>
                <h4>Atstovas:  {user.name} {user.surname} </h4><br />
            </div>
        )

    }
});

window.LoggedInRepresentativeInfoComponent = LoggedInRepresentativeInfoComponent;