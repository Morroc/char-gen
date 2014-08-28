<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Менеджер атрибутов</title>
</head>
<body>

<h2><a href="/main.jsp">Домой</a></h2>

<h2>Менеджер атрибутов</h2>

<form:form method="post" action="addAttribute" commandName="attribute">

    <table>
        <tr>
            <td>
                <form:label path="name">
                    Название атрибута
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="actionLevelBonus">
                    Бонус к действию
                </form:label>
            </td>
            <td>
                <form:input path="actionLevelBonus"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                   value="Создать атрибут"/></td>
        </tr>
    </table>
</form:form>

<h3>Все атрибуты</h3>
<c:if test="${!empty attributesList}">
    <table class="data">
        <tr>
            <th>Название аттрибута</th>
            <th>Бонус на действия</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${attributesList}" var="attribute">
            <tr>
                <td>${attribute.name}</td>
                <td>${attribute.actionLevelBonus}</td>
                <td><a href="deleteAttribute/${attribute.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>