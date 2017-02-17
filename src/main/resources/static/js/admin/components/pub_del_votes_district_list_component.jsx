
var PubDelVotesDistrictListComponent = React.createClass( {

    getMultiVotesByDistrict: function( districtId ) {
        var multiVotesList = [];
        this.props.multiVotes.map( function( multiVote, index ) {
            if ( multiVote.district.id == districtId && multiVote.deleted_date == null ) {
                multiVotesList.push( multiVote );
            }
        });
        return multiVotesList;
    },



    render: function() {
        this.props.multiVotes.map( function( multiVote, index ) {
            //console.log(multiVote);

        });


        var self = this;
        var districtList = this.props.districts.map( function( district, index ) {

            var modalId = "modal" + district.id;
            var modalIdHash = "#modal" + district.id;

            console.log( index + ' ' + district.id + ' ' + self.getMultiVotesByDistrict( district.id ).length );
            var disabledMultiList = true;
            var disabledMultiPublish = true;
            var disabledMultiDelete = true;
            if ( self.getMultiVotesByDistrict( district.id ).length > 0 ) {
                disabledMultiDelete = false;
                disabledMultiList = false;
                if ( self.getMultiVotesByDistrict( district.id )[0].published_date == null ) {
                    disabledMultiPublish = false;
                }
            }
            
            var multiVotesList = self.getMultiVotesByDistrict( district.id ).map( function (multiVote, index) {
               
                return (
                       <tr key={index}>
                           <td>{multiVote.party.title}</td>
                           <td>{multiVote.votes}</td>
                       </tr>
               )
            });

            return (

                <tr key={index}>
                    <td>{district.title}</td>
                    <td><button type="button" className="btn btn-default" disabled >Peržiūrėti balsus</button></td>
                    <td><button type="button" className="btn btn-primary" onClick={self.props.onPublishSingleVotes( district.id )} disabled >Publikuoti balsus</button></td>
                    <td><button type="button" className="btn btn-danger" onClick={self.props.onDeleteSingleVotes( district.id )} disabled >Trinti balsus</button></td>
                    
                    <td><button type="button" className="btn btn-default" data-toggle="modal" data-target={modalIdHash} disabled={disabledMultiList}>Peržiūrėti balsus</button>
                        <div className="modal fade" id={modalId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">{district.title} apylinkės balsai</h4>
                                    </div>
                                    <div className="modal-body">
                                        
                                        <table className="table">
                                        <thead>
                                            <tr>
                                                <th>Partija</th>
                                                <th>Balsai</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {multiVotesList}
                                        </tbody>
                                    </table>

                                </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Uždaryti</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td><button type="button" className="btn btn-primary" onClick={self.props.onPublishMultiVotes( district.id )} disabled={disabledMultiPublish}>Publikuoti balsus</button></td>
                    <td><button type="button" className="btn btn-danger" onClick={self.props.onDeleteMultiVotes( district.id )} disabled={disabledMultiDelete} >Trinti balsus</button></td>


                </tr>



            )
        });

        return (
            <div className="panel panel-default">
                <h3>{this.props.constit.title}</h3>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Apylinkės pavadinimas</th>
                            <th colSpan="3" style={{ textAlign: "center" }}>Vienamandatės</th>
                            <th colSpan="3" style={{ textAlign: "center" }}>Daugiamandatės</th>
                        </tr>
                    </thead>
                    <tbody>
                        {districtList}
                    </tbody>
                </table>

            </div>
        )
    }
});




window.PubDelVotesDistrictListComponent = PubDelVotesDistrictListComponent;