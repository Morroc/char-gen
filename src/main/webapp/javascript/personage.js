/**
 * Created by artemk on 9/2/14.
 */
var LEGENDARY_FIVE = 5;

$(document).ready(function () {
    $("#mainMenu").load("main_menu.html");

    var personageId = ajax.getURLParameter('id');

    ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
        renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
    }, errorHandler);

    ajax.getJsonData('/rest/personage/'.concat(personageId).concat('/differentTypesOfMeritsForPersonage'), function (differentTypesOfMeritsForPersonageJson) {
        renderDifferentTypesOfMeritsForPersonageJson(differentTypesOfMeritsForPersonageJson);
    }, errorHandler);

    ajax.getJsonData('/rest/personage/'.concat(personageId).concat('/differentTypesOfFlawsForPersonage'), function (differentTypesOfFlawsForPersonageJson) {
        renderDifferentTypesOfFlawsForPersonageJson(differentTypesOfFlawsForPersonageJson);
    }, errorHandler);

    ajax.getJsonData('/rest/attachedSkill/', function (attachedSkillListJson) {
        renderAttachedSkillListJson(attachedSkillListJson);
    }, errorHandler);

    ajax.getJsonData('/rest/triggerSkill/', function (triggerSkillListJson) {
        renderTriggerSkillListJson(triggerSkillListJson);
    }, errorHandler);

    $(".modalbox").fancybox();

    $("#linkAttachedSkillToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasAttachedSkill = $(this).serializeObject();
        personageHasAttachedSkill.attachedSkill = {id: personageHasAttachedSkill.attachedSkill};
        personageHasAttachedSkill.personage = {id: personageHasAttachedSkill.personage};
        personageHasAttachedSkill.personage.race = {id: personageHasAttachedSkill.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasAttachedSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            new PNotify({
                title: 'Инфо',
                text: 'Прикрепленный навык добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#linkTriggerSkillToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasTriggerSkill = $(this).serializeObject();
        personageHasTriggerSkill.hasTalent = $("#hasTalent").prop('checked');
        personageHasTriggerSkill.hasTeacher = $("#hasTeacher").prop('checked');
        personageHasTriggerSkill.triggerSkill = {id: personageHasTriggerSkill.triggerSkill};
        personageHasTriggerSkill.personage = {id: personageHasTriggerSkill.personage};
        personageHasTriggerSkill.personage.race = {id: personageHasTriggerSkill.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasTriggerSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
            new PNotify({
                title: 'Инфо',
                text: 'Тригерный навык добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
        }, errorHandler);
    });

    $("#linkMeritToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasMerit = $(this).serializeObject();
        personageHasMerit.merit = {id: personageHasMerit.merit};
        personageHasMerit.personage = {id: personageHasMerit.personage};
        personageHasMerit.personage.race = {id: personageHasMerit.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasMerit), function (differentTypesOfMeritsForPersonageJson) {
            renderDifferentTypesOfMeritsForPersonageJson(differentTypesOfMeritsForPersonageJson);
            new PNotify({
                title: 'Инфо',
                text: 'Достоинство добавлено успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
            ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
                $("#personageName").html($("#personageNameTemplate").tmpl(personageWithAllRelatedEntitiesJson));
            }, errorHandler);
        }, errorHandler);
    });

    $("#linkFlawToPersonageForm").submit(function (event) {
        event.preventDefault();
        $.fancybox.showLoading();
        var personageHasFlaw = $(this).serializeObject();
        personageHasFlaw.flaw = {id: personageHasFlaw.flaw};
        personageHasFlaw.personage = {id: personageHasFlaw.personage};
        personageHasFlaw.personage.race = {id: personageHasFlaw.personage.race};
        ajax.putJsonData($(this), JSON.stringify(personageHasFlaw), function (differentTypesOfFlawsForPersonageJson) {
            renderDifferentTypesOfFlawsForPersonageJson(differentTypesOfFlawsForPersonageJson);
            new PNotify({
                title: 'Инфо',
                text: 'Недостаток добавлен успешно.'
            });
            $.fancybox.close();
            $.fancybox.hideLoading();
            ajax.getJsonData('/rest/personage/'.concat(personageId), function (personageWithAllRelatedEntitiesJson) {
                $("#personageName").html($("#personageNameTemplate").tmpl(personageWithAllRelatedEntitiesJson));
            }, errorHandler);
        }, errorHandler);
    });

    $("#updatePersonageAttributeForm").submit(function (event) {
        event.preventDefault();
        var personageHasAttribute = $(this).serializeObject();
        personageHasAttribute.attribute = {id: personageHasAttribute.attribute};
        personageHasAttribute.personage = {id: personageHasAttribute.personage};
        personageHasAttribute.personage.race = {id: personageHasAttribute.personage.race};
        ajax.postJsonData($(this), JSON.stringify(personageHasAttribute), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
        }, errorHandler);
    });

    $("#updatePersonageAttachedSkillForm").submit(function (event) {
        event.preventDefault();
        var personageHasAttachedSkill = $(this).serializeObject();
        personageHasAttachedSkill.attachedSkill = {id: personageHasAttachedSkill.attachedSkill};
        personageHasAttachedSkill.personage = {id: personageHasAttachedSkill.personage};
        personageHasAttachedSkill.personage.race = {id: personageHasAttachedSkill.personage.race};
        ajax.postJsonData($(this), JSON.stringify(personageHasAttachedSkill), function (personageWithAllRelatedEntitiesJson) {
            renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson);
        }, errorHandler);
    });

    $('.generate').click(function () {
        ajax.getJsonData('/rest/personage/setGenerated', function (personageWithAllRelatedEntitiesJson) {
            location.reload();
        }, errorHandler);
    });
});

