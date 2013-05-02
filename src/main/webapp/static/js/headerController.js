mainApp.controller("headerController", function($scope, $location, UrlUtils, loginRest, authUtils) {
	$scope.logOut = function() {
		loginRest.logOut(
			function() {
				authUtils.logOut();
				UrlUtils.redirect($location, UrlUtils.getUrl("URL_LOGIN"));
			},
			function(returnObject) {
				ExceptionUtils.processRestError($scope, $location, UrlUtils, returnObject);
			}
		);
	};
});