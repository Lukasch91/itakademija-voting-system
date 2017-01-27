var RepresentativeInfoComponent = React.createClass({
    render: function() {
        return (
                <div>
                <p>Vardas: {this.props.representative.name}</p>
                <p>Pavardė: {this.props.representative.surname}</p>
                <p>Prisijungimo vardas: {this.props.representative.loginName}</p>
                <p>Slaptažodis: {this.props.representative.password}</p>
                <p>El. paštas: {this.props.representative.email}</p>
                <button className="btn btn-success" >Redaguoti</button>
                <button className="btn btn-info">Siųsti prisijungimus atstovui</button>
                <button className="btn btn-danger" onClick={this.props.onDelete}>Trinti atstovą</button>
                <button className="btn btn-warning" onClick={this.props.onCancel}>Grįžti</button>
                </div>
        
        )
    }
});

window.RepresentativeInfoComponent = RepresentativeInfoComponent;