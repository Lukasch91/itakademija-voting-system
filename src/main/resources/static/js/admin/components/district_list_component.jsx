var DistrictListComponent = React.createClass( {
    render: function() {

        var self = this;
        var districtList = this.props.districts.map( function( district, index ) {
            var modalId = "modal" + district.id;
            var modalIdHash = "#modal" + district.id;
            var rep_name = "nepriskirta";
            if ( district.representatives.length != 0 ) {
                rep_name = district.representatives[0].name;

                return (
                    <tr key={index}>
                        <td>{district.title}</td>
                        <td>{district.address}</td>
                        <td>{district.numberOfVoters}</td>
                        <td>{rep_name}</td>
                        <td><button type="button" className="btn btn-xs btn-primary" onClick={self.props.onAdministerRepresentative( district )}>Administruoti atstovą</button></td>
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
                                                Ar tikrai norite ištrinti {district.title} apylinkę?
                        </div>
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                                <button type="button" className="btn btn-xs btn-danger" onClick={self.props.onRemoveItem( district )} data-dismiss="modal">Ištrinti</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>





                        </td>
                    </tr>
                )
            } else {
                return (
                    <tr key={index}>
                        <td>{district.title}</td>
                        <td>{district.address}</td>
                        <td>{district.numberOfVoters}</td>
                        <td>{rep_name}</td>
                        <td><button type="button" className="btn btn-xs btn-primary" onClick={self.props.onAddRepresentative( district )}>Pridėti atstovą</button></td>
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
                                                Ar tikrai norite ištrinti {district.title} apylinkę?
      </div>
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                                <button type="button" className="btn btn-xs btn-danger" onClick={self.props.onRemoveItem( district )} data-dismiss="modal">Ištrinti</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>
                    </tr>
                );
            }
        });
        /* <DeleteConfirmationComponent deletion={self.props.onRemoveItem( district )} /> */


        return (
            <div className="panel panel-default">
                <h3>{this.props.constit.title}</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Apylinkės pavadinimas</th>
                            <th>Adresas</th>
                            <th>Rinkėjų skaičius</th>
                            <th>Atstovas</th>
                            <th></th>
                            <th>Trinti</th>
                        </tr>
                    </thead>
                    <tbody>
                        {districtList}
                    </tbody>
                </table>

            </div>
        )
    }
});




window.DistrictListComponent = DistrictListComponent;