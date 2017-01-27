var AdministrateRepresentativeComponent = React.createClass({
    render: function() {
        return (
                <form>         
                <label>Vardas</label><br />
                <input className="form-control" type="text"   value={this.props.representative.name} onChange={this.props.onFieldChange('name')}/><br />
                
                <label>Pavardė</label><br />
                <input className="form-control" type="text"   value={this.props.representative.surname} onChange={this.props.onFieldChange('surname')}/><br />
                
                <label>Prisijungimo vardas</label><br />
                <input className="form-control" type="text"   value={this.props.representative.loginName} onChange={this.props.onFieldChange('loginName')}/><br />
                
                <label>Slaptažodis </label><button className="btn btn-warning" >Generuoti slaptažodį</button><br />
                <input className="form-control" type="password"   value={this.props.representative.password} onChange={this.props.onFieldChange('password')}/><br />
                
                
                <label>El. paštas</label><br />
                <input className="form-control" type="email"   value={this.props.representative.email} onChange={this.props.onFieldChange('email')}/><br />
                
                <button className="btn btn-success" onClick={this.props.onAddRepresentative}>Pridėti</button>
                <button className="btn btn-danger" onClick={this.props.onCancel} >Atšaukti</button>
                </form>
                
        
        )
    }
});

AdministrateRepresentativeComponent.propTypes = {
        representative: React.PropTypes.object.isRequired,
        onFieldChange: React.PropTypes.func.isRequired,
        onAddRepresentative: React.PropTypes.func.isRequired
};

window.AdministrateRepresentativeComponent = AdministrateRepresentativeComponent;