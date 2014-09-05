$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/triggerSkill/', function (triggerSkillListJson) {
        renderTriggerSkills(triggerSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/triggerSkill/skillTypes', function(skillTypeList) {
        renderSkillTypes(skillTypeList);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addTriggerSkillForm").submit(function(event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function(triggerSkillListJson) {
            renderTriggerSkills(triggerSkillListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Навык создан успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updateTriggerSkillForm").submit(function(event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (triggerSkillListJson) {
            renderTriggerSkills(triggerSkillListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Навык сохранен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function renderTriggerSkills(triggerSkillListJson) {
    window.triggerSkillListJson = triggerSkillListJson;
    $("#triggerSkillList").html($("#triggerSkillListTemplate").tmpl(triggerSkillListJson));

    $('.deleteTriggerSkill').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/triggerSkill/', id, function (triggerSkillListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateTriggerSkill").fancybox({
        'afterLoad' : function(obj){
            var id = $(obj.element).parent().find("[name=id]").val();
            for(var i = 0; i < window.triggerSkillListJson.length; i++) {
                if(window.triggerSkillListJson[i].id == id) {
                    $('#updateTriggerSkillForm').attr('action', '/rest/triggerSkill/' + window.triggerSkillListJson[i].id);
                    $('#updateName').val(window.triggerSkillListJson[i].name);
                    $('#updateBaseCost').val(window.triggerSkillListJson[i].baseCost);
                    $('#updateType').val(window.triggerSkillListJson[i].type.name);
                    $('#updateExpertCost').val(window.triggerSkillListJson[i].expertCost);
                    $('#updateMasterCost').val(window.triggerSkillListJson[i].masterCost);
                    $('#updatePostMasterCost').val(window.triggerSkillListJson[i].postMasterCost);
                    break;
                }
            }
        }
    });
}

function renderSkillTypes(skillTypeList) {
    var skillTypeTemplate = $("#skillTypeTemplate");
    skillTypeTemplate.tmpl(skillTypeList).appendTo("#type");
    skillTypeTemplate.tmpl(skillTypeList).appendTo("#updateType");
}

function errorHandler(triggerSkillListJson) {
    alert("Error: " + triggerSkillListJson);
}