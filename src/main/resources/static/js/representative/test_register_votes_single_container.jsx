var TestRegisterVotesSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidates: [],
            singleResults: []
            //            currentDistrictId: 0

        };
    },

    componentWillMount: function() {
        var self = this;

        //bad design??? maybe pass a prop by rendering this element 
        //from <LoggedInRepresentativeInfoContainer /> as it knows the districtId
        //also possible to split into 2 lifecycle methods
        axios.get( '/currentuser' )
            .then( function( response ) {
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


    render: function() {
        var self = this;

        if ( self.state.singleResults[0] == null ) {
            var candidatesList = this.state.candidates.map( function( candidate, index ) {

 //      console.log( candidate.candidateConstituency != null ? candidate.candidateConstituency : '---' );

                return (
                    <div key={'candidate' + index}>
                        <label>{candidate.candidateName} {candidate.candidateSurname}</label>
                        <TestVoteFormSingleContainer candidateId={candidate.candidateID} /><br />
                    </div>
                );
            });
            return (
                <form>
                    <h3>Vienamandatės</h3>
                    <LoggedInRepresentativeInfoContainer />
                    {candidatesList}
                    <button id="publishSingle" className="btn btn-success" onClick="">XXXXX</button>
                </form>
            )
        } else {
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
                    </tbody>
                </table>
                </form>
            )
        }
    }
});

window.TestRegisterVotesSingleContainer = TestRegisterVotesSingleContainer;



