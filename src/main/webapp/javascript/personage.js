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
            $.fancybox.close();
            new PNotify({
                title: 'Инфо',
                text: 'Прикрепленный навык добавлен успешно.'
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
}

function renderAttachedSkillListJson(attachedSkillListJson) {
    $("#attachedSkillByPersonageSelectTemplate").tmpl(attachedSkillListJson).appendTo("#attachedSkill");
}

function errorHandler(personageWithAllRelatedEntitiesJson) {
    alert("Fucking error!!! " + personageWithAllRelatedEntitiesJson);
}

