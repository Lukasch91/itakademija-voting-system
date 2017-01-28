var DeleteVotesContainer = React.createClass({

    getInitialState: function() {
        return {
            constituencies: [],
            districts: [],
            districtVal: 'base'
        };
    },
    
    componentWillMount: function() {
        var self = this;
        axios.get('/api/constituency')
        .then(function (response) {
            self.setState({ 
                constituencies: response.data 
            });
        });
        axios.get('/api/district')
        .then(function (response) {
            self.setState({ 
                districts: response.data
            });
        });
    },
    
    handleFieldChange: function(event) {
        this.setState({districtVal: event.target.value});
        axios.delete('/api/singleelectiondistrict/' + this.state.districtVal).then(function() {
            console.log('votes deleted');
        });
    },
    
    handleDeleteVotes: function() {
        if (this.state.districtVal != 'base') {
        axios.delete('/api/singleelectiondistrict/' + this.state.districtVal).then(function() {
            console.log('votes deleted');
        });
        } else {
            alert('Paršome pasirinkti apygardą');
        }
    },
    
    
    render: function() {
        return (

                <DeleteVotesComponent  
                constituencies={this.state.constituencies}  
                districts={this.state.districts}
                onFieldChange={this.handleFieldChange}
                onDeleteVotes={this.handleDeleteVotes}/>

                )
  }
});

window.DeleteVotesContainer = DeleteVotesContainer;