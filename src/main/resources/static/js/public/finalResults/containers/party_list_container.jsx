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
                        labels: ['LDDP', 'LPP'],
                        datasets: [{
                            label: '# of Votes',
                            data: [10, 15],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)',
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