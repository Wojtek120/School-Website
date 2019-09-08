<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Solution list</title>

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
    <h2>Solutions list</h2>
    <h4>  <a href='<c:url value="/admin/solution/add"/>'>Add new solution</a> </h4>
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
                    <a href='<c:url value="/solution/details?id=${solution.id}"/>'>Details</a>
                    <a href='<c:url value="/admin/solution/edit?id=${solution.id}"/>'>Edit</a>
                    <a href='<c:url value="/admin/solution/delete?id=${solution.id}"/>'>Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
