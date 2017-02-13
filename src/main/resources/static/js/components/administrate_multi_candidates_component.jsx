var AdministrateMultiCandidatesComponent = React.createClass( {
    render: function() {

        var self = this;
        var partyList = this.props.parties.map( function( party, index ) {
            return (
                <tr key={index}>
                    <td>{party.title}</td>
                    <td>{party.party_abbreviation}</td>
                    <td>
                        <AdministrateCandidatesCSVPartyComponent partyId={party.id} />
                    </td>
                </tr>
            );
        });

        return (
            <div className="panel panel-default">
                <h4>Daugiamandatės</h4>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Partijos pavadinimas</th>
                            <th>Trumpinys</th>
                            <th>Pridėti kandidatus</th>
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