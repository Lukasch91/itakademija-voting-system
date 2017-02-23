var SingleElectionsDistrictResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var distrResultsList = this.props.districts.map( function( district, index ) {

            return (
                <tr key={index}>
                    <td>{district.title}</td>
                    <td>{district.numberOfVoters}</td>
                    <td>{district.voted}</td>
                    <td>{district.percentageOfVoted} %</td>
                    <td>{district.invalidVotes}</td>
                    <td>{district.percentageOfInvalidVotes} %</td>
                    <td>{district.validVotes}</td>
                    <td>{district.percentageOfValidVotes} %</td>
                </tr>
            );
        });

        var candidatesResultList = this.props.candidates.map( function( candidate, index ) {

            return (
                <tr key={index}>
                    <td>{candidate.candidateFirstname}</td>
                    <td>{candidate.candidateSurname}</td>
                    <td>{candidate.party}</td>
                    <td>{candidate.voted}</td>
                    <td>{candidate.percentageOfValidVotes}</td>
                    <td>{candidate.percentageOfAllVotes} %</td>
                </tr>
            );
        });

        return (
            <div className="panel panel-default">
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Apylinkė</th>
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
                        {distrResultsList}
                    </tbody>
                </table>
                <label> Apygardos rezultatai</label>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Vardas</th>
                            <th>Pavardė</th>
                            <th>Partija</th>
                            <th>Balsavo</th>
                            <th>% nuo galiojančių biuletenių</th>
                            <th>% nuo dalyvavusių rinkėjų</th>
                        </tr>
                    </thead>
                    <tbody>
                        {candidatesResultList}
                    </tbody>
                </table>
            </div>
        )
    }
});



window.SingleElectionsDistrictResultsComponent = SingleElectionsDistrictResultsComponent;