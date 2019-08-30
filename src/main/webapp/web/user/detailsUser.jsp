<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 27.08.19
  Time: 00:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User details</title>

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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
    <h2>User details</h2>
    <br>

    <h3>Username: </h3>
    <p>${user.username}</p>

    <h3>Email: </h3>
    <p>${user.email}</p>

    <h3>Group: </h3>
    <p>${group.name}</p>

    <table>
        <tr>
            <th>Exercise</th>
            <th>Solution</th>
            <th>Created</th>
            <th>Updated</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${solutions}" var="solution" varStatus="status">
            <tr>
                <td>${exercises.get(status.index).title}</td>
                <td>${solution.description}</td>
                <td>${solution.created}</td>
                <td>${solution.updated}</td>
                <td>
                    <a href='<c:url value="/solution/details?id=${solution.id}"/>'>Details</a>
                    <a href='<c:url value="/solution/edit?id=${solution.id}"/>'>Edit</a>
                    <a href='<c:url value="/solution/delete?id=${solution.id}"/>'>Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<%@include file="/web/general/footer.jsp"%>
</body>
</html>
