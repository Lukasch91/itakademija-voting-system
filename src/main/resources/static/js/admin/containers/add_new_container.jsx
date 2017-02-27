var AddNewContainer = React.createClass({
    
    
    handleAddNew: function() {
        this.context.router.push(this.props.redirectTo);
    },
    

    render: function() {
        return <AddNewComponent onAddNew={this.handleAddNew} />
    }
});

AddNewContainer.contextTypes = {
        router: React.PropTypes.object.isRequired,
};


window.AddNewContainer = AddNewContainer;