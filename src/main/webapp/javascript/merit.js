$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/merit/', function (meritListJson) {
        render(meritListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addMeritForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function (meritListJson) {
            render(meritListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Достоинтсво создано успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updateMeritForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (meritListJson) {
            render(meritListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Достоинтсво сохранено успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

});

function render(meritListJson) {
    window.meritListJson = meritListJson;
    $("#meritList").html($("#meritListTemplate").tmpl(meritListJson));

    $('.deleteMerit').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/merit/', id, function (meritListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateMerit").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            for (var i = 0; i < window.meritListJson.length; i++) {
                if (window.meritListJson[i].id == id) {
                    $('#updateMeritForm').attr('action', '/rest/merit/' + window.meritListJson[i].id);
                    $('#updateName').val(window.meritListJson[i].name);
                    $('#updateCost').val(window.meritListJson[i].cost);
                    $('#updateDescription').val(window.meritListJson[i].description);
                    $('#updateActionBonus').val(window.meritListJson[i].actionBonus);
                    break;
                }
            }
        }
    });
}

function errorHandler(meritListJson) {
    alert("Error: " + meritListJson);
}