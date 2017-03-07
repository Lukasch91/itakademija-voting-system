var DistrictListComponent = React.createClass( {
    render: function() {

        var self = this;
        var districtList = this.props.districts.map( function( district, index ) {
            var modalId = "modal" + district.id;
            var modalIdHash = "#modal" + district.id;
            var rep_name = "nepriskirta";
            if ( district.representatives.length != 0 ) {
                rep_name = district.representatives[0].name + ' ' + district.representatives[0].surname;

                return (
                    <tr key={index}>
                        <td>{district.title}</td>
                        <td>{district.address}</td>
                        <td>{district.numberOfVoters}</td>
                        <td>{rep_name}</td>
                        <td><button type="button" className="btn btn-success btn-xs " onClick={self.props.onAdministerRepresentative( district )}>Peržiūrėti atstovą</button></td>
                        <td>


                            <div>
                                <button type="button" className="btn btn-primary btn-danger btn-xs " data-toggle="modal" data-target={modalIdHash}>
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
                        <td><button type="button" className="btn btn-primary btn-xs " onClick={self.props.onAddRepresentative( district )}>Pridėti atstovą</button></td>
                        <td>
                            <div>
                                <button type="button" className="btn btn-primary btn-danger btn-xs " data-toggle="modal" data-target={modalIdHash}>
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
            <div>
                <h4>{this.props.constit.title} apygarda</h4>
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