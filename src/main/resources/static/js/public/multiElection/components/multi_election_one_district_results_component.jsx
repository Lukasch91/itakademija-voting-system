var MultiElectionsOneDistrictResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var PartiesDistResults = this.props.parties.map( function( party, index ) {
            return (
                <tr key={index}>
                    <td>{party.id}</td>
                    <td>{party.partyTitle}</td>
                    <td>{party.votes}</td>
                    <td>{party.percentageOfAllVotes}</td>
                </tr>
            );
        });

        return (

            <div>
                <div className="panel panel-default">
                    <table className="table table-hover">
                        <thead>
                                          <tr>
                                <th>Reg. Nr.</th>
                                <th>Partijos pavadinimas</th>
                                <th>Balsavo</th>
                                <th>Balsavo proc.</th>
                            </tr>
                        </thead>
                        <tbody>
                            {PartiesDistResults}
                        </tbody>
                    </table>
                </div>


                <button id="bakcToConstituencyResults" className="btn btn-xs btn-warning" onClick={this.props.onCancel}>Grįžti</button>
            </div>
        )
    }
});



window.MultiElectionsOneDistrictResultsComponent = MultiElectionsOneDistrictResultsComponent;