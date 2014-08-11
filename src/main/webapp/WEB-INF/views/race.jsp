<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title" /></title>
</head>
<body>

<h2><spring:message code="label.title" /></h2>

<form:form method="post" action="add" commandName="race">

    <table>
        <tr>
            <td><form:label path="name">
                <spring:message code="label.raceName" />
            </form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="maxAge">
                <spring:message code="label.maxAge" />
            </form:label></td>
            <td><form:input path="maxAge" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="<spring:message code="label.addRace"/>" /></td>
        </tr>
    </table>
</form:form>

<h3><spring:message code="label.races" /></h3>
<c:if test="${!empty raceList}">
    <table class="data">
        <tr>
            <th><spring:message code="label.raceName" /></th>
            <th><spring:message code="label.maxAge" /></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${raceList}" var="race">
            <tr>
                <td>${race.name}</td>
                <td>${race.maxAge}</td>
                <td><a href="delete/${race.id}"><spring:message code="label.deleteRace" /></a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>