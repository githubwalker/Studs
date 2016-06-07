<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <c:set var="serverpath" scope="session" value="${pageContext.request.contextPath}"/>

    <script src="${serverpath}/scripts/jtable.2.4.0/external/json2.js" type="text/javascript"> </script>

    <link href="${serverpath}/scripts/jquery-ui-1.11.4/jquery-ui.css" rel="stylesheet" type="text/css" />
    <link href="${serverpath}/scripts/jtable.2.4.0/themes/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
    <link href="${serverpath}/scripts/bootstrap-3.3.6/css/bootstrap.css" rel="stylesheet" />

    <style type="text/css">
        button, select {
            text-transform: inherit;
            color: black;
        }
    </style>


    <script src="${serverpath}/scripts/jquery-2.2.0.js" type="text/javascript" > </script>
    <script src="${serverpath}/scripts/bootstrap-3.3.6/js/bootstrap.js" type="text/javascript" > </script>


    <script src="${serverpath}/scripts/jquery-ui-1.11.4/jquery-ui.js" type="text/javascript" > </script>
    <script src="${serverpath}/scripts/jtable.2.4.0/jquery.jtable.js" type="text/javascript" > </script>

    <script src="${serverpath}/scripts/custom/helpers.js" type="text/javascript"></script>


</head>

<body>

<div id="myAddUpdateDialog" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Add/Update record</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label" for="inputName">Student name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputName" placeholder="Student name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="inputAge" >Age</label>
                        <div class="col-sm-10">
                            <input type="number" class="form-control" id="inputAge" placeholder="Student Age"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> <!-- data-dismiss="modal" -->
                <button type="button" class="btn btn-primary" id = "OKbtn">Save changes</button> <!-- data-toggle="modal" -->
                <!-- <button type="button" class="btn btn-primary">OK</button> -->
            </div>
        </div>
    </div>
</div>

<div id="myDeleteDialog" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirm record removal</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <p class="col-sm-12">Do you really want to remove record <label  id="record_placeholder"></label> ? </p>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button> <!-- data-dismiss="modal" -->
                <button type="button" class="btn btn-primary" id = "OKbtnDelete">Confirm Removal</button> <!-- data-toggle="modal" -->
            </div>
        </div>
    </div>
</div>

<form id="logout_form" action="${serverpath}/j_spring_security_logout" name="f" method="POST" hidden>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<nav class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Welcome to Students editor <sec:authentication property="name"/>!</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><sec:authentication property="name"/> : </a></li>
                <li><a href="javascript:$('#logout_form').submit()">Log out >></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>


<div class="container">
    <div>
        <h1> Edit students list </h1>
        <div id="PeopleTableContainer" style="width: 100%;"></div>
    </div>
</div>

