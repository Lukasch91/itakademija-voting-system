var RegisterVotesMultiComponent = React.createClass( {
    render: function() {
        var self = this;
        var votesList = [];
        var votesEntered = [];
        
        this.props.elections.map( function( electVotes, index ) {
            votesList.push( electVotes.party.id );
            votesEntered.push( electVotes );
        });

        console.log('bla');
        var partyCount = [];


        var partyList = this.props.parties.map( function( party, index ) {
            partyCount.push(party);

            if ( votesList.includes( party.id ) ) {

                for ( var i = 0; i < votesEntered.length; i++ ) {
                    if ( votesEntered[i].party.id == party.id ) {
                        return (
                            <div key={index}>
                                <label>{party.title} ({party.party_abbreviation} )</label>
                                <div>Įvestas rezultatas: {votesEntered[i].votes}</div><br />
                            </div> )
                    }
                }


            } else {

                return (
                    <div key={index}>
                        <label>{party.title} ({party.party_abbreviation} )</label>
                        <VoteFormContainer partyId={party.id} /><br />
                    </div>
                );
            }
            
        });

        console.log(partyCount.length);
        var disabled = true;
        if (votesEntered.length == 5 && votesEntered[0].published_date == null) {
            disabled = false;
        }
        
        
        return (
            <form>
                <h3>Daugiamandatės</h3>
                <h4>Apygarda: test</h4>
                <h4>Apylinkė: test</h4><br />
                {partyList}
                <input type="checkbox" /> Patvirtinu, kad įvesti duomenys teisingi.<br />
                <button className="btn btn-success" onClick={self.props.onPublishVotes} disabled={disabled}>Publikuoti rezultatus</button>&nbsp;
                
            </form>
        )
    }
});

RegisterVotesMultiComponent.propTypes = {
    parties: React.PropTypes.array.isRequired,
    elections: React.PropTypes.array.isRequired
};

window.RegisterVotesMultiComponent = RegisterVotesMultiComponent;