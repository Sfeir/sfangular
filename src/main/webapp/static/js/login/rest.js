var loginRestModule = angular.module("loginRestModule", ["ngResource", "restConfigurationModule"]);

loginRestModule.constant("subUrlLogin", "login/");

loginRestModule.factory("loginRest", function(RestConfiguration, subUrlLogin, $resource) {

    // if a parameter is missing, angular remove /:missingParameter from the URL
    var Login = $resource(RestConfiguration.url + subUrlLogin + ':action',
        {},
        {
            logIn: {
                method: "POST",
                params: { action: "logIn" }
            },
            logOut: {
                method: "GET",
                params: { action: "logOut" }
            },
            // This method is used to test if the session is still alive and to get its informations
            getAuthentication: {
                method: "get",
                params: { action: "getAuthentication" }
            }
        });

    return {
        logIn: function(login, callbackFunction, errorFunction) {
        	Login.logIn(
        			{
        				login: login
        			},
                    callbackFunction,
                    errorFunction
        	);
        },
        logOut: function(callbackFunction, errorFunction) {
        	Login.logOut(
        			{ },
                    callbackFunction,
                    errorFunction
        	);
        },
        getAuthentication: function(callbackFunction, errorFunction) {
        	Login.getAuthentication(
        			{ },
                    callbackFunction,
                    errorFunction
        	);
        }
    };
	
});
