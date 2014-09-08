/**
 * Created by artemk on 9/2/14.
 */
$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var personageId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
        renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attachedSkill/', function (attachedSkillListJson) {
        renderAttachedSkillListJson(attachedSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/triggerSkill/', function (triggerSkillListJson) {
        renderTriggerSkillListJson(triggerSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/personage/personageTriggerSkill/skillLevels', function(skillLevelList) {
        renderSkillLevels(skillLevelList);
    }, errorHandler);

    $(".modalbox").fancybox();

    $("#linkAttachedSkillToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasAttachedSkill = $(this).serializeObject();
        personageHasAttachedSkill.attachedSkill = {id: personageHasAttachedSkill.attachedSkill};
        if(!(personageHasAttachedSkill.personage instanceof Array)) {
            personageHasAttachedSkill.personage = {id: personageHasAttachedSkill.personage};
        } else {
            personageHasAttachedSkill.personage = {id: personageHasAttachedSkill.personage[0]};
        }
        personageHasAttachedSkill.personage.race = {id: personageHasAttachedSkill.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasAttachedSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            $.fancybox.close();
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
        if(!(personageHasTriggerSkill.personage instanceof Array)) {
            personageHasTriggerSkill.personage = {id: personageHasTriggerSkill.personage};
        } else {
            personageHasTriggerSkill.personage = {id: personageHasTriggerSkill.personage[0]};
        }
        personageHasTriggerSkill.personage.race = {id: personageHasTriggerSkill.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasTriggerSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            $.fancybox.close();
            new PNotify({
                title: 'Инфо',
                text: 'Тригерный навык добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson) {
    var selector = $("#personageNameTemplate");
    $("#personageTitle").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageName").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageByAttachedSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson).appendTo("#linkAttachedSkillToPersonageForm");
    $("#personageByTriggerSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson).appendTo("#linkTriggerSkillToPersonageForm");

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
}

function renderAttachedSkillListJson(attachedSkillListJson) {
    $("#attachedSkillByPersonageSelectTemplate").tmpl(attachedSkillListJson).appendTo("#attachedSkill");
}

function renderTriggerSkillListJson(triggerSkillListJson) {
    $("#triggerSkillByPersonageSelectTemplate").tmpl(triggerSkillListJson).appendTo("#triggerSkill");
}

function errorHandler(personageWithAllRelatedEntitiesJson) {
    alert("Fucking error!!! " + personageWithAllRelatedEntitiesJson);
}

function renderSkillLevels(skillLevelList) {
    $("#skillLevelTemplate").tmpl(skillLevelList).appendTo("#currentLevel");
}

