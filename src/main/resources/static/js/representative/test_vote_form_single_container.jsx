var TestVoteFormSingleContainer = React.createClass( {

    getInitialState: function() {
        return {
            candidateId: null,
            districtId: null
        
        };
    },
    componentWillMount: function() {
        var self = this;

        self.setState( {
            candidateId: self.props.candidateId,
            districtId: self.props.districtId
        });
    },


    render: function() {
        var self = this;
 

        return (
            <div>
                <b>I'm growing to be an input :D - </b>
                <b> - CandidateId: {self.state.candidateId} - </b>
                <b> - DistrictId: {self.state.districtId}</b>
            </div>
        )
    }

});


window.TestVoteFormSingleContainer = TestVoteFormSingleContainer;


   
