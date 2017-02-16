
var PubDelVotesDistrictListComponent = React.createClass( {
    
    render: function() {
        var disabled = true;   
        var self = this;
        var districtList = this.props.districts.map( function( district, index ) {
            /*if (getMultiVotesByDistrict(district.id).length != 0) {
                disabled = false;
            }    */
/*            console.log('disId ' + district.id);
            
            var someShit = axios.get('/api/reg-votes-multi/dis/' + district.id)
            .then(function (response) {
              console.log(response);
              return response;
            })
            console.log(someShit);*/
            return (
                    <tr key={index}>
                        <td>{district.title}</td>
                        <td><button type="button" className="btn btn-default" disabled >Peržiūrėti balsus</button></td>
                        <td><button type="button" className="btn btn-primary" onClick={self.props.onPublishSingleVotes(district.id)} disabled >Publikuoti balsus</button></td>
                        <td><button type="button" className="btn btn-danger" onClick={self.props.onDeleteSingleVotes(district.id)} disabled >Trinti balsus</button></td>
                        <td><button type="button" className="btn btn-default" disabled >Peržiūrėti balsus</button></td>
                        <td><button type="button" className="btn btn-primary" onClick={self.props.onPublishMultiVotes(district.id)} disabled={disabled}>Publikuoti balsus</button></td>
                        <td><button type="button" className="btn btn-danger" onClick={self.props.onDeleteMultiVotes(district.id)} disabled={disabled}>Trinti balsus</button></td>
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