var HomeComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <div className="jumbotron" style={{minHeight: '600px', borderRadius: 0, backgroundImage: 'url(images/seimas.png)'}}>
                <h1><span className="glyphicon glyphicon-ok" ></span> RinkSis</h1>
                <p>Naujausia Lietuvos Respublikos Seimo rinkimų sistema</p>
                
              </div>
                <p>test.csv -> excel2010 -> data -> From text -> delimited -> encoding utf-8 -> comma</p>
            </div>
        )
    }
});

window.HomeComponent = HomeComponent;