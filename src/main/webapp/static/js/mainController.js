// main module with its dependencies
var mainApp = angular.module("mainApp", ["$strap", "urlModule", "exceptionModule", "authModule", "rightsRestModule", "loginRestModule", "usersRestModule"]);

// We use a provider to inject the url map in modules (url module)
mainApp.provider("urlMap", function() {
	var mapVar = null;
	return {
		setMap: function(map) {
			mapVar = map;
		},
		$get: function() {
			return {
				map: mapVar
			};
		}
	};
});

// configuration of the module
mainApp.config(function($routeProvider, urlMapProvider) {
	var urlMap = getUrlMap();
	urlMapProvider.setMap(urlMap);
	
	/**
	 * Route provider : mapping from an url to a template, its controller and its objects to load
	 * Resolve is used to get objects before displaying the page (no loading time)
	 */
	var routeProvider = $routeProvider.when(
		urlMap["URL_HOME"],
		{
			controller: AccueilController,
			templateUrl: "static/pages/home/home.html",
			resolve: { authUtils: AuthUtilsResolver.resolve.authUtilsInstance }
		}
	);
	routeProvider.when(
		urlMap["URL_LOGIN"],
		{
			controller: LoginController,
			templateUrl: "static/pages/login/login.html"
		}
	);
	routeProvider.when(
		urlMap["URL_USERS_LIST"],
		{
			controller: UsersListController,
			templateUrl: "static/pages/users/list.html",
			resolve: {
				authUtils: AuthUtilsResolver.resolve.authUtilsInstance,
				users: UsersListController.resolve.users
			}
		}
	);
	routeProvider.when(
		urlMap["URL_USERS_ADD"],
		{
			controller: UsersAddController,
			templateUrl: "static/pages/users/addEdit.html",
			resolve: {
				authUtils: AuthUtilsResolver.resolve.authUtilsInstance,
				rights: UsersAddEditController.resolve.rights
			}
		}
	);
	routeProvider.when(
		urlMap["URL_USERS_EDIT"] + "/:id",
		{
			controller: UsersEditController,
			templateUrl: "static/pages/users/addEdit.html",
			resolve: {
				authUtils: AuthUtilsResolver.resolve.authUtilsInstance,
				user: UsersAddEditController.resolve.user,
				rights: UsersAddEditController.resolve.rights,
			}
		}
	);
	routeProvider.otherwise({redirectTo: "/"});
});

// main controller redirects from a view to another view
mainApp.controller("mainController", function($rootScope, $scope, $location, ExceptionUtils, UrlUtils) {

	$rootScope.$on("$routeChangeStart", function (event, next, current) {
        $scope.loading = false;
        $scope.setError(false, "");
    });
    $rootScope.$on("$routeChangeSuccess", function (event, current, previous) {
        $scope.loading = false;
        $scope.setError(false, "");

        // Ariadne's thread stores visited pages
        UrlUtils.addToAriadneThread($location.url());
        $scope.newLocation = $location.path();
    });
    
    // this event is mainly fired when there is a rest error
    $rootScope.$on("$routeChangeError", function (event, current, previous, rejection) {
        $scope.loading = false;
    	ExceptionUtils.processException($scope, $location, UrlUtils, rejection);
    });
    
    // displays the error on the screen
    $scope.setError = function(error, title, text) {
    	$scope.errorDisplay = error;
    	$scope.errorTitle = title;
    	$scope.errorText = text;
    };

    // define the onglet class depending on location
	$scope.getClassOnglet = function(pathType) {
		if (UrlUtils.isPathOfType(pathType, $location.path())) {
			return "active";
		}
		return "";
	};
	$scope.UrlUtils = UrlUtils;
	
});

mainApp.directive("orderbyurl", function(UrlUtils) {
	return {
		restrict: "E",
		link: function(scope, element, attributes) {
			
			var newElement = "<a href=\"#";
			if (scope.orderBy == attributes.orderby && scope.order == UrlUtils.URL_ASC_VALUE) {
				newElement = newElement + UrlUtils.buildURL(scope.baseUrl, UrlUtils.buildListParameters(scope.textSearched, scope.firstResult, scope.maxResults, attributes.orderby, UrlUtils.URL_DESC_VALUE));
			} else {
				newElement = newElement + UrlUtils.buildURL(scope.baseUrl, UrlUtils.buildListParameters(scope.textSearched, scope.firstResult, scope.maxResults, attributes.orderby, UrlUtils.URL_ASC_VALUE));
			}
			newElement = newElement + "\">" + attributes.label + "</a>";

			var icon = "";
			if (scope.orderBy == attributes.orderby) {
				if (scope.order == UrlUtils.URL_ASC_VALUE) {
					icon = "<i class=\"icon-arrow-down\" />";
				} else {
					icon = "<i class=\"icon-arrow-up\" />";
				}
			}
			
			element.replaceWith(newElement + icon);
		}
	};
});

// configuration of angular strap
mainApp.value("$strap.config", {
	datepicker: {
		language: "fr"
	}
});

var FLOAT_REGEXP = /^\-?\d+((\.|\,)\d+)?$/;
mainApp.directive('smartFloat', function() {
  return {
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      ctrl.$parsers.unshift(function(viewValue) {
        if (FLOAT_REGEXP.test(viewValue)) {
          ctrl.$setValidity('float', true);
          return parseFloat(viewValue.replace(',', '.'));
        } else {
          ctrl.$setValidity('float', false);
          return undefined;
        }
      });
    }
  };
});