var usersRestModule = angular.module("usersRestModule", ["ngResource", "restConfigurationModule"]);

usersRestModule.constant("subUrlUsers", "users/");

usersRestModule.factory("usersRest", function(RestConfiguration, subUrlUsers, $resource) {

    // if a parameter is missing, angular remove /:missingParameter from the URL
    var Users = $resource(RestConfiguration.url + subUrlUsers + ':action/:id',
        {},
        {
            list: {
                method: "GET",
                params: { action: "list" }
            },
            get: {
                method: "GET",
                params: { action: "get" }
            },
            save: {
            	method: "POST",
            	params: { action: "save" }
            },
            // delete is a keyword :(
            delet: {
            	method: "DELETE",
            	params: { action: "delete" }
            }
        });

    return {
    	// Defines the default parameters directly in the REST service
    	listDefaultParameters: function(UrlUtils, textSearchedP, firstResultP, maxResultsP, orderByP, orderP) {
        	var textSearched = "";
        	if (textSearchedP) {
        		textSearched = textSearchedP;
        	}
        	var firstResult = 0;
        	if (firstResultP) {
        		firstResult = firstResultP;
        	}
        	var maxResults = 10;
        	if (maxResultsP) {
        		maxResults = maxResultsP;
        	}
        	var orderBy = "lastname";
        	if (orderByP) {
        		orderBy = orderByP;
        	}
        	var order = UrlUtils.URL_ASC_VALUE;
        	if (orderP) {
        		order = orderP;
        	}
        	
        	return {
        		textSearched: textSearched,
        		firstResult: firstResult,
        		maxResults: maxResults,
        		orderBy: orderBy,
        		order: order
        	};
    	},
    	// Same stuff for the type ahead
    	typeAheadDefaultParameters: function(UrlUtils, textSearchedP) {
        	var textSearched = "";
        	if (textSearchedP) {
        		textSearched = textSearchedP;
        	}
        	
        	return {
        		textSearched: textSearched,
        		firstResult: 0,
        		maxResults: 10,
        		orderBy: "lastname",
        		order: UrlUtils.URL_ASC_VALUE
        	};
    	},
        list: function(textSearched, firstResult, maxResults, orderBy, order, callbackFunction, errorFunction) {
        	Users.list(
        			{
        				textSearched: textSearched,
        				firstResult: firstResult,
        				maxResults: maxResults,
        				orderBy: orderBy,
        				order: order
        			},
                    callbackFunction,
                    errorFunction
        	);
        },
        get: function(id, callbackFunction, errorFunction){
        	Users.get(
        			{ id: id },
                    callbackFunction,
                    errorFunction
        	);
        },
        save: function(user, callbackFunction, errorFunction){
        	Users.save(
        			user,
                    callbackFunction,
                    errorFunction
        	);
        },
        // delete is a keyword :(
        delet: function(id, callbackFunction, errorFunction){
        	Users.delet(
        			{ id: id },
                    callbackFunction,
                    errorFunction
        	);
        }
    };
	
});