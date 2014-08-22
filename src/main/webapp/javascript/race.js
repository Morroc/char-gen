$(document).ready(function(){

    ajax.getJsonData('/rest/race/all', function(data) {
        render(data);
    }, errorHandler);
});

function render(data) {
    $("#raceListTemplate").tmpl(data).appendTo("#raceList");

    $('.deleteRace').click(function() {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race', id, function(data) {
            $(_this).parent().parent().fadeToggle("slow", function() {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(data) {
    alert("Error: " + data);
}