var ApiComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <h1>API</h1>
                
                <h3>Konsoliduoti rezultatai:</h3>
                    <table>
                        <tbody>
                            <tr>
                                <td>Description</td>
                                <td>
                                    <a href="http://localhost:8080/api/PUBLIC/consolidatedResults/">getSortedPartyList()</a>
                                </td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td>
                                    <a href="http://localhost:8080/api/PUBLIC/members/">getSortedMemberOfParlList()</a>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                
                <h3>Vienmandačių rezultatai:</h3>
                <table>
                    <tbody>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/constresults">singleElectionConstituencyResults</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/districtresults/{id}">singleElectionDistrictResults(id)</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/candidatesresults/{id}">getSingleElectionResults(id)</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/constresults/{id}">getConstiteuncyResult(id)</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/constresultsdis/{id}">getConstituencyIdByDistrictId(id)</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/districtdetails/{id}">getSingleElectionResultInDistrict(id)</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/singledetails/">getSingleElectionDetails()</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/singleDistrictDetails/{id}">getDistrictElectionDetails(id)</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
        
        <h3>Daugiamandačių rezultatai:</h3>
            <table>
                <tbody>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multicons">getMultiElectionResults()</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multiconslist">getMultiElectionConstituencyList()</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multidistlist/{id}">getResultsOfDistricts(id)</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multidis/{id}">getDistrictPartiesResults(id)</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multicons/{id}">getConstituencyPartiesResults(id)</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multiDetails/">getMultiElectionDetails()</a>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>
                            <a href="http://localhost:8080/api/PUBLIC/multiDistrictDetails/{id}">getDistrictElectionDetails(id)</a>
                        </td>
                    </tr>
                    
                </tbody>
            </table>

            </div>
        )
    }
});

window.ApiComponent = ApiComponent;