var AddConstituencyComponent = React.createClass( {
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
                    <label>Apygardos pavadinimas</label><br />
                    <input id="constituencyName" className="form-control" type="text" value={this.props.constituency.title} onChange={this.props.onFieldChange( 'title' )} name="input1" /><br />
                    <button id="addConstituency" className="btn btn-success btn-xs " onClick={this.props.onAddClick}>Pridėti</button>
                    <button id="cancelConstituency" className="btn btn-danger btn-xs " onClick={this.props.onCancel}>Atšaukti</button>
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

AddConstituencyComponent.propTypes = {
    constituency: React.PropTypes.object.isRequired,
    onFieldChange: React.PropTypes.func.isRequired,
    onAddClick: React.PropTypes.func.isRequired,
    validationArray: React.PropTypes.array.isRequired
};

window.AddConstituencyComponent = AddConstituencyComponent;