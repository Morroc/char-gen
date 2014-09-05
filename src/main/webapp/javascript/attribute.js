/**
 * Created by artemk on 8/26/14.
 */
$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/attribute/', function (attributeListJson) {
        render(attributeListJson);
    }, errorHandler);

    $('.modalbox').fancybox();

    $("#addAttributeForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.putJsonData($(this), JSON.stringify($(this).serializeObject()), function (attributeListJson) {
            render(attributeListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Аттрибут создан успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#updateAttributeForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        ajax.postJsonData($(this), JSON.stringify($(this).serializeObject()), function (attributeListJson) {
            render(attributeListJson);
            new PNotify({
                title: 'Инфо',
                text: 'Аттрибут сохранен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function render(attributeListJson) {
    window.attributeListJson = attributeListJson;
    $("#attributeList").html($("#attributeListTemplate").tmpl(attributeListJson));

    $('.deleteAttribute').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/attribute/', id, function (attributeListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $(".updateAttribute").fancybox({
        'afterLoad': function (obj) {
            var id = $(obj.element).parent().find("[name=id]").val();
            for (var i = 0; i < window.attributeListJson.length; i++) {
                if (window.attributeListJson[i].id == id) {
                    $('#updateAttributeForm').attr('action', '/rest/attribute/' + window.attributeListJson[i].id);
                    $('#updateName').val(window.attributeListJson[i].name);
                    $('#updateActionLevelBonus').val(window.attributeListJson[i].actionLevelBonus);
                    break;
                }
            }
        }
    });
}

function errorHandler(attributeListJson) {
    alert("Error: " + attributeListJson);
}