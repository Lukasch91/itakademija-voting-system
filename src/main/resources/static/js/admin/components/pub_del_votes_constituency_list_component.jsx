var PubDelVotesConstituencyListComponent = React.createClass({
   render: function() {
       var self = this;
       var constituencyList = this.props.constituencies.map( function( constituency, index ) {
           return (
               <tr key={index}>
                   <td>{constituency.title}</td>
                   <td>{constituency.districts.length}</td>
                   <td><button type="button" className="btn btn-info" onClick={self.props.onDistrictsList( constituency )}>Atidaryti apylinkių sąrašą</button></td>
               </tr>
           );
       });

       return (
           <div className="panel panel-default">
               <table className="table">
                   <thead>
                       <tr>
                           <th>Apygarda</th>
                           <th>Apylinkės</th>
                           <th></th>
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

window.PubDelVotesConstituencyListComponent = PubDelVotesConstituencyListComponent;