/**
 * Created by artemk on 8/26/14.
 */

$(document).ready(function(){

    ajax.getJsonData('/rest/attachedSkill/all', function(data) {
        render(data);
    }, errorHandler);
});

function render(data) {
    $("#attachedSkillListTemplate").tmpl(data).appendTo("#attachedSkillList");

    $('.deleteAttachedSkill').click(function() {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/attachedSkill', id, function(data) {
            $(_this).parent().parent().fadeToggle("slow", function() {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(data) {
    alert("Error: " + data);
}