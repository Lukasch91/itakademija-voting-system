var AddNewComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <button id="addNew" type="button" className="btn btn-success btn-xs " onClick={this.props.onAddNew}>
                    <span className="glyphicon glyphicon-plus"></span>
                </button>
            </div>
        )
    }
});

window.AddNewComponent = AddNewComponent;