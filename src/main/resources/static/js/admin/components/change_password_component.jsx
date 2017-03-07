var ChangePasswordComponent = React.createClass({
   render: function() {
       return (
               <div>
               
               <form>
               <label>Pasikeiskite slaptažodį</label><br />
               <input id="newAdminPass" className="form-control" type="password" value={this.props.admin.newPass} onChange={this.props.onFieldChange( 'newPass' )} name="input" /><br />
               <label>Pakartokite slaptažodį</label><br />
               <input id="newAdminPass" className="form-control" type="password" value={this.props.admin.newPassCheck} onChange={this.props.onFieldChange( 'newPassCheck' )} name="input" /><br />
               <button id="changePass" type="button" className="btn btn-warning btn-xs " onClick={this.props.onChangePassClick}>Keisti</button>
               <p>{this.props.passwordError}</p>
           </form>
               
               </div>
       )
   } 
});

window.ChangePasswordComponent = ChangePasswordComponent;