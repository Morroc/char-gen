<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Character generator</title>
</head>
<body>

<a href="<c:url value="/view/race_manager_ajax.html" />">
    Расы
</a><br/>

<a href="<c:url value="/view/personage_manager_ajax.html" />">
    Персонажи
</a><br/>

<a href="<c:url value="/view/attached_skill_manager_ajax.html" />">
    Прикрепленные навыки
</a><br/>

<a href="<c:url value="/view/attribute_manager_ajax.html" />">
    Аттрибуты
</a><br/>

<a href="<c:url value="/view/merit_manager_ajax.html" />">
    Достоинства
</a><br/>

<a href="<c:url value="/view/flaw_manager_ajax.html" />">
    Недостатки
</a><br/>

<a href="<c:url value="/view/trigger_skill_manager_ajax.html" />">
    Тригерные навыки
</a><br/>

</body>
</html>
