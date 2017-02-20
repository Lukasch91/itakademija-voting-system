var PubDelVotesDistrictListContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: [],
            constit: [],
            multiVotes: [],
            singleVotes: [],
            disableTest: true,
            theTestingState: 0
        };
    },

    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;
        
        axios.all([
                  axios.get( '/api/constituency/' + conId ),
                  axios.get( '/api/reg-votes-multi'),
                  axios.get( '/api/singleelection')
                  ]).then(axios.spread(function (constResponse, votesMultiResponse, votesSingleResponse) {
                      self.setState( {
                          districts: constResponse.data.districts,
                          constit: constResponse.data,
                          multiVotes: votesMultiResponse.data,
                          singleVotes: votesSingleResponse.data
                      })
                  }));
    },

    handleGoBack: function() {
        console.log( 'click' );
        this.context.router.push( '/publish-delete-votes' );
    },

    handlePublishSingleVotes: function( districtId ) {
        var self = this;
        return function() {
            console.log( 'publishing: ' + districtId );
            axios.post( '/api/singleelectiondistrict/' + districtId ).then(function() {
                window.location.reload();
            });
            
        }
    },

    handleDeleteSingleVotes: function( districtId ) {
        return function() {
            console.log( 'deleting ' + districtId );
            axios.delete( '/api/singleelectiondistrict/' + districtId ).then(function() {
                window.location.reload();
            });
        }
    },

    handlePublishMultiVotes: function( districtId ) {
        return function() {
            console.log( 'publishing: ' + districtId );
            axios.post( '/api/multielectiondistrict/' + districtId ).then(function() {
                window.location.reload();
            });
        }
    },

    handleDeleteMultiVotes: function( districtId ) {
        return function() {
            console.log( 'deleting ' + districtId );
            axios.delete( '/api/multielectiondistrict/' + districtId ).then(function() {
                window.location.reload();
            });
        }
    },
    
    handleTheTesting: function(districtId) {
        var self = this;
        console.log(districtId);
        return function() {
            self.setState({
                theTestingState: districtId
            });
            
        }
    },

    render: function() {
        
        return (
            <div>
                <PubDelVotesDistrictListComponent
                    districts={this.state.districts}
                    constit={this.state.constit}
                    multiVotes={this.state.multiVotes}
                    singleVotes={this.state.singleVotes}
                    onPublishSingleVotes={this.handlePublishSingleVotes}
                    onDeleteSingleVotes={this.handleDeleteSingleVotes}
                    onPublishMultiVotes={this.handlePublishMultiVotes}
                    onDeleteMultiVotes={this.handleDeleteMultiVotes}
                    disableTest={this.state.disableTest}
                    theTesting={this.handleTheTesting}
                    theTestingState={this.state.theTestingState}
                    disableTest={this.state.disableTest}
                    />
                <button id="backToConstituency" type="button" className="btn btn-default" onClick={this.handleGoBack}>Back</button>
            </div>
        )
    }
});

PubDelVotesDistrictListContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};


window.PubDelVotesDistrictListContainer = PubDelVotesDistrictListContainer;