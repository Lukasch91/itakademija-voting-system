var MultiElectionsOneDistrictResultsComponent = React.createClass( {
    render: function() {
        var self = this;
        var info = this.props.info;
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

                Puslapis atnaujintas {info.updateDate}

                <h3>{info.constituencyTitle} rinkimų apygarda</h3>
                <h4>{info.districtTitle} rinkimų apylinkė</h4>
                Pagal gautus iš apylinkių duomenis: <br />
                rinkėjų sąraše įrašyta rinkėjų – {info.voters} ,<br /> 
                rinkimuose dalyvavo – {info.allVotes} ({info.percentageOfVoted} %), <br />
                negaliojančių biuletenių – {info.invalidVotes} ({info.percentageOfInvalidVotes} %),<br />
                galiojančių biuletenių – {info.validVotes} ({info.percentageOfValidVotes} %).

                <div>
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