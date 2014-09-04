$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/race/', function (raceListJson) {
        render(raceListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addRaceForm").submit(function(event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function(raceListJson) {
            render(raceListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Раса создана успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updateRaceForm").submit(function(event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (raceListJson) {
            render(raceListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Раса сохранена успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function render(raceListJson) {
    window.raceListJson = raceListJson;
    $("#raceList").html($("#raceListTemplate").tmpl(raceListJson));

    $('.deleteRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/', id, function (raceListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateRace").fancybox({
        'afterLoad' : function(obj){
            var id = $(obj.element).parent().find("[name=id]").val();
            for(var i = 0; i < window.raceListJson.length; i++) {
                if(window.raceListJson[i].id == id) {
                    $('#updateRaceForm').attr('action', '/rest/race/' + window.raceListJson[i].id);
                    $('#updateName').val(window.raceListJson[i].name);
                    $('#updateMaxAge').val(window.raceListJson[i].maxAge);
                    break;
                }
            }
        }
    });
}

function errorHandler(raceListJson) {
    alert("Error: " + raceListJson);
}