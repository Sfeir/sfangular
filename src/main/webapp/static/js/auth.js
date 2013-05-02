var authModule = angular.module("authModule", []);

authModule.factory("authUtils", function() {
	
	var AuthUtils = function() {};

	AuthUtils.prototype.user = null;
	
	AuthUtils.prototype.logIn = function(login) {
		this.user = { login: login };
	};
	
	AuthUtils.prototype.isLogged = function() {
		return this.user != null;
	};
	
	AuthUtils.prototype.logOut = function() {
		delete this.user;
	};
	
	var instance = new AuthUtils();
	
	return instance;
});

var AuthUtilsResolver = function() {};

/**
 * We use a resolver in order to avoid an authentication control in every controller
 */
AuthUtilsResolver.resolve = {
	authUtilsInstance: function($q, loginRest, ExceptionUtils, authUtils) {
		var deferred = $q.defer();
		if (authUtils) {
			if (authUtils.isLogged()) {
				deferred.resolve(authUtils);
			} else {
				// rest call to get the authentication object (browser refresh for example)
				loginRest.getAuthentication(
					function(resultObject) {
						authUtils.logIn(resultObject.login);
						deferred.resolve(authUtils);
					},
					function() {
						deferred.reject(
							ExceptionUtils.createException(
								ExceptionUtils.EXCEPTION_CODES.USER_NOT_LOGGED,
								"User not logged in",
								"authUtils is not logged in"));
					}
				);
			}
		} else {
			// this case should not happen, we never know ...
			deferred.reject(
				ExceptionUtils.createException(
					ExceptionUtils.EXCEPTION_CODES.USER_NOT_LOGGED,
					"User not logged in",
					"authUtils is null"));
		}
		return deferred.promise;
	}	
};