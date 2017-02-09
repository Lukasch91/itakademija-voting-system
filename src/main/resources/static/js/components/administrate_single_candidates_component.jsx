var AdministrateSingleCandidatesComponent = React.createClass( {

    render: function() {

        var self = this;



        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
            return (
                <tr key={index}>
                    <td>{constituency.title}</td>
                    <td>
                        <button onClick={() => { self.props.handleSHIT( constituency.id ) } } className="btn btn-primary">Pridėti kandidatus</button>

                    </td>
                    <td>
                        <AdministrateCandidatesCSVcomponent handleSHIT={() => {self.props.handleSHIT}}
                                    deletion={() => { self.props.onRemoveItem( constituency.id ) } } />
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