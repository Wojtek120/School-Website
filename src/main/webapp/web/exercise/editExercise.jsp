<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 08.09.19
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add exercise</title>

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
    <h2>Edit exercise</h2>

    <form class="text-center" action="/admin/exercise/edit?id=${exercise.id}" method="post">
        <div class="form-group">
            <label for="exerciseTitle">Exercise title</label>
            <input type="text" class="form-control" name="exerciseTitle" id="exerciseTitle" value="${exercise.title}">
        </div>
        <div class="form-group">
            <label for="exerciseDescription">Exercise description</label>
            <input type="text" class="form-control" name="exerciseDescription" id="exerciseDescription" value="${exercise.description}">
        </div>
        <button class="btn btn-color rounded-0" type="submit">Edit exercise</button>
    </form>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>