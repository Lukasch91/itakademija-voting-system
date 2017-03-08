var SingleElectionsResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var info = this.props.info;
        var constResultsList = this.props.consituencies.map( function( constituency, index ) {
            return (
                <tr key={index}>
                    <td>{constituency.title}</td>
                    <td>{constituency.numberOfDistrictsPublishedResults} /{constituency.numberOfDistricts}</td>
                    <td>{constituency.numberOfVoters}</td>
                    <td>{constituency.numberOfVotersWhoVote}</td>
                    <td>{constituency.percentageOfVotersWhoVote} %</td>
                    <td>{constituency.numberOfInvalidVotes}</td>
                    <td>{constituency.percentageOfInvalidVotes} %</td>
                    <td>{constituency.numberOfValidVotes}</td>
                    <td>{constituency.percentageOfValidVotes} %</td>
                    <td><button type="button" className="btn btn-xs btn-info" onClick={self.props.onAdministerDistricts( constituency.constituencyId )}>Apylinkių rezultatai</button></td>

                </tr>
            );
        });

        return (
            <div>
                <h2>Balsavimo rezultatai vienmandatėse apygardose</h2>
                <h7>
                    Apylinkių skaičius – {info.numberOfDistricts}<br />
                    Apygardų skaičius – {info.numberOfConstituencies}  <br />
                    Pagal gautus iš apylinkių duomenis:<br />
                    rinkėjų sąraše įrašyta rinkėjų – {info.numberOfVoters} , rinkimuose dalyvavo – {info.numberOfVotersWhoVoted} ({info.percentageOfVoters} %)<br />
                    Negaliojančių biuletenių – {info.invalidVotes} ({info.percentageOfInvalidVotes} %), galiojančių biuletenių – {info.validvotes} ({info.percentageOfValidVotes} %)<br />
                </h7>
                <div>
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
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {constResultsList}
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
});



window.SingleElectionsResultsComponent = SingleElectionsResultsComponent;