var ApiComponent = React.createClass( {
    render: function() {
        return (
            <div>
                <h2>API</h2>


                <table>
                    <tbody>
                        <tr>
                            <th colspan="1">Konsoliduoti rezultatai:</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>Konsoliduoti balsavimo rezultatai (partijos ir mandatai)</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/consolidatedResults/">http://localhost:8080/api/PUBLIC/consolidatedResults/</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Išrinktų seimo narių sarašas</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/members/">http://localhost:8080/api/PUBLIC/members/</a>
                            </td>
                        </tr>
                    </tbody>
                </table>


                <table>
                    <tbody>
                        <tr>
                            <th colspan="1">Rezultatai vienmandatės apygardose:</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>Vienmandačių apygardų balsavimo suvestinė</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/constresults">http://localhost:8080/api/PUBLIC/constresults</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Vienmandačių apygardų balsavimo informacija</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/singledetails/">http://localhost:8080/api/PUBLIC/singledetails/</a>
                            </td>
                        </tr>
                    </tbody>
                </table>


                <table>
                    <tbody>
                        <tr>
                            <th colspan="1">Rezultatai daugiamandatėse apygardose:</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td>Daugiamandačių apygardų balsavimo suvestinė</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/multicons">http://localhost:8080/api/PUBLIC/multicons</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Daugiamandačių apygardų balsavimo suvestinė</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/multiconslist">http://localhost:8080/api/PUBLIC/multiconslist</a>
                            </td>
                        </tr>
                        <tr>
                            <td>Daugiamandačių apygardų balsavimo informacija</td>
                            <td>
                                <a href="http://localhost:8080/api/PUBLIC/multiDetails/">http://localhost:8080/api/PUBLIC/multiDetails/</a>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div>
        )
    }
});

window.ApiComponent = ApiComponent;