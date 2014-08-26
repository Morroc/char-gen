/**
 * Created by artemk on 8/26/14.
 */
$(document).ready(function () {

    ajax.getJsonData('/rest/attribute/all', function (data) {
        render(data);
    }, errorHandler);
});

function render(data) {
    $("#attributeListTemplate").tmpl(data).appendTo("#attributeList");

    $('.deleteAttribute').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/attribute', id, function (data) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(data) {
    alert("Error: " + data);
}