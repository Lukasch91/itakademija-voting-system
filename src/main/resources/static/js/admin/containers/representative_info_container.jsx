var RepresentativeInfoContainer = React.createClass( {

    getInitialState: function() {
        return {
            representative: [],
            constituency: 'test',
            district: {}
        };
    },

    componentWillMount: function() {
        var self = this;
        var repId = this.props.params.repId;
        var conId = this.props.params.conId;

        axios.all( [axios.get( '/api/ADMIN/representative/' + repId ), axios.get( '/api/ADMIN/constituency/' + conId )] )
            .then( axios.spread( function( repResponse, constResponse ) {
                self.findRepresentativesDistrict( repResponse.data, constResponse.data );
                self.setState( {
                    representative: repResponse.data,
                    constituency: constResponse.data
                });
            }) );



    },

    findRepresentativesDistrict: function( representative, constituency ) {
        var self = this;
        var districtid = representative.districtId;
        var districts = constituency.districts;
        for ( var i = 0; i < districts.length; i++ ) {
            if ( districts[i].id == districtid ) {
                self.setState( { district: districts[i] });
            }
        }
    },

    handleDelete: function() {
        var self = this;
        var repId = this.props.params.repId;

        axios.delete( '/api/ADMIN/representative/' + repId ).then( function( response ) {
            console.log( 'item deleted' );
            var district = self.state.representative.districtId;
            console.log( district );
            self.context.router.push( '/dis/' + self.props.params.conId );
        });
    },

    handleCancel: function() {
        var self = this;
        self.context.router.push( '/dis/' + self.props.params.conId );


    },

    render: function() {
        return <RepresentativeInfoComponent
            representative={this.state.representative}
            constituency={this.state.constituency}
            district={this.state.district}
            onDelete={this.handleDelete}
            onCancel={this.handleCancel}
            />
    }
});

RepresentativeInfoContainer.contextTypes = {
    router: React.PropTypes.object.isRequired,
};

window.RepresentativeInfoContainer = RepresentativeInfoContainer;