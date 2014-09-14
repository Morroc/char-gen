$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/birthMerit/', function (birthMeritListJson) {
        render(birthMeritListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addBirthMeritForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function (birthMeritListJson) {
            render(birthMeritListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Врожденное достоинтсво создано успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updateBirthMeritForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (birthMeritListJson) {
            render(birthMeritListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Врожденное достоинтсво сохранено успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

});

function render(birthMeritListJson) {
    window.birthMeritListJson = birthMeritListJson;
    $("#birthMeritList").html($("#birthMeritListTemplate").tmpl(birthMeritListJson));

    $('.deleteBirthMerit').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/birthMerit/', id, function (birthMeritListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateBirthMerit").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            for (var i = 0; i < window.birthMeritListJson.length; i++) {
                if (window.birthMeritListJson[i].id == id) {
                    $('#updateBirthMeritForm').attr('action', '/rest/birthMerit/' + window.birthMeritListJson[i].id);
                    $('#updateName').val(window.birthMeritListJson[i].name);
                    $('#updateCost').val(window.birthMeritListJson[i].cost);
                    $('#updateDescription').val(window.birthMeritListJson[i].description);
                    $('#updateActionBonus').val(window.birthMeritListJson[i].actionBonus);
                    break;
                }
            }
        }
    });
}

function errorHandler(birthMeritListJson) {
    alert("Error: " + birthMeritListJson);
}