<script type="text/javascript">

    function convert2jsonstring( urlparams_string )
    {
        var js_parsed = urlparams_string
                        ?
                        JSON.parse('{"' + urlparams_string.replace(/&/g, '","').replace(/=/g,'":"') + '"}',
                                function(key, value) { return key===""?value:decodeURIComponent(value) })
                        :
                {}
                ;

        return JSON.stringify( js_parsed );
    }

    $(document).ready(function () {

        var ppc = $('#PeopleTableContainer');

        ppc.jtable({
            title: 'Table of students',
            paging:true,
            pageSize: 10,

            actions: {
                listAction: function (postData, jtParams) {
                    console.log("Loading from custom function...");
                    return $.Deferred(function ($dfd) {
                        $.ajax({
                            url: '${serverpath}/springrestapi/students/getPage/' + jtParams.jtStartIndex + '/' + jtParams.jtPageSize,
                            // beforeSend: function (request) { request.setRequestHeader( "_csrf", "${_csrf.token}" ); },
                            type: 'POST',
                            dataType: 'json',
                            data: postData,
                            success: function (data) {
                                $dfd.resolve(data);
                            },
                            error: function () {
                                $dfd.reject();
                            }
                        });
                    });
                }
            },
            fields: {
                PersonId: {
                    title: 'Student Id',
                    key: true,
                    create: false,
                    edit: false,
                    list: true
                },
                Name: {
                    title: 'Student Name',
                    width: '40%'
                },
                Age: {
                    title: 'age',
                    width: '20%'
                },
                CustomUpdate: {
                    title: '',
                    width: '1%',
                    sorting: false,
                    create: false,
                    edit: false,
                    list: true,

                    display: function (data) {
                        if (data.record) {
                            var id_code = id="custom-edit-record-button" + data.record.PersonId;
                            var strret = '<button title="Edit Record" id="' + id_code + '" class="jtable-command-button jtable-edit-command-button"><span>Edit Record</span></button>';

                            function onEditClicked () {
                                var dlg = $("#myAddUpdateDialog");
                                dlg.modal('show');
                                $( ".modal-body #inputName", dlg).val( data.record.Name );
                                $( ".modal-body #inputAge", dlg).val( data.record.Age );

                                function onOKclicked () {
                                    dlg.find("#OKbtn").addClass('btn-disabled');

                                    var inName = dlg.find("#inputName").val();
                                    var inAge = dlg.find("#inputAge").val();
                                    var stud = { PersonId : data.record.PersonId, Name: inName, Age: inAge };
                                    dlg.modal('hide');

                                    $.ajax({
                                        url: '${serverpath}/springrestapi/students/update/' + data.record.PersonId,
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application/json'
                                        },
                                        type: 'POST',
                                        dataType: 'json',
                                        data: JSON.stringify(stud),
                                        success: function (server_data) {
                                            console.log('updateRecord success ...');

                                            ppc.jtable( 'updateRecord',
                                                    { record: stud, clientOnly : true }
                                            );
                                        },
                                        error: function () {
                                            console.log('createAction error ...');
                                        }
                                    });
                                };

                                dlg.find("#OKbtn").one( "click", onOKclicked );
                                dlg.one( "hidden.bs.modal", function () {
                                    dlg.find("#OKbtn").off( "click", onOKclicked );
                                } );
                            };

                            // ppc.off( "click", "#" + id_code, onEditClicked );
                            ppc.off( "click", "#" + id_code );
                            ppc.on( "click", "#" + id_code, onEditClicked );

                            return strret;
                        }
                    }
                },
                CustomDelete: {
                    title: '',
                    width: '1%',
                    sorting: false,
                    create: false,
                    edit: false,
                    list: true,

                    display: function (data) {
                        if (data.record) {
                            var id_code = id="custom-delete-record-button" + data.record.PersonId;
                            var strret = '<button title="Delete Record" id="' + id_code + '" class="jtable-command-button jtable-delete-command-button"><span>Delete Record</span></button>';

                            function onDeleteClicked () {
                                console.log("onDeleteClicked ...");

                                var dlg = $("#myDeleteDialog");
                                dlg.modal("show");
                                $( ".modal-body #record_placeholder", dlg).text( JSON.stringify(data.record) );

                                function onOKclicked_deleteDialog() {
                                    console.log("Delete record confirmed ...");
                                    dlg.find("#OKbtnDelete").addClass('btn-disabled');
                                    dlg.modal( "hide" );
                                    dlg.find("#OKbtnDelete").off( "click", onOKclicked_deleteDialog );

                                    $.ajax({
                                        url: '${serverpath}/springrestapi/students/delete/' + data.record.PersonId,
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application/json'
                                        },
                                        type: 'POST',
                                        dataType: 'json',
                                        success: function (success_data) {
                                            console.log('deleteAction success ...');
                                            // ppc.deleteRecord( { key: data.record.PersonId, clientOnly : true } );
                                            ppc.jtable( 'deleteRecord',
                                                    { key: data.record.PersonId, clientOnly : true }
                                            );
                                        },
                                        error: function () {
                                            console.log('deleteAction error ...');
                                        }
                                    });

                                }

                                dlg.find("#OKbtnDelete").one( "click", onOKclicked_deleteDialog );
                                dlg.one( "hidden.bs.modal", function () {
                                    dlg.find("#OKbtnDelete").off( "click", onOKclicked_deleteDialog );
                                } );
                            };

                            // ppc.off( "click", "#" + id_code, onDeleteClicked );
                            ppc.off( "click", "#" + id_code );
                            ppc.on( "click", "#" + id_code, onDeleteClicked );

                            return strret;
                        }
                    }
                }
            },
            toolbar: {
                items: [{
                    icon: 'scripts/jtable.2.4.0/themes/jqueryui/add.png',
                    text: 'Add New Record',
                    click: function () {

                        //perform your custom job...
                        console.log( "Add new record clicked" );

                        var dlg = $("#myAddUpdateDialog");
                        $( ".modal-body #inputName", dlg).val( "" );
                        $( ".modal-body #inputAge", dlg).val( "" );

                        dlg.find("#OKbtn").one( "click", onOKButton_inEditDialog );
                        dlg.one( "hidden.bs.modal", function () {
                            dlg.find("#OKbtn").off( "click", onOKclicked );
                        } );

                        function onOKButton_inEditDialog () {
                            dlg.find("#OKbtn").addClass('btn-disabled');

                            console.log( "hidden.bs.modal" );
                            var inName = dlg.find("#inputName").val();
                            var inAge = dlg.find("#inputAge").val();
                            dlg.modal('hide');

                            var stud = { Name : inName, Age : inAge };

                            $.ajax({
                                url: '${serverpath}/springrestapi/students/append',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                },
                                type: 'POST',
                                dataType: 'json',
                                data: JSON.stringify(stud),
                                success: function (data) {
                                    console.log('createAction success ...');

                                    ppc.jtable( 'addRecord',
                                            { record: stud, clientOnly : true }
                                    );
                                },
                                error: function () {
                                    console.log('createAction error ...');
                                }
                            });
                        };

                        dlg.modal('show');

                        console.log( "leaving" );
                    }
                }]
            }
        });

        //Load person list from server
        ppc.jtable('load');

    });

</script>

</body>
</html>
