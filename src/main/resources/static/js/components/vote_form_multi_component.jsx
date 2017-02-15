var VoteFormMultiComponent = React.createClass( {
    render: function() {
        var self = this;
        var validationArrayList = this.props.validationArray.map( function( object, index ) {
            return (
                <tr className="danger" key={index}>
                    <td>{object.defaultMessage}</td>
                </tr>
            );
        });
        var voteForm = function() {
            return (
                <div>
                    <div className="row">
                        <div className="input-group col-xs-4">
                            <input
                                className="form-control"
                                id="enterMultiVotes"
                                type="number"
                                value={self.props.election.votes}
                                onChange={self.props.onFieldChange( 'votes' )}
                                />
                            <span className="input-group-btn">
                                <button id="saveMultiVotes" className="btn btn-success" onClick={self.props.onVoteClick}>Išsaugoti balsus</button>
                            </span>
                        </div>
                    </div>
                    <table className="table table-condensed">
                        <tbody>
                            {validationArrayList}
                        </tbody>
                    </table>
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



window.VoteFormMultiComponent = VoteFormMultiComponent;