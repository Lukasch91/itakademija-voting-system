var AddNewComponent = React.createClass({
    render: function() {
        return <button type="button" className="btn btn-success" onClick={this.props.onAddNew}>Add New</button>
    }
});

window.AddNewComponent = AddNewComponent;