var DownloadCSVContainer = React.createClass( {

    getInitialState: function() {
        return {
            filename: "NoName",
            request: 0,
            csvData: ""
        };
    },

    componentWillMount: function() {
        var self = this;
        var filename = self.props.fileName;
        var request = self.props.request;

        self.setState( {
            filename: filename,
            request: request
        });
    },

    getData: function() {
        var self = this;
        var request = self.state.request;

        axios.get( '/api/PUBLIC/downloadCSV/' + request )
            .then( function( response ) {
                self.setState( { csvData: response.data });
            })
            .then( function() {
                self.downloadCSV();
            });
    },

    downloadCSV: function() {
        var self = this;
        var filename = self.state.filename;
        var text = self.state.csvData;

        var pom = document.createElement( 'a' );
        pom.setAttribute( 'href', 'data:text/plain;charset=utf-8,' + encodeURIComponent( text ) );

        pom.setAttribute( 'download', filename );
        pom.style.display = 'none';

        document.body.appendChild( pom );
        pom.click();

        document.body.removeChild( pom );
    },


    render: function() {
        var self = this;
        var buttonName = self.props.buttonName;
        return (
            <div>
                <button type="button" className="btn btn-xs btn-success" onClick={self.getData}>
                    {buttonName}
                </button>
            </div>

        )
    }
});

window.DownloadCSVContainer = DownloadCSVContainer;