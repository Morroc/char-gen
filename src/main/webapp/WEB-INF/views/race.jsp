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
    <title>Race editor</title>
</head>
<body>

<h2>Race editor</h2>

<form:form method="post" action="add" commandName="race">

    <table>
        <tr>
            <td><form:label path="name">
                Название расы
            </form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="maxAge">
                Максимально возможный возраст
            </form:label></td>
            <td><form:input path="maxAge" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить расу" /></td>
        </tr>
    </table>
</form:form>

<h3>Все расы</h3>
<c:if test="${!empty raceList}">
    <table class="data">
        <tr>
            <th>Название расы</th>
            <th>Максимально возможный возраст</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${raceList}" var="race">
            <tr>
                <td>${race.name}</td>
                <td>${race.maxAge}</td>
                <td><a href="delete/${race.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>