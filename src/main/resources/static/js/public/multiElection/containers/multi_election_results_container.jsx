var MultiElectionsResultsContainer = React.createClass( {

    getInitialState: function() {
        return {
            consituencies: [],
            parties: [],
            info: {}

        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/PUBLIC/multiconslist' )
            .then( function( response ) {
                self.setState( {
                    consituencies: response.data
                });
            })

        axios.get( 'api/PUBLIC/multiDetails/' )
            .then( function( response ) {
                self.setState( {
                    info: response.data
                });
            })
        axios.get( '/api/PUBLIC/multicons' ).then( function( response ) {
            self.setState( {
                parties: response.data
            });
        })
            .then( function() {

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

    handleAdministerDistricts: function( id ) {
        var self = this;
        return function() {
            self.context.router.push( '/multidisresult/' + id );
        }
    },

    partiesTitle: function( parties ) {
        var titleList = parties.map( function( party, index ) {
                return ( party.shortTitle );
           
        });
        console.log(titleList);
        return titleList;
    },

    partiesMandates: function( parties ) {
        var mandatesList = parties.map( function( party, index ) {
            if (party.numberOfMandates) {
                console.log(party.numberOfMandates);
                return ( party.numberOfMandates );
            }
        });
        return mandatesList;
    },


    render: function() {
        return (

            <div>
                <MultiElectionsResultsComponent
                    consituencies={this.state.consituencies}
                    onAdministerDistricts={this.handleAdministerDistricts}
                    parties={this.state.parties}
                    info={this.state.info}
                    />
                 </div>
        )
    }
});

MultiElectionsResultsContainer.contextTypes = {
    router: React.PropTypes.object.isRequired
};

window.MultiElectionsResultsContainer = MultiElectionsResultsContainer;