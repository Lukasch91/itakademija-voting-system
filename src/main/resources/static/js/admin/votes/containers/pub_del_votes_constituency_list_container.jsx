var PubDelVotesConstituencyListContainer = React.createClass({
    
    getInitialState: function() {
        return {
            constituencies: []
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/ADMIN/constituency' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            });

    },
    
    handleDistrictList: function( constituency ) {
        var self = this;
        return function() {
            self.context.router.push( '/publish-delete-votes/' + constituency.id );
        }
    },
    
    render: function() {
        return (
                <PubDelVotesConstituencyListComponent 
                    constituencies={this.state.constituencies}
                    onDistrictsList={this.handleDistrictList}
                />
        )
    }
});

PubDelVotesConstituencyListContainer.contextTypes = {
        router: React.PropTypes.object.isRequired
};

window.PubDelVotesConstituencyListContainer = PubDelVotesConstituencyListContainer;