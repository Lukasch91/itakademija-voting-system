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