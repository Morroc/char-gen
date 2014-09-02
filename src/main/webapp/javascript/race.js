$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var raceId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
        renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attribute/all', function (attributeListJson) {
        renderAttributeListJson(attributeListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/flaw/all', function (flawListJson) {
        renderFlawListJson(flawListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/merit/all', function (meritListJson) {
        renderMeritListJson(meritListJson);
    }, errorHandler);

    $(".modalbox").fancybox();

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
                $.fancybox.close();
                new PNotify({
                    title: 'Инфо',
                    text: 'Атрибут добавлен к расе успешно.'
                });
            }, errorHandler);
        });
    });

    $("#linkMeritToRaceForm").submit(function (event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            meritByRace: $('#meritByRace').val(),
            raceByMerit: $('#raceByMerit').val(),
            raceCost: $('#raceCost').val(),
            defaultForRace: $('#defaultForRaceMerit').prop('checked')
        }, function (raceWithAllRelatedEntitiesJson) {
            ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
                renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
                $.fancybox.close();
                new PNotify({
                    title: 'Инфо',
                    text: 'Достоинство добавлено к расе успешно.'
                });
            }, errorHandler);
        });
    });

    $("#linkFlawToRaceForm").submit(function (event) {
        event.preventDefault();
        var posting = ajax.post($(this), {
            flawByRace: $('#flawByRace').val(),
            raceByFlaw: $('#raceByFlaw').val(),
            defaultForRace: $('#defaultForRaceFlaw').prop('checked')
        }, function (raceWithAllRelatedEntitiesJson) {
            ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
                renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
                $.fancybox.close();
                new PNotify({
                    title: 'Инфо',
                    text: 'Недостаток добавлен к расе успешно.'
                });
            }, errorHandler);
        });
    });
});

function renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson) {
    var selector = $("#raceNameTemplate");
    $("#raceTitle").html(selector.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceName").html(selector.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceByAttributeTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkAttributeToRaceForm");
    $("#raceByMeritTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkMeritToRaceForm");
    $("#raceByFlawTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkFlawToRaceForm");

    $("#raceHasAttributeList").html($("#raceHasAttributeListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceAttributes']));

    $('.unlinkAttributeFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/unlinkAttributeFromRace', id, function (raceListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $("#raceHasMeritList").html($("#raceHasMeritListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceMerits']));

    $('.unlinkMeritFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/unlinkMeritFromRace', id, function (raceListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $("#raceHasFlawList").html($("#raceHasFlawListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceFlaws']));

    $('.unlinkFlawFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/unlinkFlawFromRace', id, function (raceListJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function renderAttributeListJson(attributeListJson) {
    $("#attributeByRaceSelectTemplate").tmpl(attributeListJson).appendTo("#attributeByRace");
}

function renderFlawListJson(flawListJson) {
    $("#flawByRaceSelectTemplate").tmpl(flawListJson).appendTo("#flawByRace");
}

function renderMeritListJson(meritListJson) {
    $("#meritByRaceSelectTemplate").tmpl(meritListJson).appendTo("#meritByRace");
}

function errorHandler(raceWithAllRelatedEntitiesJson) {
    alert("Error: " + raceWithAllRelatedEntitiesJson);
}