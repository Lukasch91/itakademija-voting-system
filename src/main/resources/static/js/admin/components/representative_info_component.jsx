var RepresentativeInfoComponent = React.createClass( {
    render: function() {
        return (
            <div className="col-md-5">
                <h4>Apylinkės atstovo informacija</h4>
                <div >
                    <table className="table table-hover">
                        <tbody>
                            <tr>
                                <th className="col-md-4">Apygarda:</th>
                                <td className="col-md-8">{this.props.constituency.title}</td>
                            </tr>
                            <tr>
                                <th>Apylinkė:</th>
                                <td>{this.props.district.title}</td>
                            </tr>
                            <tr>
                                <th>Vardas:</th>
                                <td>{this.props.representative.name}</td>
                            </tr>
                            <tr>
                                <th>Pavardė:</th>
                                <td>{this.props.representative.surname}</td>
                            </tr>
                            <tr>
                                <th>Prisijungimo vardas:</th>
                                <td>{this.props.representative.loginName}</td>
                            </tr>
                            <tr>
                                <th>El. paštas:</th>
                                <td>{this.props.representative.email}</td>
                            </tr>

                        </tbody>
                    </table>
                </div>

                <button id="deleteRepresentative" type="button" className="btn btn-primary btn-warning btn-xs " data-toggle="modal" data-target="#modal">
                    Trinti atstovą
                </button>&nbsp;
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


                <button id="bakcToDistrict" type="button" className="btn btn-danger btn-xs " onClick={this.props.onCancel}>Grįžti</button>
            </div>

        )
    }
});

window.RepresentativeInfoComponent = RepresentativeInfoComponent;