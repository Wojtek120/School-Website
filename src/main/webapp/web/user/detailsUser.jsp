<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 27.08.19
  Time: 00:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User details</title>

    <style>
        <%@ include file="/css/style.css"%>
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="/web/general/header.jsp"%>

<div class="container">
    <h2>User details</h2>
    <br>

    <h3>Username: </h3>
    <p>${user.username}</p>

    <h3>Email: </h3>
    <p>${user.email}</p>

    <h3>Group: </h3>
    <p>${group.name}</p>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
