/**
 * Created by artemk on 8/26/14.
 */
$(document).ready(function () {

    ajax.getJsonData('/rest/attribute/all', function (attributeListJson) {
        render(attributeListJson);
    }, errorHandler);

    $("#addAttributeForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            name: $('#name').val(),
            actionLevelBonus: $('#actionLevelBonus').val()
        }, function( attributeListJson ) {
            ajax.getJsonData('/rest/attribute/all', function (attributeListJson) {
                render(attributeListJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Аттрибут создан успешно.'
                });
            }, errorHandler);
        });
    });
});

function render(attributeListJson) {
    $("#attributeList").html($("#attributeListTemplate").tmpl(attributeListJson));

    $('.deleteAttribute').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/attribute/delete', id, function (attributeListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(attributeListJson) {
    alert("Error: " + attributeListJson);
}