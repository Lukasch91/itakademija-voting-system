var CandidateSearchComponen = React.createClass({
   render: function () {
       return (
               <div>
               
               <form>
              
               <div className="input-group">
               <label>Ieškoti kandidato</label>
               
                 <input id="search" type="text" className="form-control" placeholder="Search" disabled />
                 <div className="input-group-btn">
                   <button id="searchButton" className="btn btn-default" type="submit">
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