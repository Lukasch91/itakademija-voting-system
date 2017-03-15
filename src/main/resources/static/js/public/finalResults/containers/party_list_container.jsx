var PartyListContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/consolidatedResults/' )
            .then( function( response ) {
                self.setState( { parties: response.data });
            }).then( function() {

                var titles = self.partiesTitle( self.state.parties );
                var mandates = self.partiesMandates( self.state.parties );

                var ctx = document.getElementById( "myChart" );
                var myChart = new Chart( ctx, {
                    type: 'bar',
                    data: {
                        labels: titles,
                        datasets: [{
                            label: 'Mandatų skaičius',
                            data: mandates,
                            backgroundColor: "rgba(153,255,51,0.4)",
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            xAxes: [{
                                display: false
                            }],
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            })
    },

    partiesTitle: function( parties ) {
        var titleList = parties.map( function( party, index ) {
            return ( party.partyTitle );
        });
        return titleList;
    },

    partiesMandates: function( parties ) {
        var mandatesList = parties.map( function( party, index ) {
            return ( party.mandates );
        });
        return mandatesList;
    },

    render: function() {
        return (
            <div>
                <div className="row">
                    <div className="col-sm-12">
                        <h3>Balsavimo rezultatai rinkimų apylinkėse</h3>
                    </div>
                </div>
                <div className="row">
                    <div className="col-sm-6"><PartyListComponent
                        parties={this.state.parties}
                        />
                    </div>
                    <div className="col-sm-6"><canvas id="myChart"></canvas></div>
                </div>
            </div>

        )
    }
});

window.PartyListContainer = PartyListContainer;