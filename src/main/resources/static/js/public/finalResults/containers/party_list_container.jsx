var PartyListContainer = React.createClass( {

    getInitialState: function() {
        return {
            parties: [],
            mandates: [],
            labels: [],
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/consolidatedResults/' )
            .then( function( response ) {
                self.setState( {
                    parties: response.data,
                    
                });
                var ctx = document.getElementById( "myChart" );
                var myChart = new Chart( ctx, {
                    type: 'bar',
                    data: {
// CIA REIKIA PADUOTI PARTY TITLES MASYVA                       
                        labels: [this.state.parties],
                        datasets: [{
                            label: '# of Votes',
// CIA REIKIA PADUOTI MANDATU MASYVA     
                            data: [this.state.parties],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
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

    render: function() {
        return (
            <div>
                <PartyListComponent
                    parties={this.state.parties}
                    labels={this.state.labels}
                    mandates={this.state.mandates}
                    />
                <canvas id="myChart" width="400" height="400"></canvas>
            </div>
        )
    }
});

window.PartyListContainer = PartyListContainer;