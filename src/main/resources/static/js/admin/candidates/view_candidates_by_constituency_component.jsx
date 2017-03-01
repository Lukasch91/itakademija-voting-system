var ViewCandidatesByConstituencyComponent = React.createClass( {

    getInitialState: function() {
        return {
            candidates: []
        };
    },
    
    handleInitiateData: function() {
        var self = this;
        if(self.state.candidates.length == 0) {
            var conId = this.props.constituency.id;
            axios.get('api/ADMIN/candidateConstituency/' + conId)    
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
        var conId = this.props.constituency.id;
        
        axios.delete('/api/ADMIN/candidateConstituency/'+ conId)
        .then(function(response) { 
            console.log("deletedddddd");
            self.setState({ 
                candidates: []
            });
            
        });
        window.location.reload();//!!!!!!!!improve, initialize AxiosGet
    },
    
    render: function() {

        var modalId = "modal" + 1 + this.props.constituency.id;
        var modalIdHash = "#modal" + 1 + this.props.constituency.id;
        var self = this;
           
        var candidateList = self.state.candidates.map( function( candidate, index ) {
            return (
                    <tr key={index}>
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
                    </tr>
                );
        });

        return (

            <div>
                <button type="button" className="btn btn-xs btn-primary btn-danger" data-toggle="modal" data-target={modalIdHash} onClick={this.handleInitiateData}>
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

                                <h4 className="modal-title" id="myModalLabel">{this.props.constituency.title} apygardos kandidatai</h4>
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
                                        <th>Partija</th>
                                        <th>Numeris partijoje</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {candidateList}
                                </tbody>
                            </table>
                                
                                
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-xs btn-danger" onClick={this.handleRemoveItem} data-dismiss="modal">Ištrinti kandidatus</button>
                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});




window.ViewCandidatesByConstituencyComponent = ViewCandidatesByConstituencyComponent;
