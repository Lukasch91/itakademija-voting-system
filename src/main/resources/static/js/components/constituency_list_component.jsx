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
                        
                    <button type="button" className="btn btn-danger" onClick={self.props.onRemoveItem( constituency )} data-dismiss="modal">
                        <span className="glyphicon glyphicon-remove"></span>
                    </button>
                    
                    </td>
                </tr>
            );
        });
        /*  <DeleteConfirmationComponent deletion={self.props.onRemoveItem( constituency )} /> */
      
        
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
