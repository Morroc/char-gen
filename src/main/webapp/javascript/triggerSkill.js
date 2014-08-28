$(document).ready(function () {

    ajax.getJsonData('/rest/triggerSkill/all', function (triggerSkillListJson) {
        renderTriggerSkills(triggerSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/triggerSkill/skillTypes', function(skillTypeList) {
        renderSkillTypes(skillTypeList);
    }, errorHandler);

    $("#addTriggerSkillForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            name: $('#name').val(),
            baseCost: $('#baseCost').val(),
            type: $('#type').val(),
            expertCost: $('#expertCost').val(),
            masterCost: $('#masterCost').val(),
            postMasterCost: $('#postMasterCost').val()
        }, function( triggerSkillListJson ) {
            ajax.getJsonData('/rest/triggerSkill/all', function (triggerSkillListJson) {
                renderTriggerSkills(triggerSkillListJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Тригерный навык создан успешно.'
                });
            }, errorHandler);
        });
    });
});

function renderTriggerSkills(triggerSkillListJson) {
    $("#triggerSkillList").html($("#triggerSkillListTemplate").tmpl(triggerSkillListJson));

    $('.deleteTriggerSkill').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/triggerSkill', id, function (triggerSkillListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function renderSkillTypes(skillTypeList) {
    $("#skillTypeTemplate").tmpl(skillTypeList).appendTo("#type");
}

function errorHandler(triggerSkillListJson) {
    alert("Error: " + triggerSkillListJson);
}