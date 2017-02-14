var LoggedInRepresentativeInfoComponent = React.createClass({
    render: function() {
        var self = this;
        console.log('CURRENTDISTRICT ' + self.props.currentDistrictId);
        return (
                <div>
                    <h4>Apygarda: </h4>
                    <h4>Apylinkė: {self.props.districtName} {self.props.currentDistrictId}</h4>
                    <h4>Atstovas: {self.props.currentUserName} {self.props.currentUserSurname}</h4><br />
                </div>
        )
        
    }
});

window.LoggedInRepresentativeInfoComponent = LoggedInRepresentativeInfoComponent;