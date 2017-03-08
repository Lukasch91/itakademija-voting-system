var DownloadCSVContainer = React.createClass( {

    getInitialState: function() {
        return {
            filename: "NoName",
            request: 0
        };
    },

    componentWillMount: function() {
        var self = this;
        var filename = self.props.fileName;
        var request = self.props.request;

        self.setState( { filename: filename,
                         request: request
                         });  
    },
    
    getData: function() {
        var self = this;
        var request = self.state.request;
        
        axios.get( '/api/PUBLIC/downloadCSV/'+ request )
        .then( function( response ) {
            console.log(response.data);
        });
        
        
        var text = "EINARAS,VILDĖ,1940-01-01,34001010000,\"Jau seniai žinoma, kad vertinant \"\"dizainą\"\" - žodžių „Lorem Ipsum“.\",1\n" +
        "RENALDAS,ŠČIGLINSKAS,1940-01-02,34001010001,\"Jau seniai žinoma, kad vertinant \"\"dizainą\"\" - žodžių „Lorem Ipsum“.\",4\n" +
        "IRENIJUS,ZALECKIS,1940-01-03,34001010002,\"Jau seniai žinoma, kad vertinant \"\"dizainą\"\" - žodžių „Lorem Ipsum“.\",5";
 
        return text;
        
        
    },
    
    downloadCSV: function() {
        //    excel -> data -> From text -> encoding utf-8
        var self = this;
        
        var filename = self.state.filename;

        var text = self.getData();
      
        
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
                <button type="button" className="btn btn-xs btn-success" onClick={self.downloadCSV}>
                    {buttonName}
                </button>
            </div>

        )
    }
});

window.DownloadCSVContainer = DownloadCSVContainer;

