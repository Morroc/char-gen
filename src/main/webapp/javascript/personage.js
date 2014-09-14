/**
 * Created by artemk on 9/2/14.
 */
$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var personageId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
        renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/personage/'.concat(personageId).concat('/differentTypesOfMeritsForPersonage'), function (differentTypesOfMeritsForPersonageJson) {
        renderDifferentTypesOfMeritsForPersonageJson(differentTypesOfMeritsForPersonageJson);
    }, errorHandler);

    ajax.getJsonData('/rest/personage/'.concat(personageId).concat('/differentTypesOfFlawsForPersonage'), function (differentTypesOfFlawsForPersonageJson) {
        renderDifferentTypesOfFlawsForPersonageJson(differentTypesOfFlawsForPersonageJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attachedSkill/', function (attachedSkillListJson) {
        renderAttachedSkillListJson(attachedSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/triggerSkill/', function (triggerSkillListJson) {
        renderTriggerSkillListJson(triggerSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/personage/personageTriggerSkill/skillLevels', function (skillLevelList) {
        renderSkillLevels(skillLevelList);
    }, errorHandler);

    $(".modalbox").fancybox();

    $("#linkAttachedSkillToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasAttachedSkill = $(this).serializeObject();
        personageHasAttachedSkill.attachedSkill = {id: personageHasAttachedSkill.attachedSkill};
        personageHasAttachedSkill.personage = {id: personageHasAttachedSkill.personage};
        personageHasAttachedSkill.personage.race = {id: personageHasAttachedSkill.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasAttachedSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            new PNotify({
                title: 'Инфо',
                text: 'Прикрепленный навык добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#linkTriggerSkillToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasTriggerSkill = $(this).serializeObject();
        personageHasTriggerSkill.hasTalent = $("#hasTalent").prop('checked');
        personageHasTriggerSkill.hasTeacher = $("#hasTeacher").prop('checked');
        personageHasTriggerSkill.triggerSkill = {id: personageHasTriggerSkill.triggerSkill};
        personageHasTriggerSkill.personage = {id: personageHasTriggerSkill.personage};
        personageHasTriggerSkill.personage.race = {id: personageHasTriggerSkill.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasTriggerSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            new PNotify({
                title: 'Инфо',
                text: 'Тригерный навык добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#linkMeritToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasMerit = $(this).serializeObject();
        personageHasMerit.merit = {id: personageHasMerit.merit};
        personageHasMerit.personage = {id: personageHasMerit.personage};
        personageHasMerit.personage.race = {id: personageHasMerit.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasMerit), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            new PNotify({
                title: 'Инфо',
                text: 'Достоинство добавлено успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
            ajax.getJsonData('/rest/personage/'.concat(personageId).concat('/differentTypesOfMeritsForPersonage'), function (differentTypesOfMeritsForPersonageJson) {
                renderDifferentTypesOfMeritsForPersonageJson(differentTypesOfMeritsForPersonageJson);
            }, errorHandler);
        }, errorHandler);
    });

    $("#linkFlawToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasFlaw = $(this).serializeObject();
        personageHasFlaw.flaw = {id: personageHasFlaw.flaw};
        personageHasFlaw.personage = {id: personageHasFlaw.personage};
        personageHasFlaw.personage.race = {id: personageHasFlaw.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasFlaw), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            new PNotify({
                title: 'Инфо',
                text: 'Недостаток добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
            ajax.getJsonData('/rest/personage/'.concat(personageId).concat('/differentTypesOfFlawsForPersonage'), function (differentTypesOfFlawsForPersonageJson) {
                renderDifferentTypesOfFlawsForPersonageJson(differentTypesOfFlawsForPersonageJson);
            }, errorHandler);
        }, errorHandler);
    });

    $("#updatePersonageAttributeForm").submit(function (event) {
        event.preventDefault();
        var personageHasAttr = $(this).serializeObject();
        personageHasAttr.attribute = {id: personageHasAttr.attribute};
        personageHasAttr.personage = {id: personageHasAttr.personage};
        personageHasAttr.personage.race = {id: personageHasAttr.personage.race};
        ajax.postJsonData($(this), JSON.stringify(personageHasAttr), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
        }, errorHandler);
    });
});

function renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson) {
    var selector = $("#personageNameTemplate");
    $("#personageTitle").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageName").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#attachedSkillPersonageId").html($("#personageByAttachedSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#triggerSkillPersonageId").html($("#personageByTriggerSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#meritPersonageId").html($("#personageByMeritTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#flawPersonageId").html($("#personageByFlawTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageByAttributeId").html($("#personageByAttributeTemplate").tmpl(personageWithAllRelatedEntitiesJson));

    $("#personageHasAttributeList").html($("#personageHasAttributeListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageAttributes']));

    $("#personageHasAttachedSkillList").html($("#personageHasAttachedSkillListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageAttachedSkills']));

    $('.unlinkAttachedSkillFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageAttachedSkill', id, function (personageAttachedSkill) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $("#personageHasTriggerSkillList").html($("#personageHasTriggerSkillListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageTriggerSkills']));

    $('.unlinkTriggerSkillFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageTriggerSkill', id, function (personageTriggerSkill) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $('.plus').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();
        var raceId = personageWithAllRelatedEntitiesJson.personage.race.id;

        ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
            updateAttributeValue(raceWithAllRelatedEntitiesJson, personageWithAllRelatedEntitiesJson, id, '+');
        }, errorHandler);
    });

    $('.minus').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();
        var raceId = personageWithAllRelatedEntitiesJson.personage.race.id;

        ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
            updateAttributeValue(raceWithAllRelatedEntitiesJson, personageWithAllRelatedEntitiesJson, id, '-');
        }, errorHandler);
    });
}

function renderAttachedSkillListJson(attachedSkillListJson) {
    $("#attachedSkill").html($("#attachedSkillByPersonageSelectTemplate").tmpl(attachedSkillListJson));
}

function renderTriggerSkillListJson(triggerSkillListJson) {
    $("#triggerSkill").html($("#triggerSkillByPersonageSelectTemplate").tmpl(triggerSkillListJson));
}

function errorHandler(personageWithAllRelatedEntitiesJson) {
    alert("Fucking error!!! " + personageWithAllRelatedEntitiesJson);
}

function renderSkillLevels(skillLevelList) {
    $("#currentLevel").html($("#skillLevelTemplate").tmpl(skillLevelList));
}

function renderDifferentTypesOfMeritsForPersonageJson(differentTypesOfMeritsForPersonageJson) {
    $("#personageHasMeritList").html($("#personageHasMeritListTemplate").tmpl(differentTypesOfMeritsForPersonageJson));

    $("#merit").html($("#meritsSelectTemplate").tmpl(differentTypesOfMeritsForPersonageJson));

    $('.unlinkMeritFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageMerit', id, function (personageMerit) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $('.unlinkMeritFromPersonageByRaceHasMeritId').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageMerit/byRaceHasMeritId', id, function (raceMerit) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function renderDifferentTypesOfFlawsForPersonageJson(differentTypesOfFlawsForPersonageJson) {
    $("#personageHasFlawList").html($("#personageHasFlawListTemplate").tmpl(differentTypesOfFlawsForPersonageJson));

    $("#flaw").html($("#flawsSelectTemplate").tmpl(differentTypesOfFlawsForPersonageJson.valueOf()['allFlawsWithoutDefaultForRace']));

    $('.unlinkFlawFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageFlaw', id, function (personageMerit) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function updateAttributeValue(raceWithAllRelatedEntitiesJson, personageWithAllRelatedEntitiesJson, personageAttributeId, plusOrMinusOne) {
    var personageHasAttribute;
    for (var i = 0; i < personageWithAllRelatedEntitiesJson.personageAttributes.length; i++) {
        if (personageWithAllRelatedEntitiesJson.personageAttributes[i].id == personageAttributeId) {
            personageHasAttribute = personageWithAllRelatedEntitiesJson.personageAttributes[i];
        }
    }

    var raceHasAttributeByPersonage;
    for(var a = 0; a < raceWithAllRelatedEntitiesJson.raceAttributes.length; a++){
        if(raceWithAllRelatedEntitiesJson.raceAttributes[a].attribute.id == personageHasAttribute.attribute.id) {
            raceHasAttributeByPersonage = raceWithAllRelatedEntitiesJson.raceAttributes[a];
        }
    }

    if(plusOrMinusOne == '+') {
        if(personageHasAttribute.currentValue == raceHasAttributeByPersonage.maxValue) {
            alert("Для данной расы атрибут " + raceHasAttributeByPersonage.attribute.name
                + " не может быть больше " + raceHasAttributeByPersonage.maxValue);
            return;
        }
        personageHasAttribute.currentValue = personageHasAttribute.currentValue + 1;
    } else {
        if(personageHasAttribute.currentValue == 1) {
            alert("Атрибут не может быть меньше 1");
            return;
        }
        personageHasAttribute.currentValue = personageHasAttribute.currentValue - 1;
    }
    $("#attributeByPersonageId").html($("#attributeByPersonageTemplate").tmpl(personageHasAttribute));
    $("#attributeCurrentValue").html($("#currentValueTemplate").tmpl(personageHasAttribute));
    $('#updatePersonageAttributeForm').attr('action', '/rest/personage/personageAttribute/' + personageHasAttribute.id);
    $("#updatePersonageAttributeForm").submit();
}

