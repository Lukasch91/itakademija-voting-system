var ViewCandidatesByPartyComponent = React.createClass( {

    getInitialState: function() {
        return {
            candidates: []
        };
    },
    
    handleInitiateData: function() {
        var self = this;
        if(self.state.candidates.length == 0) {
            var partyId = this.props.party.id;
            axios.get('api/ADMIN/candidateParty/' + partyId)    
            .then(function (response) {
     
                console.log(response.data);
                
                self.setState({ 
                    candidates: response.data
                });
            });            
        }
    },
    
    handleRemoveItem: function() { 
        
        console.log("delete");
        
        var self = this;
        var partyId = this.props.party.id;
        
        axios.delete('/api/ADMIN/candidateParty/'+ partyId)
        .then(function(response) { 
            console.log("deletedddddd");
            self.setState({ 
                candidates: []
            });
            
        });
        window.location.reload();//!!!!!!!!improve, initialize AxiosGet
    },
    
    render: function() {

        var modalId = "modal" + 1 + this.props.party.id;
        var modalIdHash = "#modal" + 1 + this.props.party.id;
        var self = this;
           
        var candidateList = self.state.candidates.map( function( candidate, index ) {
            return (
                    <tr key={index}>
                        <td>{candidate.candidateName}</td>
                        <td>{candidate.candidateSurname}</td>
                        <td>{candidate.candidateDateOfBirth}</td>
                        <td>{candidate.candidatePersonalID}</td>
                        <td>{candidate.candidateDescription}</td>
                        <td>{candidate.candidateNumberInParty}</td>
  <td>
  {candidate.candidateConstituency != null ? candidate.candidateConstituency.title : '-'}
  </td>
                    </tr>
                );
        });

        return (

            <div>
                <button type="button" className="btn btn-success btn-xs " data-toggle="modal" data-target={modalIdHash} onClick={this.handleInitiateData}>
                    Peržiurėti kandidatus
                    </button>

                <div className="modal fade" id={modalId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">

                                <button type="button" className="close" data-dismiss="modal">
                                    <span aria-hidden="true">&times;</span>
                                    <span className="sr-only">Close</span>
                                </button>

                                <h4 className="modal-title" id="myModalLabel">{this.props.party.title} - kandidatai</h4>
                            </div>

                            <div className="modal-body">
                                
                                <table className="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Vardas</th>
                                        <th>Pavardė</th>
                                        <th>Gimimo data</th>
                                        <th>Asmens kodas</th>
                                        <th>Aprašymas</th>
                                        <th>Numeris partijoje</th>
                                        <th>Apygarda</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {candidateList}
                                </tbody>
                            </table>
                                
                                
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-danger" onClick={this.handleRemoveItem} data-dismiss="modal">Ištrinti kandidatus</button>
                                <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});




window.ViewCandidatesByPartyComponent = ViewCandidatesByPartyComponent;
