/**
 * Created by artemk on 9/2/14.
 */
$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var personageId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
        renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attachedSkill/all', function (attachedSkillListJson) {
        renderAttachedSkillListJson(attachedSkillListJson);
    }, errorHandler);

    $(".modalbox").fancybox();

    $("#linkAttachedSkillToPersonageForm").submit(function (event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            attachedSkillByPersonage: $('#attachedSkillByPersonage').val(),
            personageByAttachedSkill: $('#personageByAttachedSkill').val(),
            currentValue: $('#currentValue').val()
        }, function (personageWithAllRelatedEntitiesJson) {
            ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
                renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
                $.fancybox.close();
                new PNotify({
                    title: 'Инфо',
                    text: 'Прикрепленный навык добавлен успешно.'
                });
            }, errorHandler);
        });
    });
});

function renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson) {
    var selector = $("#personageNameTemplate");
    $("#personageTitle").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageName").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageByAttachedSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson).appendTo("#linkAttachedSkillToPersonageForm");

    $("#personageHasAttributeList").html($("#personageHasAttributeListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageAttributes']));

    $("#personageHasAttachedSkillList").html($("#personageHasAttachedSkillListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageAttachedSkills']));

    $("#personageHasTriggerSkillList").html($("#personageHasTriggerSkillListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageTriggerSkills']));
}

function renderAttachedSkillListJson(attachedSkillListJson) {
    $("#attachedSkillByPersonageSelectTemplate").tmpl(attachedSkillListJson).appendTo("#attachedSkillByPersonage");
}

function errorHandler(personageWithAllRelatedEntitiesJson) {
    alert("Fucking error!!! " + personageWithAllRelatedEntitiesJson);
}

