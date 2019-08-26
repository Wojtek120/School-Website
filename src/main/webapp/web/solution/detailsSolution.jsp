<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 25.08.19
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details of solution</title>

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
    <h2>Solution details</h2>
    <br>

    <h3>Name of exercise: </h3>
    <p>${exercise.title}</p>

    <h3>Solution: </h3>
    <p>${solution.description}</p>

    <h3>User: </h3>
    <p>${user.username}</p>

    <h3>Created:</h3>
    <p>${solution.created}</p>


    <h3>Updated:</h3>
    <p>${solution.updated}</p>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
