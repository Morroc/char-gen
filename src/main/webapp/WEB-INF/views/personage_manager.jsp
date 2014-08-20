<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Менеджер персонажей</title>
</head>
<body>

<h2><a href="/main.jsp">Домой</a><h2>

<h2>Менеджер персонажей</h2>

<form:form method="post" action="addPersonage" commandName="personage">

    <table>
        <tr>
            <td>
                <form:label path="name">
                    Имя персонажа
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    Возраст
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="race">
                    Раса
                </form:label>
            </td>
            <td>
                <form:select path="race" items="${racesList}" itemValue="id" itemLabel="name"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Создать персонажа"/></td>
        </tr>
    </table>
</form:form>

<h3>Все персонажи</h3>
<c:if test="${!empty personagesList}">
    <table class="data">
        <tr>
            <th>Имя персонажа</th>
            <th>Возраст</th>
            <th>Раса</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${personagesList}" var="personage">
            <tr>
                <td><a href="/personage/${personage.id}">${personage.name}</a></td>
                <td>${personage.age}</td>
                <td>${personage.race.name}</td>
                <td><a href="deletePersonage/${personage.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>