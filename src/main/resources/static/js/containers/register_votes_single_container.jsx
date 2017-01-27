var RegisterVotesSingleContainer = React.createClass({
    
    getInitialState: function() {
        return {
            candidates: [] 
        };
    },
    
    componentWillMount: function() {
        var self = this;
        axios.get('/api/candidate')
        .then(function (response) {
            self.setState({ 
                candidates: response.data 
            });
        });

    },
    
    render: function() {
        return <RegisterVotesSingleComponent candidates={this.state.candidates}  />
    }
});


window.RegisterVotesSingleContainer = RegisterVotesSingleContainer;