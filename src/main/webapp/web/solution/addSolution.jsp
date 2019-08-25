<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 25.08.19
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add solution</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Add new solution</h2>
    <form class="text-center" action="/solution/add" method="post">
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description"
                      placeholder="Solution description"></textarea>
        </div>
        <div class="form-group">
            <label for="chooseExercise">Exercise</label>
            <select class="form-control" id="chooseExercise" name="chooseExercise">
                <c:forEach items="${exercises}" var="exercise">
                    <option value="${exercise.id}">${exercise.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="chooseUser">User</label>
            <select class="form-control" id="chooseUser" name="chooseUser">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-color rounded-0" type="submit">Add solution</button>
    </form>
</div>
</body>
</html>
