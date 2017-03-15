
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
    
    getSingleInvalidVotesByDistrict: function (districtId) {
        var singleInvalidVotesList = [];
        this.props.invalidVotes.map( function( singleInvalidVote, index ) {
            if ( singleInvalidVote.district.id == districtId && singleInvalidVote.deleted_date == null && singleInvalidVote.typeMulti == false) {
                singleInvalidVotesList.push( singleInvalidVote.votes );
            }
        });
        return singleInvalidVotesList;
    },
    
    getMultiInvalidVotesByDistrict: function (districtId) {
        var multiInvalidVotesList = [];
        this.props.invalidVotes.map( function( multiInvalidVote, index ) {
            if ( multiInvalidVote.district.id == districtId && multiInvalidVote.deleted_date == null && multiInvalidVote.typeMulti == true) {
                multiInvalidVotesList.push( multiInvalidVote.votes );
            }
        });
        return multiInvalidVotesList;
    },

    timeConverter: function( timestamp ) {
        var date = new Date( timestamp );
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        if (month < 10) {
            month = '0' + month;
        }
        var day = date.getDate();
        if (day < 10) {
            day = '0' + day;
        }
        var hour = date.getHours();
        var min = date.getMinutes();
        if (min < 10) {
            min = '0' + min;
        }
        var sec = date.getSeconds();
        if (sec < 10) {
            sec = '0' + sec;
        }
        var time = year + '-' + month + '-' + day + ' ' + hour + ':' + min + ':' + sec;
        return time;
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

            var singleVotesDateEntered = self.getSingleVotesByDistrict( district.id ).map( function( singleVote, index ) {
                return (
                    <span>{self.timeConverter(singleVote.singleEnteredDate)}</span>
                )
            });
            
            var singleVotesDatePublished = self.getSingleVotesByDistrict( district.id ).map( function( singleVote, index ) {
                if (singleVote.singlePublishedDate == null) {
                    return (
                    <span>nepublikuota</span>        
                    )
                } else {
                return (
                    <span>{self.timeConverter(singleVote.singlePublishedDate)}</span>
                )
                }
            });

            var multiVotesList = self.getMultiVotesByDistrict( district.id ).map( function( multiVote, index ) {
                return (
                    <tr key={index}>
                        <td>{multiVote.party.title}</td>
                        <td>{multiVote.votes}</td>
                    </tr>
                )
            });

            var multiVotesDateEntered = self.getMultiVotesByDistrict( district.id ).map( function( multiVote, index ) {
                return (
                    <span>{self.timeConverter(multiVote.entered_date)}</span>
                )
            });
            
            var multiVotesDatePublished = self.getMultiVotesByDistrict( district.id ).map( function( multiVote, index ) {
                if (multiVote.published_date == null) {
                    return (
                    <span>nepublikuota</span>        
                    )
                } else {
                return (
                    <span>{self.timeConverter(multiVote.published_date)}</span>
                )
                }
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
                                                <tr>
                                                    <td>SUGADINTI BALSAI</td>
                                                    <td>{self.getSingleInvalidVotesByDistrict(district.id)[0]}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        Duomenys pateikti: {singleVotesDateEntered[0]}<br />
                                        Duomenys publikuoti: {singleVotesDatePublished[0]}
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
                                                <tr>
                                                    <td>SUGADINTI BALSAI</td>
                                                    <td>{self.getMultiInvalidVotesByDistrict(district.id)[0]}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        Duomenys pateikti: {multiVotesDateEntered[0]}<br />
                                        Duomenys publikuoti: {multiVotesDatePublished[0]}
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