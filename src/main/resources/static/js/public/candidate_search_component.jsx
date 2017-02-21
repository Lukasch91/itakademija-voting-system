var CandidateSearchComponen = React.createClass( {
    
    getInitialState: function() {
        return {
            candidates: [],
            candidatesBackup: [],
            candidatesAmount: 0
        };
    },
    
    componentWillMount: function() {
        var self = this;
        
        axios.get( '/api/candidate/' )
            .then( function( response ) {
                self.setState( { candidates: response.data, candidatesBackup: response.data, candidatesAmount: response.data.length });
            });
    },
    
    handleCandidateComparison: function(candidate, comparable) {
        var matches = false;
        
        if( candidate.candidateName.toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( candidate.candidateName.toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( candidate.candidateSurname.toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( candidate.candidateDateOfBirth.toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( candidate.candidatePersonalID.toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( candidate.candidateDescription.toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( (candidate.candidateParty != null ? candidate.candidateParty.title : '-').toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( ((candidate.candidateNumberInParty != null ? candidate.candidateNumberInParty : '-').toString()).indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( (candidate.candidateConstituency != null ? candidate.candidateConstituency.title : '-').toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        }
        
      return matches;
    },
    
    handleSearchChange: function( event ) {
      var self = this;
      
      //binding
      //http://stackoverflow.com/questions/27397266/onclick-event-binding-in-react-js
      
      var originalCandidates = self.state.candidatesBackup;
      var foundCandidates = [];
      for(var i=0; i < originalCandidates.length; i++) {
          if( self.handleCandidateComparison(originalCandidates[i], event.target.value) ){
              foundCandidates.push(originalCandidates[i])
          }
      }
      self.setState( { candidates: foundCandidates, candidatesAmount: foundCandidates.length });
    },

    render: function() {
        var self = this;
        
        var candidatesList = this.state.candidates.map( function( candidate, index ) {
            return (
                <tr key={'row' + index}>
                    <td>{candidate.candidateName}</td>
                    <td>{candidate.candidateSurname}</td>
                    <td>{candidate.candidateDateOfBirth}</td>
                    <td>{candidate.candidatePersonalID}</td>
                    <td>{candidate.candidateDescription}</td>
                    <td>
                    {candidate.candidateParty != null ? candidate.candidateParty.title : '-'}
                    </td>
                    <td>
                    {candidate.candidateNumberInParty != null ? candidate.candidateNumberInParty : '-'}
                    </td>
                    <td>
                    {candidate.candidateConstituency != null ? candidate.candidateConstituency.title : '-'}
                    </td>
                </tr>                 
            );
        });

        
        return (
            <div>          
                <label>Rodoma kandidatų: { this.state.candidatesAmount }</label>
                <input id="search" 
                         type="text" 
                         className="form-control" 
                         placeholder="Paieška"
                  onChange={(event)=>self.handleSearchChange( event )} />
                
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Vardas</th>
                            <th>Pavardė</th>
                            <th>Gimimo data</th>
                            <th>Asmens kodas</th>
                            <th>Aprašymas</th>
                            <th>Partija</th>
                            <th>Numeris partijoje</th>
                            <th>Apygarda</th>
                        </tr>
                    </thead>
                        <tbody>
                            {candidatesList}
                        </tbody>
                </table>      
            </div>

        )
    }
});

window.CandidateSearchComponen = CandidateSearchComponen;

