var AddNewComponent = React.createClass({
    render: function() {
        return <button type="button" className="btn btn-success btn-xs " onClick={this.props.onAddNew}><span className="glyphicon glyphicon-plus"></span></button>
    }
});

window.AddNewComponent = AddNewComponent;