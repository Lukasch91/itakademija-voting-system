var AddNewComponent = React.createClass({
    render: function() {
        return <button type="button" className="btn btn-success" onClick={this.props.onAddNew}>Pridėti</button>
    }
});

window.AddNewComponent = AddNewComponent;