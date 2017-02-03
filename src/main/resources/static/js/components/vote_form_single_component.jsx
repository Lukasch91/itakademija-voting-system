var VoteFormSingleComponent = React.createClass( {
    render: function() {
        var self = this;
        var voteForm = function() {
            return (
                    <div className="row">
                    <div className="input-group col-xs-4">
                    <input
                        className="form-control"
                        
                        type="number"
                        value={self.props.election.votes}
                        onChange={self.props.onFieldChange( 'votes' )}
                        />
                    <span className="input-group-btn">
                        <button className="btn btn-success" onClick={self.props.onVoteClick}>Išsaugoti balsus</button>
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

window.VoteFormSingleComponent = VoteFormSingleComponent;