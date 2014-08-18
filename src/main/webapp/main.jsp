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
    Races manager
</a><br/>

<a href="<c:url value="/personageManager" />">
    Personages manager
</a><br/>

<a href="<c:url value="/attachedSkillsManager" />">
    Attached skills manager
</a><br/>

<a href="<c:url value="/attributesManager" />">
    Attributes manager
</a><br/>

<a href="<c:url value="/meritManager" />">
    Merits manager
</a><br/>

<a href="<c:url value="/flawManager" />">
    Flaws manager
</a><br/>

<a href="<c:url value="/triggerSkillsManager" />">
    Trigger skills manager
</a><br/>

</body>
</html>
