var RepresentativeInfoComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <p>Vardas: {this.props.representative.name}</p>
                <p>Pavardė: {this.props.representative.surname}</p>
                <p>Prisijungimo vardas: {this.props.representative.loginName}</p>
                <p>El. paštas: {this.props.representative.email}</p>
                

                <button id="deleteRepresentative" type="button" className="btn btn-primary btn-danger btn-xs " data-toggle="modal" data-target="#modal">
                    Trinti atstovą
                </button>
                <div className="modal fade" id="modal" tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                            </div>
                            <div className="modal-body">
                                Ar tikrai norite ištrinti atstovą?
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                <button type="button" className="btn btn-xs btn-danger" onClick={this.props.onDelete} data-dismiss="modal">Ištrinti</button>
                            </div>
                        </div>
                    </div>
                </div>


                <button id="bakcToDistrict" className="btn btn-warning btn-xs " onClick={this.props.onCancel}>Grįžti</button>
            </div>

        )
    }
});

window.RepresentativeInfoComponent = RepresentativeInfoComponent;