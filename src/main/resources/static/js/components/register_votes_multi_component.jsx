var RegisterVotesMultiComponent = React.createClass( {
    render: function() {
        var self = this;
        var partyList = this.props.parties.map( function( party, index ) {
            return (
                <div key={index}>
                    <label>{party.title} ({party.party_abbreviation} )</label>
                    <input
                        className="form-control"
                        type="number"
                        value={self.props.election.votes}
                        onChange={self.props.onFieldChange( 'votes' + index )}
                        /><br />
                </div>
            );
        });

        return (
            <form>
                <h3>Daugiamandatės</h3>
                <h4>Apygarda: test</h4>
                <h4>Apylinkė: test</h4><br />
                {partyList}
                <input type="checkbox" /> Patvirtinu, kad įvesti duomenys teisingi.<br />
                <button className="btn btn-success" onClick={this.props.onVoteClick}>Pateikti rezultatus</button>
                <button className="btn btn-danger">Atšaukti</button>
            </form>
        )
    }
});

RegisterVotesMultiComponent.propTypes = {
    parties: React.PropTypes.array.isRequired,
    election: React.PropTypes.object.isRequired
};

window.RegisterVotesMultiComponent = RegisterVotesMultiComponent;