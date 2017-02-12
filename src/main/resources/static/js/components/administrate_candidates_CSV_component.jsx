var AdministrateCandidatesCSVcomponent = React.createClass( {

    getInitialState: function() {
        return {
            file: null
        };
    },

    onHandleFileChange: function( file ) {

        this.setState( { file: file });
        console.log('2');
    },

    handleAddDistrictCandidates: function() {
        console.log('3');
        var self = this;

        var header = {
            headers: {
                'Content-Type': 'multipart/form-data',
                'constituencyId': 1
            }
        };

        var file = 'nofile.aaa';
        if ( this.state.file != null ) {
            file = this.state.file;
        }
        var data = new FormData();

        data.append( 'file', file );

        axios.post( '/api/districtcandidatesFILE', data, header )
            .then( function( response ) {
                console.log( "server_response" );
                console.log( response );
            });

    },

    onFileChange: function() {

        var xxx = document.getElementById( 'fileId' ).files[0];
        console.log('1');
        this.onHandleFileChange( xxx );
    },
    
    

    render: function() {
        
        var modalId = "modal" + this.props.constitId;
        var modalIdHash = "#modal" + this.props.constitId;
        return (
            
                <div>
                <button type="button" className="btn btn-primary btn-danger" data-toggle="modal" data-target={modalIdHash}>
                    Prideeti kandidatus (id: {this.props.constitId})
                    </button>

                <div className="modal fade" id={modalId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">

                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>

                                <h4 className="modal-title" id="myModalLabel">Pasirinkite CSV bylaa</h4>
                                <h4>{this.props.constitId}</h4>
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
                                <button type="button" className="btn btn-primary" onClick={this.handleAddDistrictCandidates} >Pridėti kandidatus</button>
                                <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});




window.AdministrateCandidatesCSVcomponent = AdministrateCandidatesCSVcomponent;
