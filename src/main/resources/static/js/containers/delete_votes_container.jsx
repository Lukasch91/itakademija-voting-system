var DeleteVotesContainer = React.createClass( {

    getInitialState: function() {
        return {
            constituencies: [],
            districts: [],
            districtVal: 'base',
            electionType: 'base'
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/constituency' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            });
        axios.get( '/api/district' )
            .then( function( response ) {
                self.setState( {
                    districts: response.data
                });
            });
    },

    handleDistrictChange: function( event ) {
        this.setState( { districtVal: event.target.value });
    },
    
    handleTypeChange: function( event ) {
        this.setState( { electionType: event.target.value });
    },

    handleDeleteVotes: function() {
        if ( this.state.districtVal != 'base' && this.state.electionType == 'single' ) {
            axios.delete( '/api/singleelectiondistrict/' + this.state.districtVal ).then( function() {
                console.log( 'votes deleted' );
            });
        } else {
            alert( 'Paršome pasirinkti apygardą' );
        }
    },


    render: function() {
        return (

            <DeleteVotesComponent
                constituencies={this.state.constituencies}
                districts={this.state.districts}
                onDistrictChange={this.handleDistrictChange}
                onTypeChange={this.handleTypeChange}
                onDeleteVotes={this.handleDeleteVotes} />

        )
    }
});

window.DeleteVotesContainer = DeleteVotesContainer;