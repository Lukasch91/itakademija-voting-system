var AdministrateCandidatesCSVcomponent = React.createClass( {



    onFileChange: function() {

        console.log( "--1" );
        //        console.log( file );
        //        console.log( file.files[0] );
        var xxx = document.getElementById( 'fileId' ).files[0];
        console.log( xxx );
        console.log( 'xxx' + xxx );
        this.onHandleFileChange( xxx ); //self.refs.file.files[0]   //file.files[0]
        console.log( "--2" );
    },
    //  onChange={self.onXXX(self)}      //onChange={self.onFileChange}     onClick={self.onFileChange(constituency.id)}

    //onClick={() => { self.props.onFileChange(deleteButton) } }

    render: function() {
        return (
            <div>
                <button type="button" className="btn btn-primary btn-danger" data-toggle="modal" data-target="#modal">
                    Prideeti kandidatus
                    </button>

                <div className="modal fade" id="modal" tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">

                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>

                                <h4 className="modal-title" id="myModalLabel">Pasirinkite CSV bylaa</h4>

                            </div>

                            <div className="modal-body">
                                <form className="form">
                                    <div>
                                        <input
                                            id="fileId"
                                            type="file"
                                            name="file"
                                            accept='.csv'
                                            onChange={this.onFileChange}
                                            className="form-control" />
                                    </div>
                                </form>




                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-primary" onClick={() => { this.props.handleSHIT( 1 ) } } >Pridėti kandidatus</button>
                                <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

AdministrateSingleCandidatesComponent.propTypes = {
    onAddDistrictCandidates: React.PropTypes.func.isRequired,
    onHandleFileChange: React.PropTypes.func.isRequired,
    handleSHIT: React.PropTypes.func.isRequired
};


window.AdministrateCandidatesCSVcomponent = AdministrateCandidatesCSVcomponent;
