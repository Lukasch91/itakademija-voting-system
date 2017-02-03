var VoteFormSingleContainer = React.createClass( {

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
                console.log( 'vote added' );
            });
        /*window.location.reload();*/

/*        [
         {
           "singleCandidate": {      "candidateID": 1 },
           "singleDeletedDate": null,
           "singleDistrict": { "id": 1 },
           "singleEnteredDate": null,
           "singlePublishedDate": null,
           "singleVotes": 10
         }
       ]*/
    },

    render: function() {
        return <VoteFormSingleComponent

            election={this.state.election}
            onFieldChange={this.handleFieldChange}
            onVoteClick={this.handleVoteClick}
            />
    }
});


window.VoteFormSingleContainer = VoteFormSingleContainer;