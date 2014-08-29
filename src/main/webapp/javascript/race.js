$(document).ready(function () {
    var raceId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
        render(raceWithAllRelatedEntitiesJson);
    }, errorHandler);
});

function render(raceWithAllRelatedEntitiesJson) {
    var selector = $("#raceNameTemplate");
    $("#raceTitle").html(selector.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceName").html(selector.tmpl(raceWithAllRelatedEntitiesJson));
}

function errorHandler(raceWithAllRelatedEntitiesJson) {
    alert("Error: " + raceWithAllRelatedEntitiesJson);
}