/**
 * Created by artemk on 9/2/14.
 */
$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var personageId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
        renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
    }, errorHandler);
});

function renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson) {
    var selector = $("#personageNameTemplate");
    $("#personageTitle").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageName").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
}

function errorHandler(personageWithAllRelatedEntitiesJson) {
    alert("Fucking error!!! " + personageWithAllRelatedEntitiesJson);
}

