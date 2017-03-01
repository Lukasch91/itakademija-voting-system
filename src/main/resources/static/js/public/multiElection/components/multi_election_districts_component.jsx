var MultiElectionsDistrictResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var distrResultsList = this.props.districts.map( function( district, index ) {
            return (
                <tr key={index}>
                    <td>{district.title}</td>
                    <td>{district.numberOfVoters}</td>
                    <td>{district.numberOfVotersWhoVoted}</td>
                    <td>{district.percentageOfVoted} %</td>
                    <td>{district.invalidVotes}</td>
                    <td>{district.percentageOfInvalidVotes} %</td>
                    <td>{district.validVotes}</td>
                    <td>{district.percentageOfValidVotes} %</td>
                    <td><button type="button" className="btn btn-xs btn-info" onClick={self.props.onAdministerDistricts(district.id)}>Apylinkės rezultatai</button></td>
                </tr>
            );
        });

        var partiesResultList = this.props.parties.map( function( party, index ) {

            return (
                <tr key={index}>
                    <td>{party.id}</td>
                    <td>{party.partyTitle}</td>
                    <td>{party.votes}</td>
                    <td>{party.percentageOfAllVotes}%</td>
                </tr>
            );
        });


        return (
            <div>

                <h3><label> Balsavimo rezultatai {this.props.constituency.title} apygardoje</label></h3><br />
                <h7>
                    Apylinkių skaičius - {this.props.constituency.numberOfDistricts}<br />
                    Pagal gautus iš apylinkių duomenis:<br />
                    rinkėjų sąraše įrašyta rinkėjų – {this.props.constituency.numberOfVoters} ,<br />
                    rinkimuose dalyvavo – {this.props.constituency.numberOfVotersWhoVote} ({this.props.constituency.percentageOfVotersWhoVote} %)<br />
                    negaliojančių biuletenių – {this.props.constituency.numberOfInvalidVotes} ({this.props.constituency.percentageOfInvalidVotes} %),<br />
                    galiojančių biuletenių – {this.props.constituency.numberOfValidVotes} ({this.props.constituency.percentageOfValidVotes} %).<br />
                </h7>
                <div className="panel panel-default">
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Reg. nr.</th>
                                <th>Pavadinimas</th>
                                <th>Balsai</th>
                                <th>Proc.</th>
                            </tr>
                        </thead>
                        <tbody>
                            {partiesResultList}
                        </tbody>
                    </table>
                </div>

                <h3>Balsavimo rezultatai rinkimų apylinkėse</h3>

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
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {distrResultsList}
                        </tbody>
                    </table>
                </div>
                <button id="bakcToConstituencyResults" className="btn btn-xs btn-warning" onClick={this.props.onCancel}>Grįžti</button>
            </div>

        )
    }
});



window.MultiElectionsDistrictResultsComponent = MultiElectionsDistrictResultsComponent;