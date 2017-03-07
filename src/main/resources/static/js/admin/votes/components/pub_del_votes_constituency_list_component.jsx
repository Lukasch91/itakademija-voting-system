var PubDelVotesConstituencyListComponent = React.createClass({
   render: function() {
       var self = this;
       var constituencyList = this.props.constituencies.map( function( constituency, index ) {
           return (
               <tr key={index}>
                   <td>{constituency.title}</td>
                   <td>{constituency.districts.length}</td>
                   <td><button type="button" className="btn btn-primary btn-xs " onClick={self.props.onDistrictsList( constituency )}>Apylinkių sąrašas</button></td>
               </tr>
           );
       });

       return (
           <div>
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