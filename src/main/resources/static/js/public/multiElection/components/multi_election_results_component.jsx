var MultiElectionsResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var info = this.props.info;
        var constResultsList = this.props.consituencies.map( function( constituency, index ) {
            return (
                <tr key={index}>
                    <td>{constituency.title}</td>
                    <td>{constituency.districtsPublishedResults} /{constituency.numberOfDistricts}</td>
                    <td>{constituency.numberOfVoters}</td>
                    <td>{constituency.numberOfVotersWhoVoted}</td>
                    <td>{constituency.percentegeOfVoted} %</td>
                    <td>{constituency.invalidVotes}</td>
                    <td>{constituency.percentageOfInvalidVotes} %</td>
                    <td>{constituency.validVotes}</td>
                    <td>{constituency.percentageOfValidVotes} %</td>
                    <td><button type="button" className="btn btn-xs btn-info" onClick={self.props.onAdministerDistricts( constituency.id )}>Apylinkių rezultatai</button></td>

                </tr>


            );
        });

        var partiesResultsList = this.props.parties.map( function( party, index ) {
            return (
                <tr key={index}>
                    <td>{party.id}</td>
                    <td>{party.partyTitle}</td>
                    <td>{party.votes}</td>
                    <td>{party.percentageOfAllVotes} %</td>
                    <td>{party.numberOfMandates}</td>
                </tr>


            );
        });

        return (
            <div>
                <div className="row">
                    <div className="col-sm-12">
                        <h2>Balsavimo rezultatai daugiamandatėse apygardose</h2>
                    </div>
                </div>
                <div className="row">
                    <div className="col-sm-4">
                        <h7><br />
                            Apylinkių skaičius – {info.numberOfDistricts}<br />
                            Apygardų skaičius – {info.numberOfConstituencies}  <br />
                            Pagal gautus iš apylinkių duomenis:<br />
                            rinkėjų sąraše įrašyta rinkėjų – {info.numberOfVoters} , <br />
                            rinkimuose dalyvavo – {info.numberOfVotersWhoVoted} ({info.percentageOfVoters} %)<br />
                            Negaliojančių biuletenių – {info.invalidVotes} ({info.percentageOfInvalidVotes} %),<br />
                            galiojančių biuletenių – {info.validvotes} ({info.percentageOfValidVotes} %)<br />
                        </h7>
                    </div>
                    <div className="col-sm-8"><canvas id="myChart" height="75"></canvas></div>
                </div>

                <div>
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Reg. nr.</th>
                                <th>Partijos pavadinimas</th>
                                <th>Balsavo</th>
                                <th>Proc.</th>
                                <th>Mandatai</th>
                            </tr>
                        </thead>
                        <tbody>
                            {partiesResultsList}
                        </tbody>
                    </table>
                </div>
                <h3>Balsavimo rezultatai apygardose</h3>
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
                <DownloadCSVContainer
                    buttonName="Visi daugiamandačių rezultatai"
                    request="2"
                    fileName="Visi daugiamandačių rezultatai.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Esminiai daugiamandačių rezultatai"
                    request="4"
                    fileName="Esminiai daugiamandačių rezultatai.csv" />
                <br />
            </div>
        )
    }
});




window.MultiElectionsResultsComponent = MultiElectionsResultsComponent;