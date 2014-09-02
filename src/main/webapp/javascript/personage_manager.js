/**
 * Created by artemk on 8/26/14.
 */

$(document).ready(function(){

    ajax.getJsonData('/rest/personage/all', function(personageListJson) {
        renderPersonages(personageListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/race/all', function(raceListJson) {
        renderRaces(raceListJson);
    }, errorHandler);

    $("#addPersonageForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            name: $('#name').val(),
            age: $('#age').val(),
            race: $('#race').val()
        }, function( raceListJson ) {
            ajax.getJsonData('/rest/personage/all', function (personageListJson) {
                renderPersonages(personageListJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Персонаж создан успешно.'
                });
            }, errorHandler);
        });
    });
});

function renderPersonages(personageListJson) {
    $("#personageList").html($("#personageListTemplate").tmpl(personageListJson));

    $('.deletePersonage').click(function() {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/delete', id, function(personageListJson) {
            $(_this).parent().parent().fadeToggle("slow", function() {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function renderRaces(raceListJson) {
    $("#raceSelectTemplate").tmpl(raceListJson).appendTo("#race");
}

function errorHandler(personageListJson) {
    alert("Error: " + personageListJson);
}