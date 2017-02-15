var AdministrateRepresentativeComponent = React.createClass( {
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
                    <label>Vardas</label><br />
                    <input id="representativeName" className="form-control" type="text" value={this.props.representative.name} onChange={this.props.onFieldChange( 'name' )} /><br />

                    <label>Pavardė</label><br />
                    <input id="representativeSurname" className="form-control" type="text" value={this.props.representative.surname} onChange={this.props.onFieldChange( 'surname' )} /><br />

                    <label>Prisijungimo vardas</label><br />
                    <input id="representativeUsername" className="form-control" type="text" value={this.props.representative.loginName} onChange={this.props.onFieldChange( 'loginName' )} /><br />

                    <label>Slaptažodis </label><button id="generatePassword" className="btn btn-warning" disabled>Generuoti slaptažodį</button><br />
                    <input className="form-control" type="password" value={this.props.representative.password} onChange={this.props.onFieldChange( 'password' )} /><br />


                    <label>El. paštas</label><br />
                    <input id="email" className="form-control" type="email" value={this.props.representative.email} onChange={this.props.onFieldChange( 'email' )} /><br />

                    <button id="addRepresentative" className="btn btn-success" onClick={this.props.onAddRepresentative}>Pridėti</button>
                    <button id="cancelRepresentative" className="btn btn-danger" onClick={this.props.onCancel} >Atšaukti</button>
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

AdministrateRepresentativeComponent.propTypes = {
    representative: React.PropTypes.object.isRequired,
    onFieldChange: React.PropTypes.func.isRequired,
    onAddRepresentative: React.PropTypes.func.isRequired,
    validationArray: React.PropTypes.array.isRequired
};

window.AdministrateRepresentativeComponent = AdministrateRepresentativeComponent;