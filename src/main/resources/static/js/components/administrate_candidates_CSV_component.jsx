//var AdministrateCandidatesCSVComponent = React.createClass( {
//
//    onFileChange: function( file ) {
//
//        console.log( "--1" );
//        console.log( file );
//        console.log( file.files[0] );
//        //        var xxx = document.getElementById(file).files[0];
//        //        console.log(xxx);
//        //        console.log('xxx'+xxx);
//        this.props.onHandleFileChange( xxx ); //self.refs.file.files[0]   //file.files[0]
//        console.log( "--2" );
//    },
//    //    onXXX(file){
//    //        console.log("--1");
//    //        console.log(file);
//    //        
//    //        var xxx = document.getElementById('file').files[0];
//    //        console.log(xxx);
//    //        console.log('xxx'+xxx);
//    //        this.props.onHandleFileChange(xxx); //self.refs.file.files[0]   //file.files[0]
//    //        console.log("--2");
//    //    },
//    //  onChange={self.onXXX(self)}      //onChange={self.onFileChange}     onClick={self.onFileChange(constituency.id)}
//
//    //<td><button onClick='' className="btn btn-warning">Pridėti kadidatus</button></td>
//
//
//
//    /*
//                       <td>
//                        <form className="form">
//                            <div>
//                                <b>{constituency.id}</b>
//                                <input 
//                                id={constituency.id}
//                                type="file" 
//                                name="file" 
//                                accept='.csv'
//                                
//                                
//                                className="form-control" />
//                            </div>
//
//                            <button onClick={self.props.onAddDistrictCandidates} className="btn btn-primary">Upload</button>
//
//                        </form>
//
//
//
//                    </td>
//     */
//
//    render: function() {
//
//        var self = this;
//
//
//
//        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
//            return (
//                <tr key={index}>
//                    <td>{constituency.title}</td>
//                    <td>
//                        <button onClick={() => { self.props.handleSHIT( constituency.id ) } } className="btn btn-primary">Pridėti kandidatus</button>
//
//                    </td>
//                </tr>
//            );
//        });
//
//        return (
//            <div className="panel panel-default">
//                <h4>Vienmandatės</h4>
//                <table className="table table-hover">
//                    <thead>
//                        <tr>
//                            <th>Apygardos pavadinimas</th>
//
//                            <th>Pridėti kandidatus</th>
//                        </tr>
//                    </thead>
//                    <tbody>
//                        {constituencyList}
//
//                    </tbody>
//                </table>
//
//
//
//            </div>
//        )
//    }
//});
//
//AdministrateSingleCandidatesComponent.propTypes = {
//    onAddDistrictCandidates: React.PropTypes.func.isRequired,
//    onHandleFileChange: React.PropTypes.func.isRequired,
//    handleSHIT: React.PropTypes.func.isRequired
//};
//
//
//window.AdministrateCandidatesCSVComponent = AdministrateCandidatesCSVComponent;