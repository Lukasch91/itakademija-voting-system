var RegisterVotesSingleComponent = React.createClass({
    render: function() {    
        var self = this;
        var candidatesList = this.props.candidates.map( function( candidate, index ) {
          return (
                  <div key={index}>
                  <label>{candidate.candidateName} {candidate.candidateSurname}</label>
                  <input className="form-control" type="number" /><br />
                  </div>
          );
        });
        
        return (
                <form>         
                <h3>Vienamandatės</h3>
                <h4>Apygarda: ...</h4>
                <h4>Apylinkė: ...</h4><br />
                {candidatesList}            
                <input type="checkbox" /> Patvirtinu, kad įvesti duomenys teisingi.<br />              
                <button className="btn btn-success" >Pateikti rezultatus</button>
                <button className="btn btn-danger">Atšaukti</button>
                </form>      
        )
    }
});



window.RegisterVotesSingleComponent = RegisterVotesSingleComponent;