var CandidateDetailsComponent = React.createClass( {
    
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

                                <h4 className="modal-title" id="myModalLabel">Kandidatas {candidate.candidateName} {candidate.candidateSurname}</h4>
                            </div>

                            <div className="modal-body">
                            <p>{candidate.candidateParty != null ? ("Priklauso partijai - " + candidate.candidateParty.title) : null}</p>
                            <p>{candidate.candidateNumberInParty != null ? ("Numeris partijoje: "+ candidate.candidateNumberInParty) : null}</p>
                            <p>{candidate.candidateConstituency != null ? ("Priklauso vienmandatei " + candidate.candidateConstituency.title +" apygardai") : null}</p>
                            <br/>
                            
                            <p>Aprašymas</p>
                            <p>{candidate.candidateDescription}</p>
                            <p>Gimimo data: {candidate.candidateDateOfBirth}</p>
                            
                            


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