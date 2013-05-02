var rightsRestModule = angular.module("rightsRestModule", ["ngResource", "restConfigurationModule"]);

rightsRestModule.constant("subUrlRights", "rights/");

rightsRestModule.factory("rightsRest", function(RestConfiguration, subUrlRights, $resource) {

    // if a parameter is missing, angular remove /:missingParameter from the URL
    var Rights = $resource(RestConfiguration.url + subUrlRights + ':action/:id',
        {},
        {
            list: {
                method: "GET",
                params: { action: "list" }
            },
            listAll: {
                method: "GET",
                params: { action: "listAll" },
                isArray: true
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
        	var maxResults = 50;
        	if (maxResultsP) {
        		maxResults = maxResultsP;
        	}
        	var orderBy = "label";
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
        		orderBy: "label",
        		order: UrlUtils.URL_ASC_VALUE
        	};
    	},
        list: function(textSearched, firstResult, maxResults, orderBy, order, callbackFunction, errorFunction) {
        	Rights.list(
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
        listAll: function(callbackFunction, errorFunction) {
        	Rights.listAll(
        			{},
                    callbackFunction,
                    errorFunction
        	);
        },
        get: function(id, callbackFunction, errorFunction){
        	Rights.get(
        			{ id: id },
                    callbackFunction,
                    errorFunction
        	);
        },
        save: function(right, callbackFunction, errorFunction){
        	Rights.save(
        			right,
                    callbackFunction,
                    errorFunction
        	);
        },
        // delete is a keyword :(
        delet: function(id, callbackFunction, errorFunction){
        	Rights.delet(
        			{ id: id },
                    callbackFunction,
                    errorFunction
        	);
        }
    };
	
});
