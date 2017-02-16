var PubDelVotesDistrictListComponent = React.createClass( {
    render: function() {

        var self = this;
        var districtList = this.props.districts.map( function( district, index ) {
                return (
                    <tr key={index}>
                        <td>{district.title}</td>
                        <td><button type="button" className="btn btn-default" disabled >Peržiūrėti balsus</button></td>
                        <td><button type="button" className="btn btn-primary" onClick={self.props.onPublishSingleVotes(district.id)} >Publikuoti balsus</button></td>
                        <td><button type="button" className="btn btn-danger" onClick={self.props.onDeleteSingleVotes(district.id)} >Trinti balsus</button></td>
                        <td><button type="button" className="btn btn-default" disabled >Peržiūrėti balsus</button></td>
                        <td><button type="button" className="btn btn-primary" onClick={self.props.onPublishMultiVotes(district.id)} >Publikuoti balsus</button></td>
                        <td><button type="button" className="btn btn-danger" onClick={self.props.onDeleteMultiVotes(district.id)} >Trinti balsus</button></td>
                    </tr>
                )       
        });

        return (
            <div className="panel panel-default">
                <h3>{this.props.constit.title}</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Apylinkės pavadinimas</th>
                            <th colSpan="3" style={{textAlign:"center"}}>Vienamandatės</th>
                            <th colSpan="3" style={{textAlign:"center"}}>Daugiamandatės</th>
                        </tr>
                    </thead>
                    <tbody>
                        {districtList}
                    </tbody>
                </table>

            </div>
        )
    }
});




window.PubDelVotesDistrictListComponent = PubDelVotesDistrictListComponent;