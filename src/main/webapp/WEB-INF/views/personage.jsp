<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Personage ${personage.name}</title>
</head>
<body>

<h2><a href="/main.jsp">Home</a></h2>

<h2>Personage ${personage.name}</h2>

<h3>Атрибуты</h3>

<c:if test="${!empty personageHasAttributesByPersonage}">
    <table class="data">
        <tr>
            <th>Название атрибута</th>
            <th>Текущее значение</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${personageHasAttributesByPersonage}" var="personageHasAttribute">
            <tr>
                <td>${personageHasAttribute.attributeByPersonage.name}</td>
                <td>${personageHasAttribute.currentValue}</td>
                <td><a href="updateAttribute/${personageHasAttribute.id}?addOrRemove=add">+1</a></td>
                <td><a href="updateAttribute/${personageHasAttribute.id}?addOrRemove=remove">-1</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h3>Прикрепленные навыки</h3>

<c:if test="${!empty personageHasAttachedSkillsByPersonage}">
    <table class="data">
        <tr>
            <th>Название навыка</th>
            <th>Текущее значение</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${personageHasAttachedSkillsByPersonage}" var="personageHasAttachedSkill">
            <tr>
                <td>${personageHasAttachedSkill.attachedSkillByPersonage.name}</td>
                <td>${personageHasAttachedSkill.currentValue}</td>
                <td><a href="updateAttachedSkill/${personageHasAttachedSkill.id}?addOrRemove=add">+1</a></td>
                <td><a href="updateAttachedSkill/${personageHasAttachedSkill.id}?addOrRemove=remove">-1</a></td>
                <td>
                    <a href="unlinkAttachedSkillFromPersonage/${personageHasAttachedSkill.id}?personageId=${personage.id}">Отвязать</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h3>Добавить прикрепленный навык</h3>

<form:form method="post" action="linkAttachedSkillToPersonage" commandName="personageHasAttachedSkill">

    <table>
        <tr>
            <td>
                <form:label path="attachedSkillByPersonage">
                    Прикрепленный навык
                </form:label>
            </td>
            <td>
                <form:select path="attachedSkillByPersonage" items="${allAttachedSkillsList}" itemValue="id"
                             itemLabel="name"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:input path="personageByAttachedSkill" type="hidden" value="${personage.id}"/>
            </td>
        </tr>


        <tr>
            <td>
                <form:label path="currentValue">
                    Значение
                </form:label>
            </td>
            <td>
                <form:input path="currentValue"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить прикрепленный навык"/></td>
        </tr>
    </table>
</form:form>

<h3>Тригерные навыки</h3>

<c:if test="${!empty personageHasTriggerSkillsByPersonage}">
    <table class="data">
        <tr>
            <th>Название навыка</th>
            <th>Текущий уровень</th>
            <th>Талант</th>
            <th>Учитель</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${personageHasTriggerSkillsByPersonage}" var="personageHasTriggerSkill">
            <tr>
                <td>${personageHasTriggerSkill.triggerSkillByPersonage.name}</td>
                <td>${personageHasTriggerSkill.currentLevel}</td>
                <c:if test="${personageHasTriggerSkill.hasTalent == 'true'}">
                    <td>
                        <input type="checkbox" disabled="disabled" checked="checked">
                    </td>
                </c:if>
                <c:if test="${personageHasTriggerSkill.hasTalent == 'false'}">
                    <td>
                        <input type="checkbox" disabled="disabled">
                    </td>
                </c:if>
                <c:if test="${personageHasTriggerSkill.hasTeacher == 'true'}">
                    <td>
                        <input type="checkbox" disabled="disabled" checked="checked">
                    </td>
                </c:if>
                <c:if test="${personageHasTriggerSkill.hasTeacher == 'false'}">
                    <td>
                        <input type="checkbox" disabled="disabled">
                    </td>
                </c:if>
                <td>
                    <a href="unlinkTriggerSkillFromPersonage/${personageHasTriggerSkill.id}?personageId=${personage.id}">Отвязать</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h3>Добавить триггерный навык</h3>

<form:form method="post" action="linkTriggerSkillToPersonage" commandName="personageHasTriggerSkill">

    <table>
        <tr>
            <td>
                <form:label path="triggerSkillByPersonage">
                    Тригерный навык
                </form:label>
            </td>
            <td>
                <form:select path="triggerSkillByPersonage" items="${allTriggerSkillsList}" itemValue="id"
                             itemLabel="name"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:input path="personageByTriggerSkill" type="hidden" value="${personage.id}"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="currentLevel">
                    Уровень
                </form:label>
            </td>
            <td>
                <form:select path="currentLevel" items="${skillLevels}" id="name"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="hasTalent">
                    Талант
                </form:label>
            </td>
            <td>
                <form:checkbox path="hasTalent"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="hasTeacher">
                    Учитель
                </form:label>
            </td>
            <td>
                <form:checkbox path="hasTeacher"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить тригерный навык"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
