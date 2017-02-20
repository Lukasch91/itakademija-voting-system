var SingleElectionsResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var constResultsList = this.props.consituencies.map( function( constituency, index ) {
            return (
                <tr key={index}>
                    <td>{constituency.title}</td>
                    <td>{constituency.numberOfDistricts}</td>
                    <td>{constituency.numberOfVoters}</td>
                    <td>{constituency.numberOfVotersWhoVote}</td>
                    <td>{constituency.percentageOfVotersWhoVote}%</td>
                    <td>{constituency.numberOfInvalidVotes}</td>
                    <td>{constituency.percentageOfInvalidVotes}%</td>
                    <td>{constituency.numberOfValidVotes}</td>
                    <td>{constituency.percentageOfValidVotes}%</td>
                </tr>
            );
        });

        return (
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
                            <th>Sugadinti proc.</th>
                            <th>Geri balsai</th>
                            <th>Geri balsai proc</th>
                        </tr>
                    </thead>
                    <tbody>
                        {constResultsList}
                    </tbody>
                </table>
            </div>
        )
    }
});



window.SingleElectionsResultsComponent = SingleElectionsResultsComponent;