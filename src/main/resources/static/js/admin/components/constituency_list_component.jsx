var ConstituencyListComponent = React.createClass( {
    render: function() {
        var self = this;
        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
            var modalId = "modal" + constituency.id;
            var modalIdHash = "#modal" + constituency.id;
            return (
                <tr key={index}>
                    <td className="col-md-4">{constituency.title}</td>
                    <td className="col-md-1">{constituency.districts.length}</td>
                    <td className="col-md-2"><button type="button" className="btn btn-primary btn-xs " onClick={self.props.onAdministerDistricts( constituency )}>Administruoti apylinkes</button></td>
                    <td className="col-md-1">
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
                                            Ar tikrai norite ištrinti {constituency.title} apygardą?
                                    </div>
                                        <div className="modal-footer">
                                            <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                            <button type="button" className="btn btn-xs btn-danger" onClick={self.props.onRemoveItem( constituency )} data-dismiss="modal">Ištrinti</button>
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
            <div >
                <table className="table ">
                    <thead>
                        <tr>
                            <th className="col-md-4">Apygarda</th>
                            <th className="col-md-1">Apylinkės</th>
                            <th className="col-md-2"></th>
                            <th className="col-md-1">Trinti</th>
                        </tr>
                    </thead>
                    <tbody>
                        {constituencyList}
                    </tbody>
                </table>
            </div>

        )
    }
});

ConstituencyListComponent.propTypes = {
    constituencies: React.PropTypes.array.isRequired
};

window.ConstituencyListComponent = ConstituencyListComponent;
