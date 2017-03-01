var PartyListComponent = React.createClass( {
    render: function() {
        var self = this;
        var partyList = this.props.parties.map( function( party, index ) {
            var modalId = "modal" + party.id;
            var modalIdHash = "#modal" + party.id;
            return (
                <tr key={index}>
                    <td>{party.title}</td>
                    <td>{party.party_abbreviation}</td>
                    <td>
                        <div>
                            <button type="button" className="btn btn-xs btn-primary btn-danger" data-toggle="modal" data-target={modalIdHash}>
                                <span className="glyphicon glyphicon-remove"></span>
                            </button>
                            <div className="modal fade" id={modalId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div className="modal-dialog">
                                    <div className="modal-content">
                                        <div className="modal-header">
                                            <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                            <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                                        </div>
                                        <div className="modal-body">
                                            Ar tikrai norite ištrinti partiją - "{party.title}"?
                            </div>
                                        <div className="modal-footer">
                                            <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                            <button type="button" className="btn btn-xs btn-danger" onClick={self.props.onRemoveItem( party )} data-dismiss="modal">Ištrinti</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            );
        });


        return (
            <div className="panel panel-default">
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Partijos pavadinimas</th>
                            <th>Trumpinys</th>
                            <th>Trinti</th>
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

ConstituencyListComponent.propTypes = {
    parties: React.PropTypes.array.isRequired
};

window.PartyListComponent = PartyListComponent;