var TestRegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            singleResults: [],
            currentDistrictId: 0
        };
    },

    componentWillMount: function() {
        var self = this;

        //bad design??? maybe pass a prop by rendering this element 
        //from <LoggedInRepresentativeInfoContainer /> as it knows the districtId
        //also maybe possible to split into 2 lifecycle methods
        axios.get( '/currentuser' )
            .then( function( response ) {
                self.setState( {currentDistrictId: response.data.districtId});
                axios.all( [
                    axios.get( '/api/candidate/' + response.data.districtId ),
                    axios.get( '/api/singleelection' )
                ] )
                    .then( axios.spread( function( candidateResponse, singleElectionResponse ) {
                        self.setState( {
                            candidates: candidateResponse.data,
                            singleResults: singleElectionResponse.data
                        });
                    }) );
            });
    },
    handleSpoiltChange: function(districtId, event) {
      
        console.log("I'm spoilt :@");
        console.log(event.target.value);
        console.log(districtId);
        
    },
    
    handleListChange: function(index, event) {

        console.log("inputHandler");
        console.log(index);
        console.log(event.target.value);
        
//        1) empty array
//        2) if array[index] == null create new object (singleVote)
//        3) if array[index] != null update array[index] object(singleVote)
        
        
//        4) set empty array state to new state
        
        
        
        
        
        
//    var emails = this.state.emails.slice(); // Make a copy of the emails first.
//      var emails = this.state.emails;
//      emails[index].emailText = event.target.value; // Update it with the modified email.
//      this.setState({emails: emails}); // Update the state.
    },
    
    handleExport: function() {
        
        var self = this;
        
        var toJson = JSON.stringify(self.state.candidates);
        console.log(toJson);
    },


    render: function() {
        var self = this;

        if ( self.state.singleResults[0] == null ) {
            
        
            var candidatesList = this.state.candidates.map( function( candidate, index ) {

 //      console.log( candidate.candidateConstituency != null ? candidate.candidateConstituency : '---' );

                return (
                        
                    <tr key={'row' + index}>
                        <td>{candidate.candidateName} {candidate.candidateSurname}</td>
                        <td>
                             <input key={'input' + index} type="text" className="form-control" onChange={self.handleListChange.bind(this, index)}/>
                        </td>
                    </tr>
                                                
                    
                );
            });
            

            return (
                <div>
                    <h3>Vienamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    
                    <div>
                    
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Kandidatai</th>
                                <th>Balsai</th>
                            </tr>
                        </thead>
                        <tbody>
                           {candidatesList}
                           <tr><td>Sugadinti balsai</td>
                           <td>
                           <input key={'input-spoilt'} type="text" className="form-control" onChange={self.handleSpoiltChange.bind(this, self.state.currentDistrictId)} />
                           </td></tr>
                        </tbody>
                    </table>
                    
                    
                            
                   </div>
                                        
                    <br />
                    <button type="button" className="btn btn-success" onClick={this.handleExport}>JSON</button>
                </div>
            )
        } 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        else {
            var singleElectionResultsList = this.state.singleResults.map( function( single, index ) {
                return (
                        <tr key={'single' + index}>
                            <td>{single.singleCandidate.candidateName}</td>
                            <td> {single.singleCandidate.candidateSurname} </td>
                            <td> {single.singleVotes} </td>
                        </tr>
                );
            });
        
            return (
                <form>
                    <h3>Vienamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Kandidato Vardas</th>
                            <th>Kandidato Pavardė</th>
                            <th>Balsai</th>
                        </tr>
                    </thead>
                    <tbody>
                            {singleElectionResultsList}
                    <tr><td>Sugadinti balsai</td></tr>
                    </tbody>
                </table>
                </form>
            )
        }
    }
});

window.TestRegisterVotesSingleContainer = TestRegisterVotesSingleContainer;



