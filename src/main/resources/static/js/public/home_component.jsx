var HomeComponent = React.createClass({
    render: function() {
        return (
                <div>
                    <h1>Sveiki atvykę!</h1>
                    
                    <DownloadCSVContainer 
                    buttonName="Atsisiųsti CSV" 
                    request="1"
                    fileName="test.csv"/>
                    <br />
                    <DownloadCSVContainer 
                    buttonName="Atsisiųsti CSV 2" 
                    request="2"
                    fileName="other.csv"/>
                    
                    <p>test.csv -> excel2010 -> data -> From text -> delimited -> encoding utf-8 -> comma</p>
                </div>
        )
    }
});

window.HomeComponent = HomeComponent;