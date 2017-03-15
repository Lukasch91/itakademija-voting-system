var HomeComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <div className="jumbotron" style={{minHeight: '600px', borderRadius: 0, backgroundImage: 'url(images/seimas.png)'}}>
                <h1><span className="glyphicon glyphicon-ok" ></span> RinkSis</h1>
                <p>Naujausia Lietuvos Respublikos Seimo rinkimų sistema</p>          
              </div>
            </div>
        )
    }
});

window.HomeComponent = HomeComponent;