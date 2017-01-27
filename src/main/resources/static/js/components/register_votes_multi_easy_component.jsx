var RegisterVotesMultiEasyComponent = React.createClass( {
    render: function() {
        var self = this;
        var partyList = this.props.parties.map( function( party, index ) {
            return (
                <form key={index}>    
                <div>
                    <label>{party.title} ({party.party_abbreviation} )</label>
                    <input
                        className="form-control"
                        type="number"
                        value={self.props.election.votes}
                        onChange={self.props.onFieldChange( 'votes' )}
                        /><br />
                    <button className="btn btn-success" onClick={self.props.onVoteClick(party)}>Pateikti rezultatus</button>
                </div>
                </form>
            );
        });

        return (
            <div>
                <h3>Daugiamandatės</h3>
                <h4>Apygarda: test</h4>
                <h4>Apylinkė: test</h4><br />
                {partyList}
                <input type="checkbox" /> Patvirtinu, kad įvesti duomenys teisingi.<br />
                <button className="btn btn-success">Publikuoti rezultatus</button>
                <button className="btn btn-danger">Atšaukti</button>
            </div>
        )
    }
});

RegisterVotesMultiEasyComponent.propTypes = {
    parties: React.PropTypes.array.isRequired,
    election: React.PropTypes.object.isRequired
};

window.RegisterVotesMultiEasyComponent = RegisterVotesMultiEasyComponent;