function renderPersonageWithAllRelatedEntitiesJson(personageWithAllRelatedEntitiesJson) {
    var selector = $("#personageNameTemplate");
    $("#personageTitle").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageName").html(selector.tmpl(personageWithAllRelatedEntitiesJson));
    $("#generatedButton").html($("#generatedButtonTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#attachedSkillPersonageId").html($("#personageByAttachedSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#triggerSkillPersonageId").html($("#personageByTriggerSkillTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#meritPersonageId").html($("#personageByMeritTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#flawPersonageId").html($("#personageByFlawTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageByAttributeId").html($("#personageByAttributeTemplate").tmpl(personageWithAllRelatedEntitiesJson));
    $("#personageByAttachedSkillId").html($("#personageByAttachedSkilTemplate").tmpl(personageWithAllRelatedEntitiesJson));

    $("#personageHasAttributeList").html($("#personageHasAttributeListTemplate").tmpl(personageWithAllRelatedEntitiesJson));

    $("#personageHasAttachedSkillList").html($("#personageHasAttachedSkillListTemplate").tmpl(personageWithAllRelatedEntitiesJson));

    $('.unlinkAttachedSkillFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageAttachedSkill', id, function (returnData) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
                $("#personageName").html(selector.tmpl(returnData));
            });
        }, errorHandler);
    });

    if(personageWithAllRelatedEntitiesJson.personage.generated) {
        $("#linkFlawToPersonageButton").hide();
    }

    $("#personageHasTriggerSkillList").html($("#personageHasTriggerSkillListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageTriggerSkills']));

    $('.unlinkTriggerSkillFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageTriggerSkill', id, function (personageWithAllRelatedEntitiesJson) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
                $("#personageName").html($("#personageNameTemplate").tmpl(personageWithAllRelatedEntitiesJson));
            });
        }, errorHandler);
    });

    $("#personageHasBirthMeritList").html($("#personageHasBirthMeritListTemplate").tmpl(personageWithAllRelatedEntitiesJson.valueOf()['personageBirthMerits']));

    $('.plusAttribute').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();
        var raceId = personageWithAllRelatedEntitiesJson.personage.race.id;

        ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
            updateAttributeValue(raceWithAllRelatedEntitiesJson, personageWithAllRelatedEntitiesJson, id, '+');
        }, errorHandler);
    });

    $('.minusAttribute').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();
        var raceId = personageWithAllRelatedEntitiesJson.personage.race.id;

        ajax.getJsonData('/rest/race/'.concat(raceId), function (raceWithAllRelatedEntitiesJson) {
            updateAttributeValue(raceWithAllRelatedEntitiesJson, personageWithAllRelatedEntitiesJson, id, '-');
        }, errorHandler);
    });

    $('.plusAttachedSkill').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();

        updateAttachedSkillValue(personageWithAllRelatedEntitiesJson, id, '+');
    });

    $('.minusAttachedSkill').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();

        updateAttachedSkillValue(personageWithAllRelatedEntitiesJson, id, '-');
    });

    $('#linkMeritToPersonageSubmitButton').click(function (event) {
        event.preventDefault();
        var meritId = $(this).parent().parent().parent().find(":selected").val();

        ajax.getJsonData('/rest/merit/', function (meritListJson) {
            checkAttributePreconditionsForMerit(personageWithAllRelatedEntitiesJson, meritListJson, meritId);
        }, errorHandler);
    });

    $('.primary').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();

        updateAttributePriority(personageWithAllRelatedEntitiesJson, id, 'PRIMARY');
    });

    $('.secondary').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();

        updateAttributePriority(personageWithAllRelatedEntitiesJson, id, 'SECONDARY');
    });

    $('.basic').click(function () {
        var id = $(this).parent().parent().find("[name=id]").val();

        updateAttributePriority(personageWithAllRelatedEntitiesJson, id, 'BASIC');
    });

    ajax.getJsonData('/rest/personage/personageTriggerSkill/skillLevels', function (skillLevelList) {
        if (!personageWithAllRelatedEntitiesJson.personage.generated) {
            skillLevelList = {name : 'BASIC'};
        }
        $("#currentLevel").html($("#skillLevelTemplate").tmpl(skillLevelList));
    }, errorHandler);
}

