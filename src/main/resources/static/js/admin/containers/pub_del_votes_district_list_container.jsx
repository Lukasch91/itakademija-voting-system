var PubDelVotesDistrictListContainer = React.createClass( {

    getInitialState: function() {
        return {
            districts: [],
            constit: []
        };
    },

    componentWillMount: function() {
        var self = this;
        var conId = this.props.params.conId;
        axios.get( '/api/constituency/' + conId )
            .then( function( response ) {
                self.setState( {
                    districts: response.data.districts,
                    constit: response.data
                });
            });

    },


    handleGoBack: function() {
        console.log( 'click' );
        this.context.router.push( '/publish-delete-votes' );
    },

    handlePublishSingleVotes: function( districtId ) {
        return function() {
            console.log( 'publishing: ' + districtId );
            axios.post( '/api/singleelectiondistrict/' + districtId );
        }
    },

    handleDeleteSingleVotes: function( districtId ) {
        return function() {
            console.log( 'deleting ' + districtId );
            axios.delete( '/api/singleelectiondistrict/' + districtId );
        }
    },

    handlePublishMultiVotes: function( districtId ) {
        return function() {
            console.log( 'publishing: ' + districtId );
            axios.post( '/api/multielectiondistrict/' + districtId );
        }
    },

    handleDeleteMultiVotes: function( districtId ) {
        return function() {
            console.log( 'deleting ' + districtId );
            axios.delete( '/api/multielectiondistrict/' + districtId );
        }
    },

    render: function() {
        return (
            <div>
                <PubDelVotesDistrictListComponent
                    districts={this.state.districts}
                    constit={this.state.constit}
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