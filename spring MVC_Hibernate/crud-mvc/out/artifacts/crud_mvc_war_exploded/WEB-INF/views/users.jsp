<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список пользователей</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Список пользователей</h1>
    <a href="<c:url value='/add'/>" class="btn btn-primary mb-3">Добавить нового пользователя</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Email</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>
                    <form action="<c:url value='/edit'/>" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit" class="btn btn-warning btn-sm">Редактировать</button>
                    </form>
                    <form action="<c:url value='/delete'/>" method="post" style="display:inline;" onsubmit="return confirm('Вы уверены?')">
                        <input type="hidden" name="id" value="${user.id}">
                        <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