function renderAttachedSkillListJson(attachedSkillListJson) {
    $("#attachedSkill").html($("#attachedSkillByPersonageSelectTemplate").tmpl(attachedSkillListJson));
}

function renderTriggerSkillListJson(triggerSkillListJson) {
    $("#triggerSkill").html($("#triggerSkillByPersonageSelectTemplate").tmpl(triggerSkillListJson));
}

function errorHandler(personageWithAllRelatedEntitiesJson) {
    alert("Fucking error!!! " + personageWithAllRelatedEntitiesJson);
}

function renderDifferentTypesOfMeritsForPersonageJson(differentTypesOfMeritsForPersonageJson) {
    $("#personageHasMeritList").html($("#personageHasMeritListTemplate").tmpl(differentTypesOfMeritsForPersonageJson));

    $("#merit").html($("#meritsSelectTemplate").tmpl(differentTypesOfMeritsForPersonageJson));

    $('.unlinkMeritFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageMerit', id, function (returnData) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
                $("#personageName").html($("#personageNameTemplate").tmpl(returnData));
            });
        }, errorHandler);
    });

    $('.unlinkMeritFromPersonageByRaceHasMeritId').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageMerit/byRaceHasMeritId', id, function (returnData) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
                $("#personageName").html($("#personageNameTemplate").tmpl(returnData));
            });
        }, errorHandler);
    });
}

function renderDifferentTypesOfFlawsForPersonageJson(differentTypesOfFlawsForPersonageJson) {
    $("#personageHasFlawList").html($("#personageHasFlawListTemplate").tmpl(differentTypesOfFlawsForPersonageJson));

    $("#flaw").html($("#flawsSelectTemplate").tmpl(differentTypesOfFlawsForPersonageJson.valueOf()['allFlawsWithoutDefaultForRace']));

    $('.unlinkFlawFromPersonage').click(function () {
        var id = $(this).parent().find("[name=id]").val();
        _this = $(this);
        ajax.deleteJsonData('/rest/personage/personageFlaw', id, function (returnData) {
            $(_this).parent().parent().fadeToggle("slow", function () {
                $(this).remove();
                $("#personageName").html($("#personageNameTemplate").tmpl(returnData));
            });
        }, errorHandler);
    });
}

