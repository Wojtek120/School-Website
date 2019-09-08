<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 08.09.19
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Exercise list</title>

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
    <h2>Exercise list</h2>
    <h4>  <a href='<c:url value="/admin/exercise/add"/>'>Add new exercise</a> </h4>
    <table>
        <tr>
            <th>Exercise title</th>
            <th>Exercise description</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${exercises}" var="exercise" varStatus="status">
            <tr>
                <td>${exercise.title}</td>
                <td>${exercise.description}</td>
                <td>
                    <a href='<c:url value="/admin/exercise/edit?id=${exercise.id}"/>'>Edit</a>
                    <a href='<c:url value="/admin/exercise/delete?id=${exercise.id}"/>'>Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/web/general/footer.jsp"%>
</body>
</html>
