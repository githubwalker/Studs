<%--
  Created by IntelliJ IDEA.
  User: andrew
  Date: 15.05.2016
  Time: 2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>

    <c:set var="serverpath" scope="session" value="${pageContext.request.contextPath}"/>
    <c:set var="contextPath" scope="session" value="${pageContext.request.contextPath}"/>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <c:set var="serverpath" scope="session" value="${pageContext.request.contextPath}"/>

    <title>Please login</title>

    <!-- Bootstrap core CSS -->
    <link href="${serverpath}/scripts/bootstrap-3.3.6/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${serverpath}/scripts/custom/signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>


<div class="container" style="width: 300px;">
    <form action="${serverpath}/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="andrew">
        <input type="password" class="form-control" name="j_password" placeholder="Password" required value="1234">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>
</div>



<!--
<div class="container">

    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>

    </form>
</div>
-->


</body>
</html>
