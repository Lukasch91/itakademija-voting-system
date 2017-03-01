var AddPartyComponent = React.createClass( {
    render: function() {
        var self = this;
        var validationArrayList = this.props.validationArray.map( function( object, index ) {
            return (
                <tr className="danger" key={index}>
                    <td>{object.defaultMessage}</td>
                </tr>
            );
        });

        return (
            <div>
                <form>
                    <label>Partijos pavadinimas</label><br />
                    <input id="partyName" className="form-control" type="text" value={this.props.party.title} onChange={this.props.onFieldChange( 'title' )} /><br />

                    <label>Trumpinys</label><br />
                    <input id="partyAbr" className="form-control" type="text" value={this.props.party.party_abbreviation} onChange={this.props.onFieldChange( 'party_abbreviation' )} /><br />

                    <button id="addParty" className="btn btn-success btn-xs " onClick={this.props.onAddClick}>Pridėti</button>
                    <button id="cancelParty" className="btn btn-danger btn-xs " onClick={this.props.onCancel}>Atšaukti</button>
                </form>

                <table className="table table-condensed">
                    <tbody>
                        {validationArrayList}
                    </tbody>
                </table>

            </div>

        )
    }
});

AddPartyComponent.propTypes = {
    party: React.PropTypes.object.isRequired,
    onFieldChange: React.PropTypes.func.isRequired,
    onAddClick: React.PropTypes.func.isRequired,
    validationArray: React.PropTypes.array.isRequired
};

window.AddPartyComponent = AddPartyComponent;