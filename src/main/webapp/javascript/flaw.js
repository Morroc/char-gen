$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    ajax.getJsonData('/rest/flaw/all', function (flawListJson) {
        render(flawListJson);
    }, errorHandler);

    $("#addFlawForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            name: $('#name').val(),
            cost: $('#cost').val(),
            description: $('#description').val(),
            turnOffPreconditions: $('#turnOffPreconditions').val()
        }, function( flawListJson ) {
            ajax.getJsonData('/rest/flaw/all', function (flawListJson) {
                render(flawListJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Недостаток создан успешно.'
                });
            }, errorHandler);
        });
    });
});

function render(flawListJson) {
    $("#flawList").html($("#flawListTemplate").tmpl(flawListJson));

    $('.deleteFlaw').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/flaw/delete', id, function (flawListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(flawListJson) {
    alert("Error: " + flawListJson);
}