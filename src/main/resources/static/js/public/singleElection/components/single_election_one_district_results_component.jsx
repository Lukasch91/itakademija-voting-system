var SingleElectionsOneDistrictResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var info = this.props.info;
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
                Puslapis atnaujintas {info.updateDate}

                <h3>{info.constituencyTitle} rinkimų apygarda</h3>
                <h4>{info.districtTitle} rinkimų apylinkė</h4>
                Pagal gautus iš apylinkių duomenis: <br />
                rinkėjų sąraše įrašyta rinkėjų – {info.voters} ,<br />
                rinkimuose dalyvavo – {info.allVotes} ({info.percentageOfVoted} %), <br />
                negaliojančių biuletenių – {info.invalidVotes} ({info.percentageOfInvalidVotes} %),<br />
                galiojančių biuletenių – {info.validVotes} ({info.percentageOfValidVotes} %).
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