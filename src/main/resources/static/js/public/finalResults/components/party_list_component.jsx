var PartyListComponent = React.createClass( {

    render: function() {
        var self = this;



        var partiesList = this.props.parties.map( function( party, index ) {
            return (
                <tr key={index}>
                    <td>{party.partyTitle}</td>
                    <td>{party.mandates}</td>
                </tr>
            );
        });



        return (
            <div>
                <div>
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Partija</th>
                                <th>Mandatai</th>

                            </tr>
                        </thead>
                        <tbody>
                            {partiesList}
                        </tbody>
                    </table>
                </div>
                <DownloadCSVContainer
                    buttonName="Partijų mandatų rezultatai"
                    request="6"
                    fileName="Partijų mandatų rezultatai.csv" />
                <br />
            </div>
        )
    }
});



window.PartyListComponent = PartyListComponent;