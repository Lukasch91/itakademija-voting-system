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

                  
                    
                    <label>El. paštas</label><br />
                    <input id="email" className="form-control" type="email"  value={this.props.representative.email} onChange={this.props.onFieldChange( 'email' )} /><br />

                    <button id="addRepresentative" type="button" className="btn btn-success btn-xs " data-toggle="modal" data-target="#modalSave"  >Pridėti</button>&nbsp;
                    
                    <div className="modal fade" id="modalSave" tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                            </div>
                            <div className="modal-body">
                                <p><b>Prieš pridėdami naują vartotoją įsitikinkite, kad duomenys teisingi:</b></p>
                                <p>Vardas: {this.props.representative.name}</p>
                                <p>Pavardė: {this.props.representative.surname}</p>
                                <p>Prisijungimo vardas: {this.props.representative.loginName}</p>
                                <p>El. paštas: {this.props.representative.email}</p>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                <button type="button" className="btn btn-xs btn-danger" onClick={this.props.onAddRepresentative} data-dismiss="modal">Pridėti atstovą</button>
                                
                            </div>
                        </div>
                    </div>
                </div>
                    
                    
                    
                    
                    
                    <button id="cancelRepresentative" type="button" className="btn btn-danger btn-xs " onClick={this.props.onCancel} >Atšaukti</button>
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

/*AdministrateRepresentativeComponent.propTypes = {
    representative: React.PropTypes.object.isRequired,
    onFieldChange: React.PropTypes.func.isRequired,
    onAddRepresentative: React.PropTypes.func.isRequired,
    validationArray: React.PropTypes.array.isRequired
};*/

window.AdministrateRepresentativeComponent = AdministrateRepresentativeComponent;