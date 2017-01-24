var DistrictListComponent = React.createClass({
    render: function() {     

       var self = this;
        var districtList = this.props.districts.map( function( district, index ) {
            var rep_name = "nepriskirta";
            if (district.representatives.length != 0) {
                rep_name = district.representatives[0].name;
                
                return (
                <tr key={index}>
                <td>{district.title}</td>
                <td>{district.address}</td>
                <td>{district.numberOfVoters}</td>
                <td>{rep_name}</td>
                <td><button type="button" className="btn btn-primary" onClick={self.props.onAdministerRepresentative(district)}>Administruoti atstovą</button></td>
                <td>
                <button type="button" className="btn btn-default" onClick={self.props.onRemoveItem(district)}>
                <span className="glyphicon glyphicon-remove" ></span>
                </button></td>
                </tr>    
                )
            } else {
          return (
              <tr key={index}>
              <td>{district.title}</td>
              <td>{district.address}</td>
              <td>{district.voters}</td>
              <td>{rep_name}</td>
              <td><button type="button" className="btn btn-primary" onClick={self.props.onAddRepresentative(district)}>Pridėti atstovą</button></td>
              <td>
              <button type="button" className="btn btn-default" onClick={self.props.onRemoveItem(district)}>
              <span className="glyphicon glyphicon-remove"></span>
              </button></td>
              </tr>
          );
            }
        });

      return (
              <div className="panel panel-default">
              <h3>{this.props.constit.title}</h3>
              <table className="table">     
              <thead>  
              <tr>
                  <th>Apylinkės pavadinimas</th>
                  <th>Adresas</th>
                  <th>Rinkėjų skaičius</th>
                  <th>Atstovas</th>
                  <th></th>
                  <th>Trinti</th>
                </tr>
                  </thead>
                  <tbody>
                  {districtList}
                  </tbody>
              </table>
                  
            </div>
              )
    }
});




window.DistrictListComponent = DistrictListComponent;