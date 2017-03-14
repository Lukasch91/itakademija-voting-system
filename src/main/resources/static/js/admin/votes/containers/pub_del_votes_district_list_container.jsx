var PubDelVotesDistrictListContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: [],
            constit: [],
            multiVotes: [],
            singleVotes: [],
            disableTest: true,
            theTestingState: 0,
            corruptedVote: 0
        };
    },

    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;  
        axios.all([
                  axios.get( '/api/ADMIN/constituency/' + conId ),
                  axios.get( '/api/ADMIN/reg-votes-multi'),
                  axios.get( '/api/ADMIN/singleelection')
                  ]).then(axios.spread(function (constResponse, votesMultiResponse, votesSingleResponse) {
                      self.setState( {
                          districts: constResponse.data.districts,
                          constit: constResponse.data,
                          multiVotes: votesMultiResponse.data,
                          singleVotes: votesSingleResponse.data
                      })
                  }));
    },
    
/*    invalidVotes: function(districtId) {
        var self = this;
        axios.get('/api/ADMIN/invalid-votes-dist/' + districtId + '/false').then(function (response) {
            self.setState({ 
                corruptedVote: response.data.votes
            });
        });
        return 666;   
    },*/

    handleGoBack: function() {
        console.log( 'click' );
        this.context.router.push( '/publish-delete-votes' );
    },

    handlePublishSingleVotes: function( districtId ) {
        var self = this;
        return function() {
            console.log( 'publishing: ' + districtId );
            axios.all([
                       axios.post( '/api/ADMIN/singleelectiondistrict/' + districtId ),
                       axios.post('/api/ADMIN/inalidvotesistrict/' + districtId + '/false')
                       ])
            .then(
                self.componentWillMount()
            );
            
        }
    },

    handleDeleteSingleVotes: function( districtId ) {
        var self = this;
        return function() {
            console.log( 'deleting ' + districtId );
            axios.all([
                       axios.delete('/api/ADMIN/singleelectiondistrict/' + districtId ),
                       axios.delete('/api/ADMIN/inalidvotesistrict/' + districtId + '/false')
                       ])
            .then(self.componentWillMount());
        }
    },

    handlePublishMultiVotes: function( districtId ) {
        var self = this;
        return function() {
            console.log( 'publishing: ' + districtId );
            axios.all([
                       axios.post( '/api/ADMIN/multielectiondistrict/' + districtId ),
                       axios.post('/api/ADMIN/inalidvotesistrict/' + districtId + '/true')
                       ])
            .then(self.componentWillMount());
        }
    },

    handleDeleteMultiVotes: function( districtId ) {
        var self = this;
        return function() {
            console.log( 'deleting ' + districtId );
            axios.all([
                       axios.delete('/api/ADMIN/multielectiondistrict/' + districtId ),
                       axios.delete('/api/ADMIN/inalidvotesistrict/' + districtId + '/true')
                       ])
            .then(self.componentWillMount());
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
                invalidVotes={this.invalidVotes}
                    />
                <button id="backToConstituency" type="button" className="btn btn-danger btn-xs" onClick={this.handleGoBack}>Atgal</button>
            </div>
        )
    }
});

PubDelVotesDistrictListContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};


window.PubDelVotesDistrictListContainer = PubDelVotesDistrictListContainer;