/**
 * Created by abykovsky on 8/22/14.
 */
(function() {
    var ajax = window.ajax = {};
    var json = window.json = {};
    var jsonData = window.jsonData = {};

    ajax.post = function ($form, formData, successCallback, errorCallback) {
        $.ajax({
            url: $form.attr('action'),
            type: "POST",
            data: formData,
            contentType: "application/json",
            success: successCallback,
            error: function (data) {
                if (data.status == 200) {
                    var responseJson = jQuery.parseJSON(data.responseText);
                    successCallback(responseJson);
                } else {
                    errorCallback(data);
                }
            }
        });
    };

    ajax.postJsonData = function ($form, formData, successCallback, errorCallback) {
        ajax.post($form, formData, function (data) {
            jsonData = data;
            successCallback(data);
        }, errorCallback);
    };

    ajax.put = function ($form, formData, successCallback, errorCallback) {
        $.ajax({
            url: $form.attr('action'),
            type: "PUT",
            data: formData,
            contentType: "application/json",
            success: successCallback,
            error: function (data) {
                if (data.status == 200) {
                    var responseJson = jQuery.parseJSON(data.responseText);
                    successCallback(responseJson);
                } else {
                    errorCallback(data);
                }
            }
        });
    };

    ajax.putJsonData = function ($form, formData, successCallback, errorCallback) {
        ajax.put($form, formData, function (data) {
            jsonData = data;
            successCallback(data);
        }, errorCallback);
    };

    ajax.delete = function (url, id, successCallback, errorCallback) {
        $.ajax({
            url: url + "/" + id,
            type: "DELETE",
            contentType: "application/json",
            success: successCallback,
            error: function (data) {
                if (data.status == 200) {
                    var responseJson = jQuery.parseJSON(data.responseText);
                    successCallback(responseJson);
                } else {
                    errorCallback(data);
                }
            }
        })/*.done(function (data, textStatus, jqXHR) {
            if (jqXHR.status == 200) {
                var responseJson = jQuery.parseJSON(data.responseText);
                successCallback(responseJson);
            } else if (jqXHR.status == 204) {
                successCallback();
            } else if(jqXHR.status >= 400) {
                errorCallback(data);
            }
        })*/;
    };

    ajax.deleteJsonData = function (url, id, successCallback, errorCallback) {
        ajax.delete(url, id, function (data) {
            if(data) {
                window.jsonData = data;
            }
            successCallback(data);
        }, errorCallback);
    };

    ajax.get = function ($url, params, successCallback, errorCallback) {
        $.ajax({
            url: $url,
            type: "GET",
            data: params,
            contentType: "application/json",
            success: successCallback,
            error: function (data) {
                if (data.status == 200) {
                    var responseJson = jQuery.parseJSON(data.responseText);
                    successCallback(responseJson);
                } else {
                    errorCallback(data);
                }
            }
        });
    };

    /*function retrieves actual state of jsonData*/
    ajax.getJsonData = function (url, successCallback, errorCallback) {
        ajax.get(url, {}, function (data) {
            window.jsonData = data;
            successCallback(data);
        }, errorCallback);
    };

    json.hasErrors = function () {
        if (window.jsonData.errors && window.jsonData.errors.length > 0)
            return true;
        return false;
    };

    ajax.getURLParameter = function (sParamName) {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++)
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParamName)
            {
                return sParameterName[1];
            }
        }
    };

})();

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
