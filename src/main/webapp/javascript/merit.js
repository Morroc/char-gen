$(document).ready(function () {

    ajax.getJsonData('/rest/merit/all', function (meritListJson) {
        render(meritListJson);
    }, errorHandler);

    $("#addMeritForm").submit(function(event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            name: $('#name').val(),
            cost: $('#cost').val(),
            description: $('#description').val(),
            turnOffPreconditions: $('#turnOffPreconditions').val()
        }, function( meritListJson ) {
            ajax.getJsonData('/rest/merit/all', function (meritListJson) {
                render(meritListJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Достоинство создано успешно.'
                });
            }, errorHandler);
        });
    });
});

function render(meritListJson) {
    $("#meritList").html($("#meritListTemplate").tmpl(meritListJson));

    $('.deleteMerit').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/merit', id, function (meritListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(meritListJson) {
    alert("Error: " + meritListJson);
}