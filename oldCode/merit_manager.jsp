<%--
  Created by IntelliJ IDEA.
  User: artemk
  Date: 8/17/14
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Менеджер достоинств</title>
</head>
<body>

<h2><a href="/main.jsp">Домой</a></h2>

<h2>Менеджер достоинств</h2>

<form:form method="post" action="addMerit" commandName="merit">

    <table>
        <tr>
            <td>
                <form:label path="name">
                    Название достоинства
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="cost">
                    Стоимость
                </form:label>
            </td>
            <td>
                <form:input path="cost"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="description">
                    Описание достоинства
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="actionBonus">
                    Бонус на действие
                </form:label>
            </td>
            <td>
                <form:input path="actionBonus"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="preconditions">
                    Условия приобретения
                </form:label>
            </td>
            <td>
                <form:input path="preconditions"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                   value="Создать достоинство"/></td>
        </tr>
    </table>
</form:form>

<h3>Все достоинства</h3>
<c:if test="${!empty meritsList}">
    <table class="data">
        <tr>
            <th>Название</th>
            <th>Стоимость</th>
            <th>Описание</th>
            <th>Бонус</th>
            <th>Условия</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${meritsList}" var="merit">
            <tr>
                <td>${merit.name}</td>
                <td>${merit.cost}</td>
                <td>${merit.description}</td>
                <td>${merit.actionBonus}</td>
                <td>${merit.preconditions}</td>
                <td><a href="deleteMerit/${merit.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
