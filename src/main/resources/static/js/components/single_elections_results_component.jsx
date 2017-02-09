var SingleElectionsResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var constResultsList = this.props.consituencies.map( function( consituency, index ) {
            return (
                <tr key={index}>
                    <td>{consituency.title}</td>
                    <td>{consituency.numberOfDistricts}</td>
                    <td>{consituency.numberOfVoters}</td>
                    <td>{consituency.numberOfVotersWhoVote}</td>
                    <td>{consituency.numberOfInvalidVotes}</td>
                    <td>{consituency.numberOfValidVotes}</td>
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
                            <th>Sugadinti balsai</th>
                            <th>Geri balsai</th>
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