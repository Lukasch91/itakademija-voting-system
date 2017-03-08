var ChangePasswordComponent = React.createClass( {
    render: function() {
        return (
            <div>

                <form>
                    <label>Pasikeiskite slaptažodį</label><br />
                    <input id="newAdminPass" className="form-control" type="password" value={this.props.admin.newPass} onChange={this.props.onFieldChange( 'newPass' )} name="input" /><br />
                    <label>Pakartokite slaptažodį</label><br />
                    <input id="newAdminPassCheck" className="form-control" type="password" value={this.props.admin.newPassCheck} onChange={this.props.onFieldChange( 'newPassCheck' )} name="input" /><br />
                    <button id="changePass" type="button" className="btn btn-warning btn-xs " onClick={this.props.onChangePassClick} data-toggle="modal" data-target="#modal">Keisti</button>


                    <div className="modal fade" id="modal" tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                    <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                                </div>
                                <div className="modal-body">
                                    {this.props.changeStatus}
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-xs btn-default" onClick={this.props.onPassChangeRedirect} data-dismiss="modal">Uždaryti</button>
                                </div>
                            </div>
                        </div>
                    </div>




                </form>

            </div>
        )
    }
});

window.ChangePasswordComponent = ChangePasswordComponent;