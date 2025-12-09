<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<h2>Пользователи</h2>
<a href="add">Добавить нового пользователя</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
        <th>Действия</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <form action="edit" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Редактировать"/>
                </form>
                <form action="delete" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Удалить"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
