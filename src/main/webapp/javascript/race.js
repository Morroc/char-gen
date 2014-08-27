$(document).ready(function () {

    ajax.getJsonData('/rest/race/all', function (data) {
        render(data);
    }, errorHandler);

//    $("#addRaceForm").submit(function(){
//        alert("Submitted");
//    });

    /* Adding a handler to the submit event */

    $("#addRaceForm").submit(function(event) {

        /*Stop the usual form submission event*/

        event.preventDefault();

        /* get the elements required for the post method*/

        var $form = $( this ),

            url = $form.attr( 'action' );

        /* post method */

        var posting = $.post( url, { name: $('#name').val(), maxAge: $('#maxAge').val()}, function( data ) {

            alert('Race has been added');

            ajax.getJsonData('/rest/race/all', function (data) {
                render(data);
            }, errorHandler);

        });

    });
});

function render(data) {
    $("#raceList").html($("#raceListTemplate").tmpl(data));

    $('.deleteRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race', id, function (data) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function errorHandler(data) {
    alert("Error: " + data);
}