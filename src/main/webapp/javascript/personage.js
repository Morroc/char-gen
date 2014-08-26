/**
 * Created by artemk on 8/26/14.
 */

$(document).ready(function(){

    ajax.getJsonData('/rest/personage/all', function(data) {
        render(data);
    }, errorHandler);
});

function render(data) {
    $("#personageListTemplate").tmpl(data).appendTo("#personageList");

    $('.deletePersonage').click(function() {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage', id, function(data) {
            $(_this).parent().parent().fadeToggle("slow", function() {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(data) {
    alert("Error: " + data);
}