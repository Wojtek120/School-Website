<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 27.08.19
  Time: 00:36
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
    <h2>Users that belongs to ${group.name}</h2>
    <%--    <h4>  <a href='<c:url value="/solution/add"/>'>Add new solution</a> </h4>--%>
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
                    <a href='<c:url value="/users/details?userId=${user.id}&groupId=${group.id}"/>'>Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
