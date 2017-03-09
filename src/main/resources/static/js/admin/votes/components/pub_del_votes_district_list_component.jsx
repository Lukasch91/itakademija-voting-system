
var PubDelVotesDistrictListComponent = React.createClass( {

    getSingleVotesByDistrict: function( districtId ) {
        var singleVotesList = [];
        this.props.singleVotes.map( function( singleVote, index ) {
            if ( singleVote.singleDistrict.id == districtId && singleVote.singleDeletedDate == null ) {
                singleVotesList.push( singleVote );
            }
        });
        return singleVotesList;
    },

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

        var self = this;
        var districtList = this.props.districts.map( function( district, index ) {

            var modalSingleListId = "modalSingleList" + district.id;
            var modalSingleListIdHash = "#modalSingleList" + district.id;
            var modalSinglePublishId = "modalSinglePublish" + district.id;
            var modalSinglePublishIdHash = "#modalSinglePublish" + district.id;
            var modalSingleDeleteId = "modalSingleDelete" + district.id;
            var modalSingleDeleteIdHash = "#modalSingleDelete" + district.id;

            var modalMultiListId = "modalMultiList" + district.id;
            var modalMultiListIdHash = "#modalMultiList" + district.id;
            var modalMultiPublishId = "modalMultiPublish" + district.id;
            var modalMultiPublishIdHash = "#modalMultiPublish" + district.id;
            var modalMultiDeleteId = "modalMultiDelete" + district.id;
            var modalMultiDeleteIdHash = "#modalMultiDelete" + district.id;

            var disabledSingleList = true;
            var disabledSinglePublish = true;
            var disabledSingleDelete = true;
            if ( self.getSingleVotesByDistrict( district.id ).length > 0 ) {
                disabledSingleDelete = false;
                disabledSingleList = false;
                if ( self.getSingleVotesByDistrict( district.id )[0].singlePublishedDate == null ) {
                    disabledSinglePublish = false;
                }
            }

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

            var singleVotesList = self.getSingleVotesByDistrict( district.id ).map( function( singleVote, index ) {
                return (
                    <tr key={index}>
                        <td>{singleVote.singleCandidate.candidateName} {singleVote.singleCandidate.candidateSurname}</td>
                        <td>{singleVote.singleVotes}</td>
                    </tr>
                )
            });
            
            var multiVotesList = self.getMultiVotesByDistrict( district.id ).map( function( multiVote, index ) {
                return (
                    <tr key={index}>
                        <td>{multiVote.party.title}</td>
                        <td>{multiVote.votes}</td>
                    </tr>
                )
            });
            var testDisabled = self.props.disableTest;
            return (

                <tr key={index}>
                    <td>{district.title}</td>
                    <td><button type="button" className="btn btn-xs btn-success" data-toggle="modal" data-target={modalSingleListIdHash} disabled={disabledSingleList} >Peržiūrėti balsus</button>
                        <div className="modal fade" id={modalSingleListId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">{district.title} apylinkės balsai vienmandatėse apygardose</h4>
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
                                                {singleVotesList}
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

                    <td><button type="button" className="btn btn-xs  btn-primary" data-toggle="modal" data-target={modalSinglePublishIdHash} disabled={disabledSinglePublish} >Publikuoti balsus</button>
                        <div className="modal fade" id={modalSinglePublishId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                                    </div>
                                    <div className="modal-body">
                                        Ar tikrai norite publikuoti {district.title} apylinkės balsus vienmandatėje apygardoje?
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                                        <button type="button" className="btn btn-default" data-dismiss="modal" onClick={self.props.onPublishSingleVotes( district.id )} >Publikuoti</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>

                    <td><button type="button" className="btn btn-xs btn-danger" data-toggle="modal" data-target={modalSingleDeleteIdHash} disabled={disabledSingleDelete} >Trinti balsus</button>
                    <div className="modal fade" id={modalSingleDeleteId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                            </div>
                            <div className="modal-body">
                                Ar tikrai norite trinti {district.title} apylinkės balsus vienmandatėje apygardoje?
                </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                                <button type="button" className="btn btn-default" data-dismiss="modal" onClick={self.props.onDeleteSingleVotes( district.id )} >Trinti</button>
                            </div>
                        </div>
                    </div>
                </div>
                    </td>

                    <td><button type="button" className="btn btn-xs btn-success" data-toggle="modal" data-target={modalMultiListIdHash} disabled={disabledMultiList}>Peržiūrėti balsus</button>
                        <div className="modal fade" id={modalMultiListId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">{district.title} apylinkės balsai daugiamandatėje apygardoje</h4>
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

                    <td><button type="button" className="btn btn-xs btn-primary" data-toggle="modal" data-target={modalMultiPublishIdHash} disabled={disabledMultiPublish}>Publikuoti balsus</button>
                        <div className="modal fade" id={modalMultiPublishId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                                    </div>
                                    <div className="modal-body">
                                        Ar tikrai norite publikuoti {district.title} apylinkės balsus daugiamandatėje apygardoje?
                        </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                                        <button type="button" className="btn btn-default" data-dismiss="modal" onClick={self.props.onPublishMultiVotes( district.id )} >Publikuoti</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>

                    <td><button type="button" className="btn btn-xs btn-danger" data-toggle="modal" data-target={modalMultiDeleteIdHash} disabled={disabledMultiDelete} >Trinti balsus</button>
                        <div className="modal fade" id={modalMultiDeleteId} tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                        <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                                    </div>
                                    <div className="modal-body">
                                        Ar tikrai norite trinti {district.title} apylinkės balsus daugiamandatėje apygardoje?
                        </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                                        <button type="button" className="btn btn-default" data-dismiss="modal" onClick={self.props.onDeleteMultiVotes( district.id )}  >Trinti</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>


                </tr>



            )
        });

        return (
            <div>
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