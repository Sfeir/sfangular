function LoginController($scope, $routeParams, $location, ExceptionUtils, UrlUtils, loginRest, authUtils) {
	
	$scope.invalidLogin = false;
	$scope.expired = $routeParams.expired != null;
	
	$scope.login = function() {
		loginRest.logIn(
			$scope.login.login,
			function(resultObject) {
				// Get the authentication object and then redirect to the home page
				authUtils.logIn(resultObject.login);
				UrlUtils.redirect($location, UrlUtils.getUrl("URL_HOME"));
			},
			function(returnObject) {
				var exception = ExceptionUtils.restErrorToException(returnObject);
				
				// Differentiation between a login failed and other exceptions (technical for instance)
				if (ExceptionUtils.isExceptionCode(exception, ExceptionUtils.EXCEPTION_CODES.INVALID_LOGIN)) {
					$scope.invalidLogin = true;
				} else {
			    	ExceptionUtils.processException($scope, $location, UrlUtils, exception);
				}
			}
		);
	};
}