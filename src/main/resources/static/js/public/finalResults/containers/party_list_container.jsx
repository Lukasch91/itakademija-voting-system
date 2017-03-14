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
                self.setState( {
                    parties: response.data,
                });

            }).then( function() {
                var ctx = document.getElementById( "myChart" );
                var myChart = new Chart( ctx, {
                    type: 'bar',
                    data: {
                        labels: [self.state.parties[0].partyTitle, this.state.parties[1].partyTitle],
                        datasets: [{
                            label: '# of Votes',
                            data: [self.state.parties[0].mandates, this.state.parties[1].mandates],
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
                    />
                <canvas id="myChart" width="400" height="400"></canvas>
            </div>
        )
    }
});

window.PartyListContainer = PartyListContainer;