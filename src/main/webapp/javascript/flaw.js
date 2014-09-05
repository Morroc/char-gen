/**
 * Created by artemk on 8/26/14.
 */
$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/flaw/', function (flawListJson) {
        render(flawListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addFlawForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function (flawListJson) {
            render(flawListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Недостаток создан успешно.'
            });
        }, errorHandler);
        $.fancybox.close();
        $.fancybox.hideLoading();
    });

    $("#updateFlawForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (flawListJson) {
            render(flawListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Недостаток сохранен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function render(flawListJson) {
    window.flawListJson = flawListJson;
    $("#flawList").html($("#flawListTemplate").tmpl(flawListJson));

    $('.deleteFlaw').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/flaw/', id, function (flawListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateFlaw").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            for (var i = 0; i < window.flawListJson.length; i++) {
                if (window.flawListJson[i].id == id) {
                    $('#updateFlawForm').attr('action', '/rest/flaw/' + window.flawListJson[i].id);
                    $('#updateName').val(window.flawListJson[i].name);
                    $('#updateCost').val(window.flawListJson[i].cost);
                    $('#updateDescription').val(window.flawListJson[i].description);
                    $('#updateTurnOffPreconditions').val(window.flawListJson[i].turnOffPreconditions);
                    break;
                }
            }
        }
    });
}

function errorHandler(flawListJson) {
    alert("Error: " + flawListJson);
}