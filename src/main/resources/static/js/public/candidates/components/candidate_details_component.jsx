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

                                <h4 className="modal-title" id="myModalLabel">{candidate.candidateName} {candidate.candidateSurname}</h4>
                            </div>

                            <div className="modal-body">
                                <div className="container-fluid">
                                    <div className="row">

                                        <div className="col-md-6">
                                            <p><b>Partija: </b>{candidate.candidateParty != null ? (candidate.candidateParty.title ) : "-"}</p>
                                            <p><b>Numeris partijoje: </b>{candidate.candidateNumberInParty != null ? (candidate.candidateNumberInParty ) : "-"}</p>
                                            <p><b>Vienmandatė apygarda: </b>{candidate.candidateConstituency != null ? (candidate.candidateConstituency.title ) : "-"}</p>
                                            <br />

                                            <p><b>Gimimo data: </b>{candidate.candidateDateOfBirth}</p>
                                            <p><b>Aprašymas</b></p>
                                            <p>{candidate.candidateDescription}</p>
                                            
                                            
                                            
                                            
                                            
                                            
                                            
                                        </div>

                                        <div className="col-md-6 col-md-offset-6" style={{marginLeft: '0'}}>
                                            <img style={{ height: '250px' }} src="images/profile_photo.png" alt="profile photo" />
                                        </div>

                                    </div>
                                </div>
                            </div>



                            <div className="modal-footer">
                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Uždaryti</button>
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