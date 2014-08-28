/**
 * Created by artemk on 8/26/14.
 */

$(document).ready(function(){

    ajax.getJsonData('/rest/attachedSkill/all', function(attachedSkillListJson) {
        render(attachedSkillListJson);
    }, errorHandler);

    $("#addAttachedSkillForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            name: $('#name').val(),
            baseCost: $('#baseCost').val(),
            acquiringCost: $('#acquiringCost').val(),
            defaultSkill: $('#defaultSkill').prop('checked'),
            difficult: $('#difficult').prop('checked'),
            theoretical: $('#theoretical').prop('checked')
        }, function( attachedSkillListJson ) {
            ajax.getJsonData('/rest/attachedSkill/all', function(attachedSkillListJson) {
                render(attachedSkillListJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Прикрепленный навык создан успешно.'
                });
            }, errorHandler);
        });
    });
});

function render(attachedSkillListJson) {
    $("#attachedSkillList").html($("#attachedSkillListTemplate").tmpl(attachedSkillListJson));

    $('.deleteAttachedSkill').click(function() {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/attachedSkill/delete', id, function(attachedSkillListJson) {
            $(_this).parent().parent().fadeToggle("slow", function() {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(attachedSkillListJson) {
    alert("Error: " + attachedSkillListJson);
}