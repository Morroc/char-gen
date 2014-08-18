<%--
  Created by IntelliJ IDEA.
  User: artemk
  Date: 8/18/14
  Time: 4:50 PM
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
    <title>Trigger skills manager</title>
</head>
<body>

<h2><a href="/main.jsp">Home</a>

    <h2>

        <h2>Trigger skills manager</h2>

        <form:form method="post" action="addTriggerSkill" commandName="triggerSkill">

        <table>
            <tr>
                <td>
                    <form:label path="name">
                        Название навыка
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="baseCost">
                        Базовая стоимость
                    </form:label>
                </td>
                <td>
                    <form:input path="baseCost"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="type">
                        Тип навыка
                    </form:label>
                </td>
                <td>
                    <form:select path="type" items="${skillTypes}" id="name"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="expertCost">
                        Стоимость эксперта
                    </form:label>
                </td>
                <td>
                    <form:input path="expertCost"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="masterCost">
                        Стоимость мастера
                    </form:label>
                </td>
                <td>
                    <form:input path="masterCost"/>
                </td>
            </tr>

            <tr>
                <td>
                    <form:label path="postMasterCost">
                        Стоимость магистра
                    </form:label>
                </td>
                <td>
                    <form:input path="postMasterCost"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"
                                       value="Создать тригерный навык"/></td>
            </tr>
        </table>
        </form:form>

        <h3>Все тригерные навыки</h3>
        <c:if test="${!empty triggerSkillsList}">
        <table class="data">
            <tr>
                <th>Название навыка</th>
                <th>Базовая стоимость</th>
                <th>Тип навыка</th>
                <th>Стоимость эксперта</th>
                <th>Стоимость мастера</th>
                <th>Стоимость магистра</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${triggerSkillsList}" var="triggerSkill">
                <tr>
                    <td>${triggerSkill.name}</td>
                    <td>${triggerSkill.baseCost}</td>
                    <td>${triggerSkill.type}</td>
                    <td>${triggerSkill.expertCost}</td>
                    <td>${triggerSkill.masterCost}</td>
                    <td>${triggerSkill.postMasterCost}</td>
                    <td><a href="deleteTriggerSkill/${triggerSkill.id}">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
        </c:if>

</body>
</html>
