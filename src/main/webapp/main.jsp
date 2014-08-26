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

<a href="<c:url value="/raceManager" />">
    Менеджер рас
</a><br/>

<a href="<c:url value="/view/race_manager_ajax.html" />">
    Расы (Ajax)
</a><br/>

<a href="<c:url value="/personageManager" />">
    Менеджер персонажей
</a><br/>

<a href="<c:url value="/view/personage_manager_ajax.html" />">
    Персонажи (Ajax)
</a><br/>

<a href="<c:url value="/attachedSkillsManager" />">
    Менеджер прикрепленных скилов
</a><br/>

<a href="<c:url value="/attributesManager" />">
    Менеджер атрибутов
</a><br/>

<a href="<c:url value="/meritManager" />">
    Менеджер достоинств
</a><br/>

<a href="<c:url value="/flawManager" />">
    Менеджер недостатков
</a><br/>

<a href="<c:url value="/triggerSkillsManager" />">
    Менеджер тригерных скилов
</a><br/>

</body>
</html>
