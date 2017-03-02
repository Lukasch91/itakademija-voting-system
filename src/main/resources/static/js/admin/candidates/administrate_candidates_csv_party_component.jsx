var AdministrateCandidatesCSVPartyComponent = React.createClass( {

    getInitialState: function() {
        return {
            fileString: {},
            id: 0
        };
    },

    componentWillMount: function() {
        var content = { text: "No content!!!", id: this.props.partyId };
        this.setState( {
            id: this.props.partyId,
            fileString: content
        });
    },

    onFileChange: function() {
        var self = this;
        var file = document.getElementById( "inputId" + self.state.id );
        var reader = new FileReader();

        reader.onload = function( evt ) {
            if ( evt.target.readyState != 2 ) return;
            if ( evt.target.error ) {
                console.log( 'Error while reading file' );
                return;
            }
            console.log( evt.target.result );
            var text = self.state.fileString;
            text.text = evt.target.result;
            self.setState( { fileString: text });
        };

        reader.readAsText( file.files[0] );

    },

    handleAddPartyCandidates: function() {
        var self = this;

        var data = self.state.fileString;
        console.log( data );

        axios.post( '/api/ADMIN/partycsv', data )
            .then( function( response ) {
                console.log( "server_response" );
                console.log( response );
                if ( response.status == 201 ) {
                    // call fathers self.componentWillMount();
                    $( "#modal" + self.state.id ).modal( 'hide' );
                    console.log( "reload1" );
                    self.props.reload1();
                }
            })
            .catch( function( error ) {
                console.log( error );
            });
    },

    render: function() {
        var self = this;
        var modalId = "modal" + this.props.partyId;
        var modalIdHash = "#modal" + this.props.partyId;

        return (

            <div>
                <button type="button" className="btn btn-xs btn-primary btn-primary" data-toggle="modal" data-target={modalIdHash}>
                    Pridėti kandidatus
                    </button>

                <div className="modal fade" id={modalId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">

                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>

                                <h4 className="modal-title" id="myModalLabel">Pasirinkite CSV bylą</h4>
                            </div>

                            <div className="modal-body">
                                <form className="form">
                                    <div>
                                        <input
                                            id={"inputId" + self.state.id}
                                            type="file"
                                            name="file"
                                            accept='.csv'
                                            onChange={this.onFileChange}
                                            className="form-control" />
                                    </div>
                                </form>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-xs btn-primary" onClick={this.handleAddPartyCandidates}>Pridėti kandidatus</button>
                                <button type="button" className="btn btn-xs btn-default" data-dismiss="modal">Atšaukti</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});




window.AdministrateCandidatesCSVPartyComponent = AdministrateCandidatesCSVPartyComponent;
