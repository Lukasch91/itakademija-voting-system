var AdministrateSingleCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            constituencies: [],
            xxx: []
        };
    },

    componentWillMount: function() {

        var self = this;
        
        axios.get( '/api/constituency' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            })
            .then( function() {
                var constitIdCandidateNumber = null;
                var tempNumberOfCandidates = new Array();

//                console.log(self.state.constituencies);
                
                for ( var i = 0; i < self.state.constituencies.length; i++ ) {
                    axios.get( '/api/candidatesInConstituency/' + self.state.constituencies[i].id )
                    .then( function( response ) {
                            
                            
//     console.log(self.state.constituencies[(response.config.url.split( '/' )[3])-1]);
    
                        
     (self.state.constituencies[(response.config.url.split( '/' )[3])-1]).numberOfCandidates=response.data;
                             
   
                    });
                }
                
              console.log(self.state.constituencies);

                
            })
            .then( function( response ) {
                self.setState( {
                    xxx: self.state.constituencies 
                });
                console.log("a");
                console.log(self.state.xxx);
                self.forceUpdate();
            });

    },

    render: function() {
        return (
            <div>
                <AdministrateSingleCandidatesComponent
                    constituencies={this.state.constituencies}
                    numberOfCandidates={this.state.numberOfCandidates}
                    />
            </div>
        )
    }
});

window.AdministrateSingleCandidatesContainer = AdministrateSingleCandidatesContainer;

