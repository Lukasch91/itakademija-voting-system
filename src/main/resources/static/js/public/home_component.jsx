var HomeComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <div className="jumbotron" style={{minHeight: '600px', borderRadius: 0, backgroundImage: 'url(images/seimas.png)'}}>
                <h1><span className="glyphicon glyphicon-ok" ></span> RinkSis</h1>
                <p>Naujausia Lietuvos Respublikos Seimo rinkimų sistema</p>
                
              </div>

                <DownloadCSVContainer
                    buttonName="Visi vienmandačių rezultatai"
                    request="1"
                    fileName="Visi vien.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Esminiai vienmandačių rezultatai"
                    request="3"
                    fileName="Esminiai vien.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Visi daugiamandačių rezultatai"
                    request="2"
                    fileName="Visi daug.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Esminiai daugiamandačių rezultatai"
                    request="4"
                    fileName="Esminiai daug.csv" />
                <br />

                <p>test.csv -> excel2010 -> data -> From text -> delimited -> encoding utf-8 -> comma</p>
            </div>
        )
    }
});

window.HomeComponent = HomeComponent;