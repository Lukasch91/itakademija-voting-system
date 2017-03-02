var AdministrateSingleCandidatesComponent = React.createClass( {

    reload2: function() {
        console.log( "reload2" );
        this.props.reload3();
    },

    render: function() {
        var self = this;
        console.log( "_________________" )
        var constituencyList = this.props.constituencies.map( function( constituency, index ) {

            if ( constituency.numberOfCandidatesInConstituency != 0 ) {
                return (
                    <tr key={index}>
                        <td>{constituency.title}</td>
                        <td>
                            <ViewCandidatesByConstituencyComponent
                                reload1={self.reload2}
                                constituency={constituency} />
                        </td>
                    </tr>
                );
            } else {
                return (
                    <tr key={index}>
                        <td>{constituency.title}</td>
                        <td>
                            <AdministrateCandidatesCSVConstituencyComponent
                                reload1={self.reload2}
                                constitId={constituency.id} />
                        </td>
                    </tr>
                );
            }
        });

        return (
            <div className="panel panel-default">
                <h4>Vienmandatės</h4>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Apygardos pavadinimas</th>
                            <th>Veiksmai</th>
                        </tr>
                    </thead>
                    <tbody>
                        {constituencyList}

                    </tbody>
                </table>
            </div>
        )
    }
});

AdministrateSingleCandidatesComponent.propTypes = {
    constituencies: React.PropTypes.array.isRequired
};


window.AdministrateSingleCandidatesComponent = AdministrateSingleCandidatesComponent;