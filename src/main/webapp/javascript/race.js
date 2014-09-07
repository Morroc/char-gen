$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var raceId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
        renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attribute/', function (attributeListJson) {
        renderAttributeListJson(attributeListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/flaw/', function (flawListJson) {
        renderFlawListJson(flawListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/merit/', function (meritListJson) {
        renderMeritListJson(meritListJson);
    }, errorHandler);

    $(".modalbox").fancybox();

    $("#linkAttributeToRaceForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var raceHasAttribute = $(this).serializeObject();
        raceHasAttribute.attribute = {id: raceHasAttribute.attribute};
        raceHasAttribute.race = {id: raceHasAttribute.race};
        ajax.putJsonData($(this), JSON.stringify(raceHasAttribute), function (raceWithAllRelatedEntitiesJson) {
            renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
            $.fancybox.close();
            new PNotify({
                title: 'Инфо',
                text: 'Атрибут добавлен к расе успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#linkMeritToRaceForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var raceHasMerit = $(this).serializeObject();
        raceHasMerit.merit = {id: raceHasMerit.merit};
        raceHasMerit.race = {id: raceHasMerit.race};
        raceHasMerit.defaultForRace = $("#defaultForRaceMerit").prop('checked');
        ajax.putJsonData($(this), JSON.stringify(raceHasMerit), function (raceWithAllRelatedEntitiesJson) {
            renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
            $.fancybox.close();
            new PNotify({
                title: 'Инфо',
                text: 'Достоинство добавлено к расе успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#linkFlawToRaceForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var raceHasFlaw = $(this).serializeObject();
        raceHasFlaw.flaw = {id: raceHasFlaw.flaw};
        raceHasFlaw.race = {id: raceHasFlaw.race};
        ajax.putJsonData($(this), JSON.stringify(raceHasFlaw), function (raceWithAllRelatedEntitiesJson) {
            renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson);
            $.fancybox.close();
            new PNotify({
                title: 'Инфо',
                text: 'Недостаток добавлен к расе успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });
});

function renderRaceWithAllRelatedEntitiesJson(raceWithAllRelatedEntitiesJson) {
    var raceNameTemplate = $("#raceNameTemplate");
    $("#raceTitle").html(raceNameTemplate.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceName").html(raceNameTemplate.tmpl(raceWithAllRelatedEntitiesJson));
    $("#raceByAttributeTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkAttributeToRaceForm");
    $("#raceByMeritTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkMeritToRaceForm");
    $("#raceByFlawTemplate").tmpl(raceWithAllRelatedEntitiesJson).appendTo("#linkFlawToRaceForm");

    $("#raceHasAttributeList").html($("#raceHasAttributeListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceAttributes']));

    $('.unlinkAttributeFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/raceAttribute', id, function (raceAttribute) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $("#raceHasMeritList").html($("#raceHasMeritListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceMerits']));

    $('.unlinkMeritFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/raceMerit', id, function (raceMerit) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });

    $("#raceHasFlawList").html($("#raceHasFlawListTemplate").tmpl(raceWithAllRelatedEntitiesJson.valueOf()['raceFlaws']));

    $('.unlinkFlawFromRace').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/race/raceFlaw', id, function (raceFlaw) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
            });
        }, errorHandler);
    });
}

function renderAttributeListJson(attributeListJson) {
    $("#attributeByRaceSelectTemplate").tmpl(attributeListJson).appendTo("#attribute");
}

function renderFlawListJson(flawListJson) {
    $("#flawByRaceSelectTemplate").tmpl(flawListJson).appendTo("#flaw");
}

function renderMeritListJson(meritListJson) {
    $("#meritByRaceSelectTemplate").tmpl(meritListJson).appendTo("#merit");
}

function errorHandler(raceWithAllRelatedEntitiesJson) {
    alert("Error: " + raceWithAllRelatedEntitiesJson);
}