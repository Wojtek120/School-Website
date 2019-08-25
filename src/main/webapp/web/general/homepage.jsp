<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Homepage</title>

    <style>
        <%@ include file="/css/style.css"%>
    </style>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
<h2>Recent solution list</h2>
<h4>  <a href='<c:url value="/solution/add"/>'>Add new solution</a> </h4>
<table>
    <tr>
        <th>Exercise</th>
        <th>Solution</th>
        <th>Created</th>
        <th>Updated</th>
        <th>User</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${solutions}" var="solution" varStatus="status">
        <tr>
            <td>${exercises.get(status.index).title}</td>
            <td>${solution.description}</td>
            <td>${solution.created}</td>
            <td>${solution.updated}</td>
            <td>${users.get(status.index).username}</td>
            <td>
                <a href='<c:url value="/solution/delete?id=${solution.id}"/>'>Delete</a>
                <a href='<c:url value="/solution/edit?id=${solution.id}"/>'>Edit</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
