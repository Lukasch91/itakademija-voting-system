var AdministrateSingleCandidatesComponent = React.createClass( {

    render: function() {

        var self = this;


        var constituencyList = this.props.constituencies.map( function( constituency, index ) {

            /*
            get number of candidates who have constituencyId == index
             
             
            if ( if number is more than 0 then change button  ) {
            } else {
            }
              */

            return (
                <tr key={index}>
                    <td>{constituency.title}</td>
                    <td>
                        <AdministrateCandidatesCSVDistrictcomponent constitId={constituency.id} />
                    </td>
                    <td>
                        <ViewCandidatesByConstituencyComponent constituency={constituency} />
                    </td>
                </tr>
            );
        });

        return (
            <div className="panel panel-default">
                <h4>Vienmandatės</h4>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Apygardos pavadinimas</th>

                            <th>Pridėti kandidatus</th>
                            
                            <th>Peržiurėti kandidatus</th>
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
    handleSHIT: React.PropTypes.func.isRequired
};


window.AdministrateSingleCandidatesComponent = AdministrateSingleCandidatesComponent;