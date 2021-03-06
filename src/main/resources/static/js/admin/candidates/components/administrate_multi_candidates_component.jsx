var AdministrateMultiCandidatesComponent = React.createClass( {
    
    reload2: function() {
      console.log("reload2");
      this.props.reload3();
    },
    
    render: function() {
        var self = this;
        var partyList = this.props.parties.map( function( party, index ) {

            if ( party.numberOfCandidatesInParty != 0 ) {
                return (
                    <tr key={index}>
                        <td>{party.title}</td>
                        <td>{party.party_abbreviation}</td>
                        <td>
                            <ViewCandidatesByPartyComponent 
                            reload1={self.reload2}
                            party={party} />
                        </td>
                    </tr>
                );
            } else {
                return (
                    <tr key={index}>
                        <td>{party.title}</td>
                        <td>{party.party_abbreviation}</td>
                        <td>
                            <AdministrateCandidatesCSVPartyComponent 
                            reload1={self.reload2} 
                            party={party} />
                        </td>
                    </tr>
                );
            }
        });

        return (
            <div>
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