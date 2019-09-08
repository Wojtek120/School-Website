<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 08.09.19
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit user</title>
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
    <h2>Add new user</h2>

    <form class="text-center" action="/admin/user/edit?id=${user.id}" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" value="${user.username}">
        </div>
        <div class="form-group">
            <label for="email">E-mail</label>
            <input type="text" class="form-control" name="email" id="email"  value="${user.email}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" value="${user.password}">
        </div>
        <div class="form-group">
            <label for="groupName">Group</label>
            <select class="form-control" id="groupName" name="groupId">
                <c:forEach items="${groups}" var="group">
                    <option value="${group.id}" ${user.userGroupId == group.id ? 'selected="selected"' : ''}>${group.name}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-color rounded-0" type="submit">Edit user</button>
    </form>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
