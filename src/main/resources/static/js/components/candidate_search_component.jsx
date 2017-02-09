var CandidateSearchComponen = React.createClass({
   render: function () {
       return (
               <div>
               
               <form>
              
               <div className="input-group">
               <label>Ieškoti kandidato</label>
               
                 <input type="text" className="form-control" placeholder="Search" disabled />
                 <div className="input-group-btn">
                   <button className="btn btn-default" type="submit">
                     <i className="glyphicon glyphicon-search"></i>
                   </button>
                 </div>
               </div>
               
             </form>
               
               </div>
               
       )
   } 
});

window.CandidateSearchComponen = CandidateSearchComponen;