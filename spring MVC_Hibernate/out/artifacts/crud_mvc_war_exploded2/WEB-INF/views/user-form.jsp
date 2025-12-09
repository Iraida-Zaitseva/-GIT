<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Форма пользователя</title>
</head>
<body>
<h2>Пользователь</h2>
<c:choose>
    <c:when test="${user.id != null}">
        <form action="update" method="post">
            <input type="hidden" name="id" value="${user.id}"/>
    </c:when>
    <c:otherwise>
        <form action="save" method="post">
    </c:otherwise>
</c:choose>

    Имя: <input type="text" name="name" value="${user.name}"/><br/>
    Email: <input type="text" name="email" value="${user.email}"/><br/>
    <input type="submit" value="Сохранить"/>
</form>
<a href="/">Назад к списку</a>
</body>
</html>
