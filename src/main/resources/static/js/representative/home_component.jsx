var HomeComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <div className="col-sm-8 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                    <br />
                    <div className="alert alert-success" style={{ borderRadius: 0 }}>
                        <h5 style={{ margin: '0' }}>Jūs esate prisijungęs prie RinkSis sistemos apylinkės atstovo aplinkos.</h5>
                    </div>
                    <div className="panel panel-default" style={{ borderRadius: 0 }}>
                        <div className="panel-heading">Instrukcija</div>
                        <div className="panel-body">
                            <p>Pirmą kartą prisijungus rekomenduojama pasikeistį slaptažodį, paspaudus ant <span className="glyphicon glyphicon-cog"></span>.</p>
                            <p>Suskaičiuotus vienmandatėse ir daugiamantasėse apygardose balsus įvest galite viršutiniame meniu pasirinkę atitinkamai "Balsų registravimas (vien.)" ir "Balsų registravimas (daug.)".
                            Balsus leidžiama suvesti tik tada, kai oficialiai paskelbiama rinkimų pradžia.
                            Įvestus ir nusiųstus balsus galima anuliuoti tik tiesiogiai susisiekus su administratoriumi.</p>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

window.HomeComponent = HomeComponent;