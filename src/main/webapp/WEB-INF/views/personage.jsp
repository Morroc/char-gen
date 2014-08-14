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

<h2>Personage ${personage.name}</h2>

<h3>Прикрепленные навыки</h3>

<form:form method="post" action="linkAttachedSkillToPersonage" commandName="personageHasAttachedSkill">

    <table>
        <tr>
            <td>
                <form:label path="attachedSkillByPersonage">
                    Прикрепленный навык
                </form:label>
            </td>
            <td>
                <form:select path="attachedSkillByPersonage" items="${attachedSkillsList}" itemValue="id" itemLabel="name"/>
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
                                   value="Добавить навык"/></td>
        </tr>
    </table>
</form:form>

<h3>Прикрепленные навыки</h3>

</body>
</html>
