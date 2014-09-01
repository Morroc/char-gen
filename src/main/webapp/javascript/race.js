$(document).ready(function () {
    var raceId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
        renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attribute/all', function (attributeListJson) {
        renderAttributeListJson(attributeListJson);
    }, errorHandler);

    $("#linkAttributeToRaceForm").submit(function (event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            attributeByRace: $('#attributeByRace').val(),
            raceByAttribute: $('#raceByAttribute').val(),
            baseCost: $('#baseCost').val(),
            maxValue: $('#maxValue').val(),
            from1To3NonGeneratingCost: $('#from1To3NonGeneratingCost').val(),
            from3To6NonGeneratingCost: $('#from3To6NonGeneratingCost').val(),
            from6To9NonGeneratingCost: $('#from6To9NonGeneratingCost').val(),
            from9To12NonGeneratingCost: $('#from9To12NonGeneratingCost').val()
        }, function (raceWithAllRelatedEntitiesJson) {
            ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
                renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
                new PNotify({
                    title: 'Инфо',
                    text: 'Атрибут добавлен к расе успешно.'
                });
            }, errorHandler);
        });
    });
});

function renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson) {
    var selector = $("#raceNameTemplate");
    $("#raceTitle").html(selector.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceName").html(selector.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceIdTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkAttributeToRaceForm");

    $("#raceHasAttributeList").html($("#raceHasAttributeListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceAttributes']));

    $('.unlinkAttributeFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/unlink', id, function (raceListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function renderAttributeListJson(attributeListJson) {
    $("#attributeByRaceSelectTemplate").tmpl(attributeListJson).appendTo("#attributeByRace");
}

function errorHandler(raceWithAllRelatedEntitiesJson) {
    alert("Error: " + raceWithAllRelatedEntitiesJson);
}