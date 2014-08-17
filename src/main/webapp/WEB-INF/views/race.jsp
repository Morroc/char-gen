<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Race ${race.name}</title>
</head>
<body>

<h2><a href="/main.jsp">Home</a></h2>

<h2>Race ${race.name}</h2>

<h3>Значение атрибутов для расы</h3>

<c:if test="${!empty raceHasAttributeByRace}">
    <table class="data">
        <tr>
            <th>Название атрибута</th>
            <th>Базовая стоимость за единицу</th>
            <th>Стоимость поднятия с 1 до 3 по игре</th>
            <th>Стоимость поднятия с 3 до 6 по игре</th>
            <th>Стоимость поднятия с 6 до 9 по игре</th>
            <th>Стоимость поднятия с 9 до 12 по игре</th>
            <th>Максимальное значение</th>
            <th>Бонус на действия</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${raceHasAttributeByRace}" var="raceHasAttribute">
            <tr>
                <td>${raceHasAttribute.attributeByRace.name}</td>
                <td>${raceHasAttribute.baseCost}</td>
                <td>${raceHasAttribute.from1To3NonGeneratingCost}</td>
                <td>${raceHasAttribute.from3To6NonGeneratingCost}</td>
                <td>${raceHasAttribute.from6To9NonGeneratingCost}</td>
                <td>${raceHasAttribute.from9To12NonGeneratingCost}</td>
                <td>${raceHasAttribute.maxValue}</td>
                <td>${raceHasAttribute.attributeByRace.actionLevelBonus}</td>
                <td>
                    <a href="/race/unlinkAttributeFromRace/${raceHasAttribute.id}?raceId=${race.id}">Отвязать</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h3>Добавить атрибут к расе</h3>

<form:form method="post" action="linkAttributeToRace" commandName="raceHasAttribute">

    <table>
        <tr>
            <td>
                <form:label path="attributeByRace">
                    Атрибут
                </form:label>
            </td>
            <td>
                <form:select path="attributeByRace" items="${allAttributesList}" itemValue="id"
                             itemLabel="name"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:input path="raceByAttribute" type="hidden" value="${race.id}"/>
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
                <form:label path="maxValue">
                    Максимальное значение
                </form:label>
            </td>
            <td>
                <form:input path="maxValue"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="from1To3NonGeneratingCost">
                    Стоимость поднятия с 1 до 3 по игре
                </form:label>
            </td>
            <td>
                <form:input path="from1To3NonGeneratingCost"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="from3To6NonGeneratingCost">
                    Стоимость поднятия с 3 до 6 по игре
                </form:label>
            </td>
            <td>
                <form:input path="from3To6NonGeneratingCost"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="from6To9NonGeneratingCost">
                    Стоимость поднятия с 6 до 9 по игре
                </form:label>
            </td>
            <td>
                <form:input path="from6To9NonGeneratingCost"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="from9To12NonGeneratingCost">
                    Стоимость поднятия с 9 до 12 по игре
                </form:label>
            </td>
            <td>
                <form:input path="from9To12NonGeneratingCost"/>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить атрибут"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
