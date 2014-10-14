/**
 * Created by artemk on 8/26/14.
 */

$(document).ready(function(){
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/personage/', function(personageListJson) {
        renderPersonages(personageListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/race/', function(raceListJson) {
        renderRaces(raceListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addPersonageForm").submit(function(event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var person = $(this).serializeObject();
        person.race = {id: person.race};
        ajax.putJsonData($(this), JSON.stringify(person), function(personageListJson) {
            renderPersonages(personageListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Персонаж создан успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updatePersonageForm").submit(function(event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var person = $(this).serializeObject();
        person.race = {id: person.race};
        ajax.postJsonData($(this), JSON.stringify(person), function (personageListJson) {
            renderPersonages(personageListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Персонаж сохранен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function renderPersonages(personageListJson) {
    window.personageListJson = personageListJson;
    $("#personageList").html($("#personageListTemplate").tmpl(personageListJson));

    $('.deletePersonage').click(function() {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/', id, function(personageListJson) {
            $(_this).parent().parent().fadeToggle("slow", function() {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updatePersonage").fancybox({
        'afterLoad' : function(obj){
            var id = $(obj.element).parent().find("[name=id]").val();
            for(var i = 0; i < window.personageListJson.length; i++) {
                if(window.personageListJson[i].id == id) {
                    var personageRace = window.personageListJson[i].race;
                    $('#updatePersonageForm').attr('action', '/rest/personage/' + window.personageListJson[i].id);
                    $('#updateName').val(window.personageListJson[i].name);
                    $('#updateAge').val(window.personageListJson[i].age);
                    $('#updateRace').val(window.personageListJson[i].race);
                    $('#updateExperience').val(window.personageListJson[i].experience);
                    break;
                }
            }
            $("#updateRace").html($("#updateRaceSelectTemplate").tmpl(personageRace));
        }
    });
}

function renderRaces(raceListJson) {
    $("#raceSelectTemplate").tmpl(raceListJson).appendTo("#race");
}

function errorHandler(personageListJson) {
    $.fancybox.hideLoading();
    alert("Error: " + personageListJson);
}