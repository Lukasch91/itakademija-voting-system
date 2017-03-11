var HomeComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <h1>Sveiki atvykę!</h1>

                <DownloadCSVContainer
                    buttonName="Visi vienmandačių rezultatai"
                    request="3"
                    fileName="Visi vien.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Esminiai vienmandačių rezultatai"
                    request="2"
                    fileName="Esminiai vien.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Visi daugiamandačių rezultatai"
                    request="4"
                    fileName="Visi daug.csv" />
                <br />
                <DownloadCSVContainer
                    buttonName="Esminiai daugiamandačių rezultatai"
                    request="1"
                    fileName="Esminiai daug.csv" />
                <br />

                <p>test.csv -> excel2010 -> data -> From text -> delimited -> encoding utf-8 -> comma</p>
            </div>
        )
    }
});

window.HomeComponent = HomeComponent;