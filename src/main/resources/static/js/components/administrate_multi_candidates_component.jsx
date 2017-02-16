var AdministrateMultiCandidatesComponent = React.createClass( {
    
    render: function() {
        var self = this;
        console.log( "_________________" )
        var partyList = this.props.parties.map( function( party, index ) {

            if ( party.numberOfCandidatesInParty != 0 ) {
                return (
                    <tr key={index}>
                        <td>{party.title}</td>
                        <td>{party.party_abbreviation}</td>
                        <td>
                            <ViewCandidatesByPartyComponent party={party} />
                        </td>
                    </tr>
                );
            } else {
                return (
                    <tr key={index}>
                        <td>{party.title}</td>
                        <td>{party.party_abbreviation}</td>
                        <td>
                            <AdministrateCandidatesCSVPartyComponent partyId={party.id} />
                        </td>
                    </tr>
                );
            }
        });

        return (
            <div className="panel panel-default">
                <h4>Daugiamandatės</h4>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Partijos pavadinimas</th>
                            <th>Trumpinys</th>
                            <th>Veiksmai</th>
                        </tr>
                    </thead>
                    <tbody>
                        {partyList}
                    </tbody>
                </table>
            </div>
        )
    }
});

AdministrateMultiCandidatesComponent.propTypes = {
    parties: React.PropTypes.array.isRequired
};


window.AdministrateMultiCandidatesComponent = AdministrateMultiCandidatesComponent;