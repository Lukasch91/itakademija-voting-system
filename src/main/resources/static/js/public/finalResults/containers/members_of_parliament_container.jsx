var MembersOfParliamentContainer = React.createClass( {

    getInitialState: function() {
        return {
            members: [],
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/members/' )
            .then( function( response ) {
                self.setState( {
                    members: response.data,
                });

            })
    },



    render: function() {
        return (
            <div>
                <MembersOfParliamentListComponent
                    members={this.state.members}
                    />
            </div>
        )
    }
});

window.MembersOfParliamentContainer = MembersOfParliamentContainer;