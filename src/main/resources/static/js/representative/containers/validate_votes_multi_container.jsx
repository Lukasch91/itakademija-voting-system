var ValidateVotesMultiContainer = React.createClass( {


    render: function() {
        var self = this;

        var partyId = self.props.party != null ? self.props.party.id : null;
        var messageObjects = self.props.validation;
        var isSpoiltVote = self.props.isSpoilt;

        var messages = [];

        if ( messageObjects[0] != null ) {

            for ( var i = 0; i < messageObjects.length; i++ ) {

                if ( partyId != null ) {
                    if ( messageObjects[i].partyId === partyId ) {
                        messages = messageObjects[i].messages;
                    }

                } else if ( messageObjects[i].spoiltVote == isSpoiltVote ) {
                    messages = messageObjects[i].messages;
                }
            }
        }

        var listSomething = messages.map(( message, idx ) => {
            return (
                <tr key={'mData' + idx}>
                    <td style={{ color: 'red' }}>{message}</td>
                </tr>
            );
        });

        return (
            <tr>
                <td colSpan="2">
                    <table className="table table-condensed">
                        <tbody>
                            {listSomething}
                        </tbody>
                    </table>
                </td>
            </tr>
        );
    }
});

window.ValidateVotesMultiContainer = ValidateVotesMultiContainer;
