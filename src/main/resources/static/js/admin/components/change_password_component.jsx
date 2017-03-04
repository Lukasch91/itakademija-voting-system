var ChangePasswordComponent = React.createClass({
   render: function() {
       return (
               <div>
               
               <form>
               <label>Pasikeiskite slaptažodį</label><br />
               <input id="newAdminPass" className="form-control" type="password" value={this.props.admin.newPass} onChange={this.props.onFieldChange( 'newPass' )} name="input" /><br />
               <button id="changePass" className="btn btn-success btn-xs " onClick={this.props.onChangePassClick}>Keisti</button>
           </form>
               
               </div>
       )
   } 
});

window.ChangePasswordComponent = ChangePasswordComponent;