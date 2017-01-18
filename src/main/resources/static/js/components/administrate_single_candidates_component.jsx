var AdministrateSingleCandidatesComponent = React.createClass({
    render: function() {

        var self = this;
        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
          return (
              <tr key={index}>
              <td>{constituency.title}</td>
              
              <td><button type="button" className="btn btn-primary">CSV</button></td>
              </tr>
          );
        });
 
      return (
              <div className="panel panel-default">
              <h4>Vienmandatės</h4>
              <table className="table table-hover">     
              <thead>  
                  <tr>
                      <th>Apygardos pavadinimas</th>
                      
                      <th>Pridėti kandidatus</th>
                  </tr>
              </thead>
              <tbody>
                      {constituencyList}
                  
              </tbody>
              </table>
            </div>
              )
    }
  });

AdministrateSingleCandidatesComponent.propTypes = {
        parties: React.PropTypes.array.isRequired
};


window.AdministrateSingleCandidatesComponent = AdministrateSingleCandidatesComponent;