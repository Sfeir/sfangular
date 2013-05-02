/**
 * This method is common to addition and edition
 * This is not a proper controller !
 */
function UsersAddEditController($scope, $location, ExceptionUtils, UrlUtils, usersRest, add, user, rights) {
	
	$scope.add = add;
	if (add) {
		$scope.user = new Object();
		$scope.user.rights = new Array();
	} else {
		$scope.user = user;
	}
	
	makeScopeLists();
	
	$scope.save = function() {
		usersRest.save(
			$scope.user,
			function(resultObject) {
				UrlUtils.redirect($location, UrlUtils.getUrl("URL_USERS_LIST"));
			},
			function(returnObject) {
				ExceptionUtils.processRestError($scope, $location, UrlUtils, returnObject);
			}
		);
	};
	
	$scope.assignRight = function() {
		$scope.user.rights.push($scope.unassignedRightId[0]);
		makeScopeLists();
	};
	
	$scope.unassignRight = function() {
		var assignRightId = $scope.assignedRightId[0];
		
		var newRights = new Array();
		for (var indexRight in $scope.user.rights) {
			var rightId = $scope.user.rights[indexRight];
			if (assignRightId != rightId) {
				newRights.push(rightId);
			}
		}
		$scope.user.rights = newRights;
		makeScopeLists();
	};
	
	/**
	 * Used to build the 2 display lists :
	 * These are made by comparing all database rights with user rights
	 */
	function makeScopeLists() {
		
		$scope.assignedRightId = null;
		$scope.unassignedRightId = null;
		
		$scope.assignedRights = new Array();
		$scope.unassignedRights = new Array();
		
		for (var indexRight in rights) {
			var right = rights[indexRight];
			if ($.inArray(right.id, $scope.user.rights) != -1) {
				$scope.assignedRights.push(right);
			} else {
				$scope.unassignedRights.push(right);
			}
		}
	}
}

//This method is the real controller
function UsersEditController($scope, $location, ExceptionUtils, UrlUtils, usersRest, user, rights) {
	UsersAddEditController($scope, $location, ExceptionUtils, UrlUtils, usersRest, false, user, rights);
}

//This method is the real controller
function UsersAddController($scope, $location, ExceptionUtils, UrlUtils, usersRest, rights) {
	UsersAddEditController($scope, $location, ExceptionUtils, UrlUtils, usersRest, true, null, rights);
}

UsersAddEditController.resolve = {
	user: function($q, $route, usersRest, ExceptionUtils) {
		var deferred = $q.defer();
		
		var afterCallFunction = function(resultObject) {
			deferred.resolve(resultObject);
		};
		
		var errorFunction = function(resultObject) {
			deferred.reject(ExceptionUtils.restErrorToException(resultObject));
		};
		
		usersRest.get($route.current.params.id, afterCallFunction, errorFunction);
	
		return deferred.promise;
	},
	rights: function($q, rightsRest, ExceptionUtils) {
		
		var deferred = $q.defer();
		
		var afterCallFunction = function(resultObject) {
			deferred.resolve(resultObject);
		};
		
		var errorFunction = function(resultObject) {
			deferred.reject(ExceptionUtils.restErrorToException(resultObject));
		};
	
		rightsRest.listAll(afterCallFunction, errorFunction);
	
		return deferred.promise;
	}
};