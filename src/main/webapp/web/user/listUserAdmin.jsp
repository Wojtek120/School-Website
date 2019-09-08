<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 06.09.19
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User list</title>
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
    <h2>Users lsit</h2>
    <h4>  <a href='<c:url value="/admin/user/add"/>'>Add new user</a> </h4>
    <table>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <a href='<c:url value="/users/details?userId=${user.id}&groupId=${user.userGroupId}"/>'>Details</a>
                    <a href='<c:url value="/admin/user/edit?id=${user.id}"/>'>Edit</a>
                    <a href='<c:url value="/admin/user/delete?id=${user.id}"/>'>Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
