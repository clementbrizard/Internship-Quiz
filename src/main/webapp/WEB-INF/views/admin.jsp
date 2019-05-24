<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="loggedUserName" value="${pageContext.request.userPrincipal.name}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <%--    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css"/>
    <%--
        <link rel="stylesheet" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
    --%>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/bs4/dt-1.10.18/af-2.3.3/b-1.5.6/b-colvis-1.5.6/b-flash-1.5.6/b-print-1.5.6/cr-1.5.0/r-2.2.2/rr-1.2.4/sc-2.0.0/sl-1.3.0/datatables.min.css"/>

</head>
<body>
<div class="container col-md-12">

    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2 class="text-center">Welcome ${loggedUserName} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
    </h2>


    <h1 class="text-center">Admin</h1>

    <h2 class="text-center">All Users</h2>
    <div class="col-md-12">
        <table id="users" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Mail</th>
                <th>Company</th>
                <th>Phone</th>
                <th>Creation Date</th>
                <th>Active</th>
                <th>Action</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.username}</td>
                    <td>${item.firstName}</td>
                    <td>${item.secondName} ${userDetailsService.loadUserByUsername(item.username).getAuthorities()}</td>
                    <td>${item.mail}</td>
                    <td>${item.company}</td>
                    <td>${item.phone}</td>
                    <td>${item.creationDate}</td>
                    <td>${item.valid}</td>
                    <td>
                        <c:if test="${item.username != loggedUserName}">
                            <c:choose>
                                <c:when test="${item.valid==true}">
                                    <form id="disableForm" method="POST" action="${contextPath}/disable/${item.id}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <button type="button" class="btn btn-warning"><i class="far fa-edit"></i> Edit
                                    </button>
                                    <button type="button" class="btn btn-danger"><i class="far fa-edit"></i> Delete
                                    </button>
                                    <a onclick="document.forms['disableForm'].submit()" class="btn btn-info"><i
                                            class="fas fa-user-slash"></i> Disable</a>
                                </c:when>
                                <c:otherwise>
                                    <form id="disableForm" method="POST" action="${contextPath}/disable/${item.id}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <button type="button" class="btn btn-warning"><i class="far fa-edit"></i> Edit
                                    </button>
                                    <button type="button" class="btn btn-danger"><i class="far fa-edit"></i> Delete
                                    </button>
                                    <a onclick="document.forms['disableForm'].submit()" class="btn btn-info">
                                        <i class="fas fa-user-check"></i> Enable</a>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="container text-center">
            <button type="button" class="btn btn-success"><i class="fas fa-plus"></i> Add user</button>
            <form id="managerForm" method="POST" action="${contextPath}/forms">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <a onclick="document.forms['managerForm'].submit()" class="btn btn-success"><i
                    class="fas fa-list-ol"></i> Manage forms</a>
        </div>
    </div>


    <script type="text/javascript" charset="utf8"
            src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" charset="utf8"
            src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
    <script type="text/javascript"
            src="https://cdn.datatables.net/v/bs4/dt-1.10.18/af-2.3.3/b-1.5.6/b-colvis-1.5.6/b-flash-1.5.6/b-print-1.5.6/cr-1.5.0/r-2.2.2/rr-1.2.4/sc-2.0.0/sl-1.3.0/datatables.min.js"></script>

    <script>
        $(function () {
            $("#users").dataTable({
                rowReorder: true,
                colReorder: true,
                search: {
                    smart: false
                }
            });

        })
    </script>

    <%--</div>--%>
    <!-- /container -->

    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
    <%--
    <script src="${contextPath}/resources/js/jqueryScript.js"></script>
    --%>
</body>
</html>