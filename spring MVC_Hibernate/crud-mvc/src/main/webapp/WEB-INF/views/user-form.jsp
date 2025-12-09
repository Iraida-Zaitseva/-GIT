<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${user.id != null ? 'Редактировать' : 'Добавить'} пользователя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">${user.id != null ? 'Редактировать' : 'Добавить'} пользователя</h1>
    <c:url var="formAction" value="${user.id != null ? '/update' : '/save'}" />
    <form:form action="${formAction}" method="post" modelAttribute="user">
        <form:hidden path="id"/>
        <div class="mb-3">
            <label for="name" class="form-label">Имя</label>
            <form:input path="name" id="name" class="form-control" required="true"/>
            <form:errors path="name" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <form:input path="email" id="email" class="form-control" type="email" required="true"/>
            <form:errors path="email" cssClass="text-danger"/>
        </div>
        <button type="submit" class="btn btn-success">${user.id != null ? 'Обновить' : 'Сохранить'}</button>
        <a href="<c:url value='/'/>" class="btn btn-secondary">Отмена</a>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
