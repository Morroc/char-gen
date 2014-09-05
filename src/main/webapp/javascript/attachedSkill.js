/**
 * Created by artemk on 8/26/14.
 */

$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/attachedSkill/', function (attachedSkillListJson) {
        render(attachedSkillListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addAttachedSkillForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var attachedSkill = $(this).serializeObject();
        attachedSkill.defaultSkill = $("#defaultSkill").prop('checked');
        attachedSkill.difficult = $("#difficult").prop('checked');
        attachedSkill.theoretical = $("#theoretical").prop('checked');
        ajax.putJsonData($(this), JSON.stringify(attachedSkill), function (attachedSkillListJson) {
            render(attachedSkillListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Прикрепленный навык создан успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updateAttachedSkillForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var attachedSkill = $(this).serializeObject();
        attachedSkill.defaultSkill = $("#updateDefaultSkill").prop('checked');
        attachedSkill.difficult = $("#updateDifficult").prop('checked');
        attachedSkill.theoretical = $("#updateTheoretical").prop('checked');
        ajax.postJsonData($(this), JSON.stringify(attachedSkill), function (attachedSkillListJson) {
            render(attachedSkillListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Прикрепленный навык сохранен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function render(attachedSkillListJson) {
    window.attachedSkillListJson = attachedSkillListJson;
    $("#attachedSkillList").html($("#attachedSkillListTemplate").tmpl(attachedSkillListJson));

    $('.deleteAttachedSkill').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/attachedSkill/', id, function (attachedSkillListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateAttachedSkill").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            for (var i = 0; i < window.attachedSkillListJson.length; i++) {
                if (window.attachedSkillListJson[i].id == id) {
                    $('#updateAttachedSkillForm').attr('action', '/rest/attachedSkill/' + window.attachedSkillListJson[i].id);
                    $('#updateName').val(window.attachedSkillListJson[i].name);
                    $('#updateBaseCost').val(window.attachedSkillListJson[i].baseCost);
                    $('#updateAcquiringCost').val(window.attachedSkillListJson[i].acquiringCost);
                    $('#updateDefaultSkill').prop('checked', window.attachedSkillListJson[i].defaultSkill);
                    $('#updateDifficult').prop('checked', window.attachedSkillListJson[i].difficult);
                    $('#updateTheoretical').prop('checked', window.attachedSkillListJson[i].theoretical);
                    break;
                }
            }
        }
    });
}

function errorHandler(attachedSkillListJson) {
    alert("Error: " + attachedSkillListJson);
}