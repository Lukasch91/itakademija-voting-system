var TestVoteFormSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            election: {
                votes: ''
            }
        };
    },


    handleFieldChange: function( fieldName ) {
        var self = this;
        return function( e ) {
            var election = self.state.election;
            election[fieldName] = e.target.value;
            self.setState( { election: election });
        };
    },

    handleVoteClick: function( e ) {
        e.preventDefault();
        axios.post( '/api/singleelection', [{
            singleVotes: this.state.election.votes,
            singleCandidate: { candidateID: this.props.candidateId },
            singleDistrict: { id: '1' }
        }])
            .then( function() {
                
            });
        window.location.reload();

    },

    render: function() {
        var self = this;
        var voteForm = function() {
            return (
                    <div className="row">
                    <div className="input-group col-xs-4">
                    <input
                        className="form-control"
                        id="enterSingleVotes"
                        type="number"
                        value={self.state.election.votes}
                        onChange={self.handleFieldChange( 'votes' )}
                        />
                    <span className="input-group-btn">
                        <button id="saveSingleVotes" className="btn btn-success" onClick={self.handleVoteClick}>Išsaugoti balsus</button>
                    </span>
                </div>
                        </div>
            )
        };

        return (
            <div>
                {voteForm()}
            </div>
        )
    }

});


window.TestVoteFormSingleContainer = TestVoteFormSingleContainer;


   