function updateAttributeValue(raceWithAllRelatedEntitiesJson, personageWithAllRelatedEntitiesJson, personageAttributeId, plusOrMinusOne) {
    var personageHasAttribute;
    for (var i = 0; i < personageWithAllRelatedEntitiesJson.personageAttributes.length; i++) {
        if (personageWithAllRelatedEntitiesJson.personageAttributes[i].id == personageAttributeId) {
            personageHasAttribute = personageWithAllRelatedEntitiesJson.personageAttributes[i];
        }
    }

    var raceHasAttributeByPersonage;
    for (var a = 0; a < raceWithAllRelatedEntitiesJson.raceAttributes.length; a++) {
        if (raceWithAllRelatedEntitiesJson.raceAttributes[a].attribute.id == personageHasAttribute.attribute.id) {
            raceHasAttributeByPersonage = raceWithAllRelatedEntitiesJson.raceAttributes[a];
        }
    }

    if (plusOrMinusOne == '+') {
        if (!personageWithAllRelatedEntitiesJson.personage.generated) {
            var maxGeneratingValue = personageHasAttribute.priority.value + LEGENDARY_FIVE - raceHasAttributeByPersonage.baseCost;
            if (maxGeneratingValue <= 0 || personageHasAttribute.currentValue == maxGeneratingValue) {
                alert("Достигнут максимум атрибута по генерильной цене");
                return;
            }
        }
        if (personageHasAttribute.currentValue == raceHasAttributeByPersonage.maxValue) {
            alert("Для данной расы атрибут " + raceHasAttributeByPersonage.attribute.name
                + " не может быть больше " + raceHasAttributeByPersonage.maxValue);
            return;
        }
        personageHasAttribute.currentValue = personageHasAttribute.currentValue + 1;
    } else {
        if (personageHasAttribute.currentValue == 1) {
            alert("Атрибут не может быть меньше 1");
            return;
        }
        personageHasAttribute.currentValue = personageHasAttribute.currentValue - 1;
    }
    $("#attributeByPersonageId").html($("#attributeByPersonageTemplate").tmpl(personageHasAttribute));
    $("#attributeCurrentValue").html($("#currentValueTemplate").tmpl(personageHasAttribute));
    $("#priority").html($("#priorityTemplate").tmpl(personageHasAttribute));
    $('#updatePersonageAttributeForm').attr('action', '/rest/personage/personageAttribute/' + personageHasAttribute.id);
    $("#updatePersonageAttributeForm").submit();
}

function updateAttachedSkillValue(personageWithAllRelatedEntitiesJson, personageAttachedSkillId, plusOrMinusOne) {
    var personageHasAttachedSkill;
    for (var i = 0; i < personageWithAllRelatedEntitiesJson.personageAttachedSkills.length; i++) {
        if (personageWithAllRelatedEntitiesJson.personageAttachedSkills[i].id == personageAttachedSkillId) {
            personageHasAttachedSkill = personageWithAllRelatedEntitiesJson.personageAttachedSkills[i];
        }
    }

    if (plusOrMinusOne == '+') {
        if (checkAttachedSkillMaxValueWithGeneratedCost(personageWithAllRelatedEntitiesJson, personageHasAttachedSkill)) {
            personageHasAttachedSkill.currentValue = personageHasAttachedSkill.currentValue + 1;
        } else {
            return;
        }
    } else {
        if (personageHasAttachedSkill.currentValue == 1) {
            alert("Навык не может быть меньше 1");
            return;
        }
        personageHasAttachedSkill.currentValue = personageHasAttachedSkill.currentValue - 1;
    }
    $("#attachedSkillByPersonageId").html($("#attachedSkillByPersonageTemplate").tmpl(personageHasAttachedSkill.attachedSkill));
    $("#attachedSkillValue").html($("#attachedSkillValueTemplate").tmpl(personageHasAttachedSkill));
    $('#updatePersonageAttachedSkillForm').attr('action', '/rest/personage/personageAttachedSkill/' + personageHasAttachedSkill.id);
    $("#updatePersonageAttachedSkillForm").submit();
}

