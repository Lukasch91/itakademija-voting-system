var DeleteConfirmationComponent = React.createClass( {
    render: function() {

        var deleteButton = function( deletableItem, itemId ) {
            var modalId = "modal" + itemId;
            var modalIdHash = "#modal" + itemId;
            return (
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
                                    Ar tikrai norite ištrinti apygardą? ({modalId})
                                    </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                                    <button type="button" className="btn btn-xs btn-danger" onClick={deletableItem} data-dismiss="modal">Ištrinti</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        };

        return (
            <div>

                {deleteButton( this.props.deletion, this.props.itemId )}
            </div>

        )
    }
});

window.DeleteConfirmationComponent = DeleteConfirmationComponent;