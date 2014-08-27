$(document).ready(function () {

    ajax.getJsonData('/rest/race/all', function (raceListJson) {
        render(raceListJson);
    }, errorHandler);

    $("#addRaceForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), { name: $('#name').val(), maxAge: $('#maxAge').val()}, function( raceListJson ) {
            ajax.getJsonData('/rest/race/all', function (raceListJson) {
                render(raceListJson);
                new PNotify({
                    title: 'Info',
                    text: 'Race has been added successfully.'
                });
            }, errorHandler);
        });
    });
});

function render(raceListJson) {
    $("#raceList").html($("#raceListTemplate").tmpl(raceListJson));

    $('.deleteRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race', id, function (raceListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(raceListJson) {
    alert("Error: " + raceListJson);
}