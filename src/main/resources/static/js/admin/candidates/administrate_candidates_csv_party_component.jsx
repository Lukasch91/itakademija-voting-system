var AdministrateCandidatesCSVPartyComponent = React.createClass( {

    getInitialState: function() {
        return {
            fileString: {},
            id: 0,
            submit: false,
            loading: "",
            checkedComma: true,
            checkedSemiColon: false,
        };
    },

    componentWillMount: function() {
        var content = { text: "No content!!!", id: this.props.party.id, delimiter: ',' };
        this.setState( {
            id: this.props.party.id,
            fileString: content
        });
    },

    onFileChange: function() {

        var self = this;
        var file = document.getElementById( "inputId" + self.state.id );
        var reader = new FileReader();
        var regex = new RegExp( "(.*?)\.(csv)$" );
        self.setState( { loading: "Kraunama..." });

        reader.onload = function( evt ) {
            if ( evt.target.readyState != 2 ) return;
            if ( evt.target.error ) {
                console.log( '=#=  Error while reading file' );
                return;
            }
            console.log( evt.target.result );
            var text = self.state.fileString;
            text.text = evt.target.result;

            self.setState( { fileString: text, submit: true, loading: "Įkrauta" });
            setTimeout( function() { self.setState( { loading: "" }); }, 2000 );
        };

        reader.onprogress = function( data ) {
            if ( data.lengthComputable ) {
                var progress = parseInt(( ( data.loaded / data.total ) * 100 ), 10 );
                console.log( progress );
            }
        };

        if ( ( regex.test( file.files[0].name ) ) ) {
            if ( ( file.files[0].size <= 1048576 ) ) {
                reader.readAsText( file.files[0] );
            } else {
                self.setState( { submit: false });
                console.log( "=#=  File is TOOOO big for MY anus" );
            }
        } else {
            self.setState( { submit: false });
            console.log( "=#=  Wrong File Format" );
        }
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

    comma: function() {
        var self = this;
        var fileString = self.state.fileString;
        fileString.delimiter = ',';

        this.setState( {
            checkedComma: true,
            checkedSemiColon: false,
            fileString: fileString
        });
    },

    semicolon: function() {
        var self = this;
        var fileString = self.state.fileString;
        fileString.delimiter = ';';
        this.setState( {
            checkedComma: false,
            checkedSemiColon: true,
            fileString: fileString
        });
    },

    render: function() {
        var self = this;
        var modalId = "modal" + this.props.party.id;
        var modalIdHash = "#modal" + this.props.party.id;
        var party = this.props.party;

        return (

            <div>
                <button type="button" className="btn btn-primary btn-xs" data-toggle="modal" data-target={modalIdHash}>
                    Pridėti kandidatus
                    </button>

                <div className="modal fade" id={modalId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">

                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>

                                <h4 className="modal-title" id="myModalLabel">Pasirinkite {party.title} CSV bylą</h4>
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
                                <p>{self.state.loading}</p>
                                <form className="form">
                                    <input type="radio" onChange={self.comma} checked={self.state.checkedComma} /> Duomenys atskirti kableliais<br />
                                    <input type="radio" onChange={self.semicolon} checked={self.state.checkedSemiColon} /> Duomenys atskirti kabliataškiais
                                </form>
                            </div>
                            <div className="modal-footer">
                                <button
                                    id="submit"
                                    type="button"
                                    className="btn btn-xs btn-success"
                                    onClick={this.handleAddPartyCandidates}
                                    disabled={!self.state.submit}
                                    >Pridėti kandidatus</button>
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
