$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/merit/', function (meritListJson) {
        renderMeritListJson(meritListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attribute/', function (attributeListJson) {
        renderAttributeListJson(attributeListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addMeritForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function (meritListJson) {
            renderMeritListJson(meritListJson);
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
            renderMeritListJson(meritListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Достоинтсво сохранено успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#addPreconditionForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var precondition = $(this).serializeObject();
        precondition.attribute = {id: precondition.attribute};
        precondition.merit = {id: precondition.merit};
        ajax.putJsonData($(this), JSON.stringify(precondition), function (meritListJson) {
            renderMeritListJson(meritListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Условие добавлено успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

});

function renderMeritListJson(meritListJson) {
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

    $('.deletePrecondition').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/merit/precondition/', id, function (preconditionListJson) {
            $(_this).parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateMerit").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            for (var i = 0; i < window.meritListJson.length; i++) {
                if (window.meritListJson[i].merit.id == id) {
                    $('#updateMeritForm').attr('action', '/rest/merit/' + window.meritListJson[i].merit.id);
                    $('#updateName').val(window.meritListJson[i].merit.name);
                    $('#updateCost').val(window.meritListJson[i].merit.cost);
                    $('#updateDescription').val(window.meritListJson[i].merit.description);
                    $('#updateActionBonus').val(window.meritListJson[i].merit.actionBonus);
                    break;
                }
            }
        }
    });

    $(".addPrecondition").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            $('#merit').val(id);
        }
    });
}

function renderAttributeListJson(attributeListJson) {
    $("#attribute").html($("#attributeSelectTemplate").tmpl(attributeListJson));
}

function errorHandler(meritListJson) {
    alert("Error: " + meritListJson);
}