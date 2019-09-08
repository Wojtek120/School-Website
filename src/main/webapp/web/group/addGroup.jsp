<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 05.09.19
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add solution</title>

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
    <h2>Add new group</h2>

    <form class="text-center" action="/admin/group/add" method="post">
        <div class="form-group">
            <label for="groupName">Name of group</label>
            <input type="text" class="form-control" name="GroupName" id="groupName">
        </div>
        <div class="form-group">
            <label for="chooseUser">Users</label>
            <select multiple class="form-control" id="chooseUser" name="chooseUser">
                <c:forEach items="${users}" var="user">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-color rounded-0" type="submit">Add group</button>
    </form>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
