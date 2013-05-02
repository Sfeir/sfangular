/**
 * This class is only used to inject the first part of the REST URL adress
 */
var restConfigurationModule = angular.module("restConfigurationModule", []);

restConfigurationModule.constant("restUrl", "rest/");

restConfigurationModule.factory("RestConfiguration", function(restUrl) {

	var Rest = function() {};
	
	Rest.url = restUrl;

	return Rest;
});