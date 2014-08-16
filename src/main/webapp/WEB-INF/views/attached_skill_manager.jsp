<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Attached skills manager</title>
</head>
<body>

<h2><a href="/main.jsp">Home</a><h2>

<h2>Attached skills manager</h2>

<form:form method="post" action="addAttachedSkill" commandName="attachedSkill">

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
                    Базовая стоимость за единицу
                </form:label>
            </td>
            <td>
                <form:input path="baseCost"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="acquiringCost">
                    Стоимость при покупке по игре
                </form:label>
            </td>
            <td>
                <form:input path="acquiringCost"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="defaultSkill">
                    По умолчанию
                </form:label>
            </td>
            <td>
                <form:checkbox path="defaultSkill"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="difficult">
                    Сложный
                </form:label>
            </td>
            <td>
                <form:checkbox path="difficult"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="theoretical">
                    Теоритический
                </form:label>
            </td>
            <td>
                <form:checkbox path="theoretical"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Создать прикрепленный навык"/></td>
        </tr>
    </table>
</form:form>

<h3>Все прикрепленные навыки</h3>
<c:if test="${!empty attachedSkillsList}">
    <table class="data">
        <tr>
            <th>Название навыка</th>
            <th>Базовая стоимость за единицу</th>
            <th>Стоимость покупке по игре</th>
            <th>По умолчанию</th>
            <th>Сложный</th>
            <th>Теоритический</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${attachedSkillsList}" var="attachedSkill">
            <tr>
                <td>${attachedSkill.name}</td>
                <td>${attachedSkill.baseCost}</td>
                <td>${attachedSkill.acquiringCost}</td>
                <c:if test = "${attachedSkill.defaultSkill == 'true'}">
                    <td>
                        <input type="checkbox" disabled="disabled" checked="checked">
                    </td>
                </c:if>
                <c:if test = "${attachedSkill.defaultSkill == 'false'}">
                    <td>
                        <input type="checkbox" disabled="disabled">
                    </td>
                </c:if>

                <c:if test = "${attachedSkill.difficult == 'true'}">
                    <td>
                        <input type="checkbox" disabled="disabled" checked="checked">
                    </td>
                </c:if>
                <c:if test = "${attachedSkill.difficult == 'false'}">
                    <td>
                        <input type="checkbox" disabled="disabled">
                    </td>
                </c:if>

                <c:if test = "${attachedSkill.theoretical == 'true'}">
                    <td>
                        <input type="checkbox" disabled="disabled" checked="checked">
                    </td>
                </c:if>
                <c:if test = "${attachedSkill.theoretical == 'false'}">
                    <td>
                        <input type="checkbox" disabled="disabled">
                    </td>
                </c:if>
                <td><a href="deleteAttachedSkill/${attachedSkill.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>