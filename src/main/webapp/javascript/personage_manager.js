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
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function(personageListJson) {
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
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (personageListJson) {
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
                    $('#updatePersonageForm').attr('action', '/rest/personage/' + window.personageListJson[i].id);
                    $('#updateName').val(window.personageListJson[i].name);
                    $('#updateAge').val(window.personageListJson[i].age);
                    $('#updateRace').val(window.personageListJson[i].race);
                    break;
                }
            }
        }
    });
}

function renderRaces(raceListJson) {
    var raceNameTemplate = $("#raceSelectTemplate");
    raceNameTemplate.tmpl(raceListJson).appendTo("#race");
    raceNameTemplate.tmpl(raceListJson).appendTo("#updateRace");
}

function errorHandler(personageListJson) {
    alert("Error: " + personageListJson);
}