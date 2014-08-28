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
    <title>Менеджер недостатков</title>
</head>
<body>

<h2><a href="/main.jsp">Домой</a></h2>

<h2>Менеджер недостатков</h2>

<form:form method="post" action="addFlaw" commandName="flaw">

    <table>
        <tr>
            <td>
                <form:label path="name">
                    Название недостатка
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
                    Описание недостатка
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="turnOffPreconditions">
                    Условия выключения
                </form:label>
            </td>
            <td>
                <form:input path="turnOffPreconditions"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                   value="Создать недостаток"/></td>
        </tr>
    </table>
</form:form>

<h3>Все недостатки</h3>
<c:if test="${!empty flawsList}">
    <table class="data">
        <tr>
            <th>Название</th>
            <th>Стоимость</th>
            <th>Описание</th>
            <th>Условия</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${flawsList}" var="flaw">
            <tr>
                <td>${flaw.name}</td>
                <td>${flaw.cost}</td>
                <td>${flaw.description}</td>
                <td>${flaw.turnOffPreconditions}</td>
                <td><a href="deleteFlaw/${flaw.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
