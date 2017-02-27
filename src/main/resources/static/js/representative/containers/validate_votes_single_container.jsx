var ValidateVotesSingleContainer = React.createClass( {


    render: function() {
        var self = this;

        var candidateId = self.props.candidate != null ? self.props.candidate.candidateID : null;
        var messageObjects = self.props.validation;
        var isSpoiltVote = self.props.isSpoilt;
        
        var messages = [];
        
        if (messageObjects[0] != null) {
              
            for(var i=0; i < messageObjects.length; i++) {
                                
                if(candidateId != null) {
                    if(messageObjects[i].candidateId === candidateId) {
                        messages = messageObjects[i].messages;
                    }

                } else if (messageObjects[i].spoiltVote == isSpoiltVote) {
                    messages = messageObjects[i].messages;
                }
            }
        }
        
        var listSomething = messages.map((message, idx) => {
            return (
                    <tr key={'mRow' + idx}>
                        <td>{message}</td>
                    </tr>

                );
            });

        return (
                <td>
                    <table>
                        <tbody>
                            {listSomething}
                        </tbody>
                    </table>
                </td>
                
        );
    }
});

window.ValidateVotesSingleContainer = ValidateVotesSingleContainer;
