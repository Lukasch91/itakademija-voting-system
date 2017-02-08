var AddDistrictComponent = React.createClass( {
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
                    <label>Apylinkės pavadinimas</label><br />
                    <input className="form-control" type="text" value={this.props.district.title} onChange={this.props.onFieldChange( 'title' )} /><br />

                    <label>Adresas</label><br />
                    <input className="form-control" type="text" value={this.props.district.address} onChange={this.props.onFieldChange( 'address' )} /><br />

                    <label>Rinkėjų skaičius</label><br />
                    <input className="form-control" type="number" value={this.props.district.numberOfVoters} onChange={this.props.onFieldChange( 'numberOfVoters' )} /><br />

                    <button className="btn btn-success" onClick={this.props.onAddClick} >Pridėti</button>
                    <button className="btn btn-danger" onClick={this.props.onCancel} >Atšaukti</button>
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

AddDistrictComponent.propTypes = {
    district: React.PropTypes.object.isRequired,
    onFieldChange: React.PropTypes.func.isRequired,
    onAddClick: React.PropTypes.func.isRequired,
    validationArray: React.PropTypes.array.isRequired
};

window.AddDistrictComponent = AddDistrictComponent;