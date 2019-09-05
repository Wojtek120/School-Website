<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 26.08.19
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Group list</title>

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
    <h2>Group list</h2>
    <h4>  <a href='<c:url value="/group/add"/>'>Add new group</a> </h4>
    <table>
        <tr>
            <th>Group name</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${groups}" var="group" varStatus="status">
            <tr>
                <td>${group.name}</td>
                <td>
                    <a href='<c:url value="/group/users?id=${group.id}"/>'>Users</a>
                    <a href='<c:url value="/group/edit?id=${group.id}"/>'>Edit</a>
<%--                    <a href='<c:url value="/group/delete?id=${group.id}"/>'>Delete</a>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
