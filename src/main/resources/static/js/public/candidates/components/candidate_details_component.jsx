var CandidateDetailsComponent = React.createClass( {
    /*
              
                            <th>Gimimo data</th>
                            <th>Daugiau</th>
                            <th>Aprašymas</th>
                            <th>Partija</th>
                            <th>Numeris partijoje</th>
                            <th>Apygarda</th>
                    
                    <tr key={'row' + index}>
                    <td>{candidate.candidateName}</td>
                    <td>{candidate.candidateSurname}</td>
                    <td>{candidate.candidateDateOfBirth}</td>
                    <td><CandidateDetailsComponent candidateProp={candidate}/></td>
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
                
                
                
                
                
                
                
                
                
                
                
                <table className="table table-hover">
                                    <thead>
                                        <tr>
                
                                            <th>Gimimo data</th>
                                            <th>Asmens kodas</th>
                                            <th>Aprašymas</th>
                                            <th>Partija</th>
                                            <th>Numeris partijoje</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                                    </tbody>
                                </table>
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
    */
    
    
    
    
    
    
    
    
    render: function() {
        var self = this;
        var candidate = self.props.candidateProp;
        
        var modalId = "modal" + "c" + candidate.candidatePersonalID;
        var modalIdHash = "#modal" + "c" + candidate.candidatePersonalID;
        
        return (
                <div>
                <button type="button" className="btn btn-xs btn-success" data-toggle="modal" data-target={modalIdHash}>
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

                                <h4 className="modal-title" id="myModalLabel">Kandidatas</h4>
                            </div>

                            <div className="modal-body">

                            <p>Vardas Pavardė</p>
                            <p>{candidate.candidateName} {candidate.candidateSurname}</p>

                            
                            
                            <p>Gimimo data</p>
                            <p>Daugiau</p>
                            <p>Aprašymas</p>
                            <p>Partija</p>
                            <p>Numeris partijoje</p>
                            <p>Apygarda</p>

                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    
    }
});

//CandidateDetailsComponent.propTypes = {
//    candidateProp: React.PropTypes.Object.isRequired
//};


window.CandidateDetailsComponent = CandidateDetailsComponent;