function checkAttributePreconditionsForMerit(personageWithAllRelatedEntitiesJson, meritListJson, meritId) {
    var merit;
    for (var i = 0; i < meritListJson.length; i++) {
        if (meritListJson[i].merit.id == meritId) {
            merit = meritListJson[i];
        }
    }

    for (var j = 0; j < merit.preconditions.length; j++) {
        for (a = 0; a < personageWithAllRelatedEntitiesJson.personageAttributes.length; a++) {
            if (merit.preconditions[j].attribute.id == personageWithAllRelatedEntitiesJson.personageAttributes[a].attribute.id) {
                var personageHasAttribute = personageWithAllRelatedEntitiesJson.personageAttributes[a];
                if (personageHasAttribute.currentValue < merit.preconditions[j].neededValue) {
                    alert("Чтобы взять " + merit.merit.name + " нужно " + personageHasAttribute.attribute.name + " минимум " + merit.preconditions[j].neededValue);
                    return;
                }
            }
        }
    }

    $("#linkMeritToPersonageForm").submit();
}

function updateAttributePriority(personageWithAllRelatedEntitiesJson, personageAttributeId, priority) {
    var personageHasAttribute;
    for (var i = 0; i < personageWithAllRelatedEntitiesJson.personageAttributes.length; i++) {
        if (personageWithAllRelatedEntitiesJson.personageAttributes[i].id == personageAttributeId) {
            personageHasAttribute = personageWithAllRelatedEntitiesJson.personageAttributes[i];
        }
    }

    if (priority == 'BASIC') {
        var attributeValueAfterCorrection;
        if (personageHasAttribute.priority.name == 'PRIMARY') {
            attributeValueAfterCorrection = personageHasAttribute.currentValue - 2;
        }

        if (personageHasAttribute.priority.name == 'SECONDARY') {
            attributeValueAfterCorrection = personageHasAttribute.currentValue - 1;
        }

        if (attributeValueAfterCorrection > 1) {
            personageHasAttribute.currentValue = attributeValueAfterCorrection;
        } else {
            personageHasAttribute.currentValue = 1;
        }
    }

    personageHasAttribute.priority.name = priority;

    $("#attributeByPersonageId").html($("#attributeByPersonageTemplate").tmpl(personageHasAttribute));
    $("#attributeCurrentValue").html($("#currentValueTemplate").tmpl(personageHasAttribute));
    $("#priority").html($("#priorityTemplate").tmpl(personageHasAttribute));
    $('#updatePersonageAttributeForm').attr('action', '/rest/personage/personageAttribute/' + personageHasAttribute.id);
    $("#updatePersonageAttributeForm").submit();
}

function checkAttachedSkillMaxValueWithGeneratedCost(personageWithAllRelatedEntitiesJson, personageHasAttachedSkill) {
    if (!personageWithAllRelatedEntitiesJson.personage.generated) {
        if (personageHasAttachedSkill.currentValue + 1 > 5) {
            alert("Вы не можете взять навык выше 5 по генерильной цене");
            return false;
        }
        if (personageHasAttachedSkill.currentValue + 1 > 3) {
            var fourSkillCount = 0;
            var fiveSkillExist = false;
            for (var j = 0; j < personageWithAllRelatedEntitiesJson.personageAttachedSkills.length; j++) {
                if (personageWithAllRelatedEntitiesJson.personageAttachedSkills[j].currentValue == 4) {
                    fourSkillCount++;
                }

                if (personageWithAllRelatedEntitiesJson.personageAttachedSkills[j].currentValue == 5) {
                    fiveSkillExist = true;
                }
            }

            if (personageHasAttachedSkill.currentValue + 1 > 4 && fiveSkillExist) {
                alert("Вы не можете взять этот навык выше 4 по генерильной цене,\n" +
                    "вы уже имеете 1 навык на 5 по генерильной цене");
                return false;
            }

            if ((fourSkillCount == 2 && fiveSkillExist) || fourSkillCount == 3 && personageHasAttachedSkill.currentValue + 1 == 4) {
                alert("Вы не можете взять этот навык выше 3 по генерильной цене,\n" +
                    "вы уже имеете 2 навыка на 4 и 1 навык на 5 или 3 навыка на 4 по генерильной цене");
                return false;
            }
        }
    }
    return true;
}

