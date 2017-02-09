var AdministrateSingleCandidatesContainer = React.createClass( {


    getInitialState: function() {
        return {
            constituencies: [],
            file: null //sagg
           
        };
    },

    componentWillMount: function() {
        var self = this;
        axios.get( '/api/constituency' )
            .then( function( response ) {
                self.setState( {
                    constituencies: response.data
                });
            });

    },
    //===========================================================
    onHandleFileChange: function( file ) {

        console.log( "--3" );
        this.setState( { file: file });
    },
    


    handleSHIT: function( e ) {
       
        var self = this;

        console.log( "SHIT" );
        console.log(e);

    },

    handleAddDistrictCandidates: function( e ) {
        e.preventDefault();
        var self = this;

        var header = {
            headers: {
                'Content-Type': 'multipart/form-data',
                'constituencyId': 1
            }
        };

        var file = 'nofile.aaa';
        if ( this.state.file != null ) {
            file = this.state.file;
        }
        var data = new FormData();

        data.append( 'file', file );



        console.log( "sagg__upload" );

        axios.post( '/api/districtcandidatesFILE', data, header )
            .then( function( response ) {
                console.log( "sagg_done" );
                console.log( response );
            });

    },

    //===========================================================

    render: function() {
        return (
            <div>
                <AdministrateSingleCandidatesComponent
                    onHandleFileChange={this.onHandleFileChange}
                    constituencies={this.state.constituencies}
                    onAddDistrictCandidates={this.handleAddDistrictCandidates}
                    handleSHIT={this.handleSHIT}/>
            </div>
        )
    }
});

window.AdministrateSingleCandidatesContainer = AdministrateSingleCandidatesContainer;

