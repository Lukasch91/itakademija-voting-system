var CandidateSearchContainer = React.createClass( {
    
    getInitialState: function() {
        return {
            candidates: [],
            candidatesBackup: [],
            candidatesAmount: 0
        };
    },
    
    componentWillMount: function() {
        var self = this;
        
        axios.get( '/api/PUBLIC/candidate/' )
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
        } else if( (candidate.candidateParty != null ? candidate.candidateParty.title : '-').toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( ((candidate.candidateParty != null ? candidate.candidateParty.party_abbreviation : '-').toLowerCase()).indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        } else if( (candidate.candidateConstituency != null ? candidate.candidateConstituency.title : '-').toLowerCase().indexOf(comparable.toLowerCase()) >=0 ) {
            matches = true;
        }
        
      return matches;
    },
    
    handleSearchChange: function( event ) {
      var self = this;
      
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
                    
                    <td>
                    {candidate.candidateParty != null ? candidate.candidateParty.title : '-'}
                    </td>
                    <td>
                    {candidate.candidateParty != null ? candidate.candidateParty.party_abbreviation : '-'}
                    </td>
                    <td>
                    {candidate.candidateConstituency != null ? candidate.candidateConstituency.title : '-'}
                    </td>
                    <td><CandidateDetailsComponent candidateProp={candidate}/></td>
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
                            <th>Partija</th>
                            <th>Trumpinys</th>
                            <th>Apygarda</th>
                            <th>Daugiau</th>
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

window.CandidateSearchContainer = CandidateSearchContainer;

