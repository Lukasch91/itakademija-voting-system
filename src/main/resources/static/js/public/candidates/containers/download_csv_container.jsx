var DownloadCSVContainer = React.createClass( {

    getInitialState: function() {
        return {
            csvString: [],
        };
    },

    componentWillMount: function() {
        var self = this;
        self.setState( { csvString: null });
    },

    download: function() {
        //    excel -> data -> From text -> encoding utf-8

        var filename = "test.csv"
        var text = "EINARAS,VILDĖ,1940-01-01,34001010000,\"Jau seniai žinoma, kad vertinant \"\"dizainą\"\" - žodžių „Lorem Ipsum“.\",1\n" +
            "RENALDAS,ŠČIGLINSKAS,1940-01-02,34001010001,\"Jau seniai žinoma, kad vertinant \"\"dizainą\"\" - žodžių „Lorem Ipsum“.\",4\n" +
            "IRENIJUS,ZALECKIS,1940-01-03,34001010002,\"Jau seniai žinoma, kad vertinant \"\"dizainą\"\" - žodžių „Lorem Ipsum“.\",5";

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

        return (
            <div>
                <button type="button" className="btn btn-xs btn-success" onClick={self.download}>
                    Atsisiųsti CSV
                </button>
            </div>

        )
    }
});

window.DownloadCSVContainer = DownloadCSVContainer;

