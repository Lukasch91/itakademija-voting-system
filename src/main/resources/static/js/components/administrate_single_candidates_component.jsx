var AdministrateSingleCandidatesComponent = React.createClass( {

    /*

    <form role="form" class="form" onsubmit="return false;">
      <div class="form-group">
        <label for="file">File</label>
        <input id="file" type="file" class="form-control"/>
      </div>
      <button id="upload" type="button" class="btn btn-primary">Upload</button>
    </form>

    <div id="output" class="container"></div>

    <script src="/axios.min.js"></script>
    
    
    
    <script>
      (function () {
        var output = document.getElementById('output');
        
        
        document.getElementById('upload').onclick = function () {
          var data = new FormData();
          data.append('foo', 'bar');
          data.append('file', document.getElementById('file').files[0]);
          var config = {
            onUploadProgress: function(progressEvent) {
              var percentCompleted = Math.round( (progressEvent.loaded * 100) / progressEvent.total );
            }
          };
          axios.put('/upload/server', data, config)
            .then(function (res) {
              output.className = 'container';
              output.innerHTML = res.data;
            })
            .catch(function (err) {
              output.className = 'container text-danger';
              output.innerHTML = err.message;
            });
        };
      })();
    </script>
  </body>
</html>
   
    */



    render: function() {

        var self = this;
        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
            return (
                <tr key={index}>
                    <td>{constituency.title}</td>

                    <td>
                        <form className="form">
                            <div>
                                <b>{constituency.id}</b>
                                <input id="file" type="file" className="form-control" />
                            </div>

                            <button onClick={self.props.onAddDistrictCandidates} className="btn btn-primary">Upload</button>

                        </form>



                    </td>


                </tr>
            );
        });

        return (
            <div className="panel panel-default">
                <h4>Vienmandatės</h4>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>Apygardos pavadinimas</th>

                            <th>Pridėti kandidatus</th>
                        </tr>
                    </thead>
                    <tbody>
                        {constituencyList}

                    </tbody>
                </table>



            </div>
        )
    }
});

AdministrateSingleCandidatesComponent.propTypes = {
    onAddDistrictCandidates: React.PropTypes.func.isRequired
};


window.AdministrateSingleCandidatesComponent = AdministrateSingleCandidatesComponent;