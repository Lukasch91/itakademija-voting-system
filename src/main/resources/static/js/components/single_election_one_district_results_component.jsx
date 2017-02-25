var SingleElectionsOneDistrictResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var candidateDistResults = this.props.candidates.map( function( candidate, index ) {
            return (
                <tr key={index}>
                    <td>{candidate.candidateFirstname}</td>
                    <td>{candidate.candidateSurname}</td>
                    <td>{candidate.party}</td>
                    <td>{candidate.voted}</td>
                    <td>{candidate.percentageOfValidVotes} %</td>
                    <td>{candidate.percentageOfAllVotes}</td>
                </tr>
            );
        });

        return (

            <div>
                <div className="panel panel-default">
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Apygarda</th>
                                <th>Apylinkių sk.</th>
                                <th>Rinkėjų sk.</th>
                                <th>Balsavo</th>
                                <th>Balsavo proc.</th>
                                <th>Sugadinti balsai</th>
                            </tr>
                        </thead>
                        <tbody>
                            {candidateDistResults}
                        </tbody>
                    </table>
                </div>


                <button id="bakcToConstituencyResults" className="btn btn-xs btn-warning" onClick={this.props.onCancel}>Grįžti</button>
            </div>
        )
    }
});



window.SingleElectionsOneDistrictResultsComponent = SingleElectionsOneDistrictResultsComponent;