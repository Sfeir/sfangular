/**
 * Mapping key/value for all the URLs
 */
function getUrlMap() {
	return {
		"URL_HOME": "/",
		"URL_LOGIN": "/login",
		"URL_USERS_LIST": "/users/list",
		"URL_USERS_ADD": "/users/add",
		"URL_USERS_EDIT": "/users/edit"
	};
}

var urlModule = angular.module("urlModule", []);

urlModule.factory("UrlUtils", function(urlMap) {
	
	var UrlUtils = function() {};
	
	// constants for sorting the lists (ascending, descending)
	UrlUtils.URL_ASC_VALUE = "asc";
	UrlUtils.URL_DESC_VALUE = "desc";

	UrlUtils.urlMap = urlMap.map;
	
	// Ariadne's thread to keep track of all visited URLs
	UrlUtils.ariadneThread = new Array();
	
	UrlUtils.addToAriadneThread = function(path) {
		UrlUtils.ariadneThread.push(path);
	};
	
	// return the previous URL
	UrlUtils.getPreviousLocation = function() {
		return UrlUtils.ariadneThread[UrlUtils.ariadneThread.length - 2];
	};
	
	UrlUtils.getUrl = function(name) {
		return UrlUtils.urlMap[name];
	};
	
	// defines categories for links, it is used to make an onglet selected, even if the user is on different pages
	UrlUtils.mappingTypeWithPathKey = {
		"USERS": [ "URL_USERS_LIST", "URL_USERS_ADD", "URL_USERS_EDIT" ]
	};
	
	// tests if the path is of the type
	UrlUtils.isPathOfType = function(type, path) {
		
		/**
		 * We can not apply the same process to the home because its URL is /
		 * It would match every URL (/users/XX for example)
		 */
		if (path == UrlUtils.urlMap["URL_HOME"]) {
			if (type == "HOME") {
				return true;
			}
			return false;
		}
		
		// standard process
		var pathKeys = UrlUtils.mappingTypeWithPathKey[type];
		if (!pathKeys) {
			return false;
		}
		
		for (var indexPathKey in pathKeys) {
			if (path.indexOf(UrlUtils.urlMap[pathKeys[indexPathKey]]) != -1) {
				return true;
			}
		}
		return false;
	};
	
	/**
	 * Construct an URL based on the diffrent parameters
	 * Useful to generate URLs on href
	 */
	UrlUtils.buildURL = function (baseUrl, parameters) {
		var url = baseUrl;
		if (parameters) {
			url = url + "?";
			var first = true;
			for (var index in parameters) {
				var parameter = parameters[index];
				if (!first) {
					url = url + "&";
				}
				url = url + parameter.name + "=";
				if (parameter.value != null) {
					url = url + parameter.value;
				}
				first = false;
			}
		}
		return url;
	};
	
	
	// Shortcut for list pages
	UrlUtils.buildListParameters = function (textSearched, firstResult, maxResults, orderBy, order) {
		return [
		        { name: "textSearched", value: textSearched},
		        { name: "firstResult", value: firstResult},
		        { name: "maxResults", value: maxResults},
		        { name: "orderBy", value: orderBy},
		        { name: "order", value: order}
		        ];
	};
	
	// Generic method to redirect the user
	UrlUtils.redirect = function ($location, baseUrl, parameters) {
		if (baseUrl) {
			$location.path(baseUrl);
		}
		var parameterObject = new Object();
		if (parameters) {
			for (var index in parameters) {
				var parameter = parameters[index];
				parameterObject[parameter.name] = parameter.value;
			}
		}
		$location.search(parameterObject);
	};
	
	// Crate an ID URL (/personnes/123)
	UrlUtils.buildIdURL = function (baseUrl, id) {
		return baseUrl + "/" + id;
	};
	
	return UrlUtils;
});