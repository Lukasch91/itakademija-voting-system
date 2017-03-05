var HomeComponent = React.createClass({
    render: function() {
        return (
                <div>
                    <h1>Sveiki atvykę!</h1>
                    <DownloadCSVContainer />
                    <p>test.csv -> excel2010 -> data -> From text -> delimited -> encoding utf-8 -> comma</p>
                </div>
        )
    }
});

window.HomeComponent = HomeComponent;