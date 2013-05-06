function UsersListController($scope, $location, $routeParams, $rootScope, usersRest, users, UrlUtils, ExceptionUtils) {
	
	var baseUrl = UrlUtils.getUrl('URL_USERS_LIST');
	
	$scope.users = users.list;
	$scope.pagination = new Pagination(
			UrlUtils,
			baseUrl,
			users.textSearched,
			users.firstResult,
			users.maxResults,
			users.orderBy,
			users.order,
			users.count);

	$scope.baseUrl = baseUrl;
	$scope.textSearched = users.textSearched;
	$scope.firstResult = users.firstResult;
	$scope.maxResults = users.maxResults;
	$scope.orderBy = users.orderBy;
	$scope.order = users.order;

	$scope.UrlUtils = UrlUtils;
	
	$scope.textSearched = "";
	if ($routeParams.textSearched) {
		$scope.textSearched = $routeParams.textSearched;
	}
	
	$scope.search = function() {
		/**
		 * Redirection to the same page so the URL parameter is null
		 * The first result is reseted because the search text could have changed and therefore the result number could have changed as well
		 */
		UrlUtils.redirect($location, null, UrlUtils.buildListParameters($scope.textSearched, 0, $scope.maxResults, $scope.orderBy, $scope.order));
	};
	
	$scope.delet = function(id) {
		usersRest.delet(
			id,
			function() {
				// After deletion we load again the updated list
				usersRest.list(
					$scope.textSearched,
					$scope.firstResult,
					$scope.maxResults,
					$scope.orderBy,
					$scope.order,
					function(returnObject) {
						$scope.users = returnObject.list;
						$scope.pagination = new Pagination(
								UrlUtils,
								baseUrl,
								$scope.textSearched,
								$scope.firstResult,
								$scope.maxResults,
								$scope.orderBy,
								$scope.order,
								returnObject.count);
					},
					function(returnObject) {
						ExceptionUtils.processRestError($scope, $location, UrlUtils, returnObject);
					}
				);
			},
			function(returnObject) {
				ExceptionUtils.processRestError($scope, $location, UrlUtils, returnObject);
			}
		);
	};
	
	$scope.typeaheadChange = function(textSearched, callback) {
		var typeAheadDefaultParameters = usersRest.typeAheadDefaultParameters(UrlUtils, textSearched);
		usersRest.list(
			typeAheadDefaultParameters.textSearched,
			typeAheadDefaultParameters.firstResult,
			typeAheadDefaultParameters.maxResults,
			typeAheadDefaultParameters.orderBy,
			typeAheadDefaultParameters.order,
			function(returnObject) {
				typeAheadProcessing(returnObject, callback);
			},
			function(returnObject) {
				ExceptionUtils.processRestError($scope, $location, UrlUtils, returnObject);
			}
		);
	};
	
	function typeAheadProcessing(returnObject, callback) {
		var resultArray = new Array();
		var list = returnObject.list;
		for (var index in list) {
			var user = list[index];
			if (resultArray.indexOf(user.lastname) == -1) {
				resultArray.push(user.lastname);
			}
			if (resultArray.indexOf(user.firstname) == -1) {
				resultArray.push(user.firstname);
			}
		}
		callback(resultArray);
	}
}

UsersListController.resolve = {
	users: function($q, $route, usersRest, UrlUtils, ExceptionUtils) {
		
		var deferred = $q.defer();
		
		var routeParams = $route.current.params;
		
		var parameters = usersRest.listDefaultParameters(
				UrlUtils,
				routeParams.textSearched,
				parseInt(routeParams.firstResult),
				parseInt(routeParams.maxResults),
				routeParams.orderBy,
				routeParams.order);
		
		var afterCallFunction = function(resultObject) {
			resultObject.textSearched = parameters.textSearched;
			resultObject.firstResult = parameters.firstResult;
			resultObject.maxResults = parameters.maxResults;
			resultObject.orderBy = parameters.orderBy;
			resultObject.order = parameters.order;
			deferred.resolve(resultObject);
		};
		
		var errorFunction = function(resultObject) {
			deferred.reject(ExceptionUtils.restErrorToException(resultObject));
		};
	
		usersRest.list(parameters.textSearched, parameters.firstResult, parameters.maxResults, parameters.orderBy, parameters.order, afterCallFunction, errorFunction);
	
		return deferred.promise;
	}
};