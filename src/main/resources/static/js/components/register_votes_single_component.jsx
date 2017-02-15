var RegisterVotesSingleComponent = React.createClass( {
    render: function() {
        var self = this;
        var votesList = [];
        var votesEntered = [];

        this.props.elections.map( function( electVotes, index ) {
            votesList.push( electVotes.singleCandidate.candidateID );
            votesEntered.push( electVotes );
        });

        console.log( 'bla' );
        var candidateCount = [];


        var candidatesList = this.props.candidates.map( function( candidate, index ) {
            
            if (candidate.candidateConstituency != null && candidate.candidateConstituency.id == 1) {
        
            candidateCount.push( candidate );

            if ( votesList.includes( candidate.candidateID ) ) {

                for ( var i = 0; i < votesEntered.length; i++ ) {
                    if ( votesEntered[i].singleCandidate.candidateID == candidate.candidateID ) {
                        return (
                            <div key={index}>
                                <label>{candidate.candidateName} {candidate.candidateSurname}</label>
                                <div>Įvestas rezultatas: {votesEntered[i].singleVotes}</div><br />
                            </div> )
                    }
                }


            } else {

                return (
                    <div key={index}>
                        <label>{candidate.candidateName} {candidate.candidateSurname}</label>
                        <VoteFormSingleContainer candidateId={candidate.candidateID} /><br />
                    </div>
                );
            }
            }

        });

        console.log( candidateCount.length );
        var disabled = true;
        if ( candidateCount.length != 0 && votesEntered.length == candidateCount.length && votesEntered[5].singlePublishedDate == null ) {
            disabled = false;
        }

        return (
            <form>
                <h3>Vienamandatės</h3>
                <LoggedInRepresentativeInfoContainer />
                {candidatesList}
                <input id="submitSingle" type="checkbox" disabled /> Patvirtinu, kad įvesti duomenys teisingi.<br />
                <button id="publishSingle" className="btn btn-success" onClick={self.props.onPublishVotes} disabled={disabled}>Publikuoti rezultatus</button>
            </form>
        )
    }
});

RegisterVotesSingleComponent.propTypes = {
    candidates: React.PropTypes.array.isRequired,
    elections: React.PropTypes.array.isRequired
};

window.RegisterVotesSingleComponent = RegisterVotesSingleComponent;