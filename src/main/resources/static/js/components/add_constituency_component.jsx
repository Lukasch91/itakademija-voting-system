var AddConstituencyComponent = React.createClass({
    render: function() {
        return (

                <form>         
                <label>Apygardos pavadinimas</label><br />
                <input className="form-control" type="text" value={this.props.constituency.title} onChange={this.props.onFieldChange('title')} name="input1"/><br />
                <button className="btn btn-success" onClick={this.props.onAddClick}>Pridėti</button>
                <button className="btn btn-danger" onClick={this.props.onCancel}>Atšaukti</button>
                </form>
               
        )
    }
});

AddConstituencyComponent.propTypes = {
        constituency: React.PropTypes.object.isRequired,
        onFieldChange: React.PropTypes.func.isRequired,
        onAddClick: React.PropTypes.func.isRequired
};

window.AddConstituencyComponent = AddConstituencyComponent;