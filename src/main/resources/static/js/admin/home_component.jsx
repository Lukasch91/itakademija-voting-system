var HomeComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <div className="col-sm-8 col-centered" style={{ float: 'none', margin: '0 auto' }}>
                    <br />
                    <div className="alert alert-success" style={{borderRadius: 0}}>
                        <h5 style={{margin: '0'}}>Jūs esate prisijungęs prie RinkSis sistemos administratoriaus aplinkos.</h5>
                    </div>
                    <div className="panel panel-default" style={{borderRadius: 0}}>
                        <div className="panel-heading">Instrukcija</div>
                        <div className="panel-body">
                            <p>Pirmą kartą prisijungus rekomenduojama pasikeistį slaptažodį, paspaudus ant <span className="glyphicon glyphicon-cog"></span>.</p>
                            <p>Norėdami pridėti/ištrinti apygardą, apylinkę ar apylinkės atstovą viršutiniame meniu pasirinkite “Apygardos/Apylinkės”.</p>
                            <p>Meniu pasirinkę “Partijos” galėsite pridėti/trinti partijas, dalyvausiančias rinkimuose.</p>
                            <p>Norėdami įkelti kaldinatus tiek vienmandatėms apygardoms, tiek daugiamantasėe apygardose partijoms, pasirinkite “Kandidatų įkėlimas”.</p>
                            <p>Peržiūrėti apylinkės atstovo įvestus balsus galima meniu pasirinkus “Rezultatai”. Pasirinktos apylinkės rezultatus galima publikuoti ir trinti.</p>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

window.HomeComponent = HomeComponent;