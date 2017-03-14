var MembersOfParliamentListComponent = React.createClass( {
    render: function() {
        var self = this;
        var membersList = this.props.members.map( function( member, index ) {
            return (
                <tr key={index}>
                    <td>{member.surname}</td>
                    <td>{member.firstname}</td>
                    <td>{member.party}</td>
                </tr>
                    
            );
        });



        return (
            <div>
                <h3>Balsavimo rezultatai rinkimų apylinkėse</h3>
                <div>
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th>Vardas</th>
                                <th>Pavardė</th>
                                <th>Partinė priklausomybė</th>
                            </tr>
                        </thead>
                        <tbody>
                            {membersList}
                        </tbody>
                    </table>
                </div>
            </div>

        )
    }
});



window.MembersOfParliamentListComponent = MembersOfParliamentListComponent;