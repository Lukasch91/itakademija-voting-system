var ConstituencyListComponent = React.createClass( {
    render: function() {
        var self = this;
        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
            return (
                <tr key={index}>
                    <td>{constituency.title}</td>
                    <td>{constituency.districts.length}</td>
                    <td><button type="button" className="btn btn-info" onClick={self.props.onAdministerDistricts( constituency )}>Administruoti apylinkes</button></td>
                    <td>
                        <button type="button" className="btn btn-primary btn-danger" data-toggle="modal" data-target="#modal">
                            <span className="glyphicon glyphicon-remove"></span>
                        </button>
                        <div className="modal fade" id="modal" tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                                    </div>
                                    <div className="modal-body">
                                        Ar tikrai norite ištrinti apygardą?
                      </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                                        <button type="button" className="btn btn-danger" onClick={self.props.onRemoveItem( constituency )} data-dismiss="modal">Ištrinti</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            );
        });


        return (
            <div className="panel panel-default">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Apygarda</th>
                            <th>Apylinkės</th>
                            <th></th>
                            <th>Trinti</th>
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