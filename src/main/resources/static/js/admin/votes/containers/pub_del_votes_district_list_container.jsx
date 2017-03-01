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
                  axios.get( '/api/ADMIN/constituency/' + conId ),
                  axios.get( '/api/REPRES/reg-votes-multi'),
                  axios.get( '/api/REPRES/singleelection')
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