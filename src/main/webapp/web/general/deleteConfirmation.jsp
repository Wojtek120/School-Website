<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 25.08.19
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>

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
    <form class="text-center" action="/solution/delete?id=${id}" method="post">
        <h2>Are you sure you want to delete ${name}?</h2>
        <button class="btn btn-color rounded-0" type="button" name="noButton" onclick="history.back()">No </button>
        <button class="btn btn-color rounded-0" type="submit" name="yesButton">Yes</button>
    </form>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
