var DeleteVotesComponent = React.createClass( {
    render: function() {

        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
            return (
                <option key={index}>{constituency.title}</option>
            );
        });
        var districtList = this.props.districts.map( function( district, index ) {
            return (
                <option key={index} value={district.id}>{district.title}</option>
            );
        });
        return (

            <form>
                <label>Apygarda (neveikia)</label><br />
                <select className="form-control" disabled>
                    <option value="base">Pasirinkite apygardą</option>
                    {constituencyList}
                </select>
                <label>Apylinkė</label><br />
                <select className="form-control" value={this.props.districtVal} onChange={this.props.onDistrictChange}>
                    <option value="base">Pasirinkite apylinkę</option>
                    {districtList}
                </select>
                <label>Rinkimų tipas (neveikia)</label><br />
                <select className="form-control" value={this.props.electionType} onChange={this.props.onTypeChange}>
                    <option value="base">Pasirinkite rinkimų tipą</option>
                    <option value="single">Vienmandate</option>
                    <option value="multi">Daugiamandate</option>
                </select>


                <button type="button" className="btn btn-primary btn-danger" data-toggle="modal" data-target="#modal">
                    Anuliuoti rezultatus
              </button>
                <div className="modal fade" id="modal" tabIndex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
                                <h4 className="modal-title" id="myModalLabel">Dėmesio!</h4>
                            </div>
                            <div className="modal-body">
                                Ar tikrai norite ištrinti apygardą?
                          </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-default" data-dismiss="modal">Atšaukti</button>
                                <button type="button" className="btn btn-danger" onClick={this.props.onDeleteVotes} data-dismiss="modal">Ištrinti</button>
                            </div>
                        </div>
                    </div>
                </div>


            </form>

        )
    }
});

window.DeleteVotesComponent = DeleteVotesComponent;