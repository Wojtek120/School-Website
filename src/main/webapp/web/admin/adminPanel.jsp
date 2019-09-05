<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 05.09.19
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
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
    <h1>Go to:</h1>
    <a href="">
        <h3>Exercise list</h3>
    </a>
    <a href="/admin/groups">
        <h3>User groups list</h3>
    </a>
    <a href="">
        <h3>Users list</h3>
    </a>



</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
