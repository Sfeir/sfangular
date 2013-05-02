var exceptionModule = angular.module("exceptionModule", []);

exceptionModule.factory("ExceptionUtils", function() {

	var ExceptionUtils = function() {};
	
	ExceptionUtils.EXCEPTION_CODES = {
		GENERIC_EXCEPTION: "GENERIC_EXCEPTION",
		USER_NOT_LOGGED: "USER_NOT_LOGGED",
		INVALID_LOGIN: "INVALID_LOGIN",
		UNAUTHORIZED: "UNAUTHORIZED"
	};
	
	ExceptionUtils.createException = function(code, message, additionalText) {
		return { code: code, message: message, additionalText: additionalText };
	};
	
	// convert a rest error (HTTP code different from 400) to an exception object
	ExceptionUtils.restErrorToException = function(errorData) {
		
		var code = ExceptionUtils.EXCEPTION_CODES.GENERIC_EXCEPTION;
		var message = "Exception";
		
		if (errorData.status == 401) {
			code = ExceptionUtils.EXCEPTION_CODES.UNAUTHORIZED;
			message = "User not authorized";
		} else if (errorData.status == 403) {
			code = ExceptionUtils.EXCEPTION_CODES.INVALID_LOGIN;
			message = "Login invalid";
		} else if (errorData.status == 511) {
			code = ExceptionUtils.EXCEPTION_CODES.USER_NOT_LOGGED;
			message = "User not logged in";
		}
		
		// we get the error string that we may display using JSON
		return ExceptionUtils.createException(code, message, JSON.stringify(errorData));
	};
	
	// standard process for exceptions
	ExceptionUtils.processException = function($scope, $location, UrlUtils, exception) {
		
		/**
		 * we differentiate not logged exception from technical exceptions
		 * if it is a not logged exception, user is redirected to the login page
		 * else it is an exception which sould not happen and we display it
		 */
        if (exception.code == ExceptionUtils.EXCEPTION_CODES.USER_NOT_LOGGED) {
        	UrlUtils.redirect($location, UrlUtils.getUrl("URL_LOGIN"), [ { name: "expired", value: true } ]);
        } else {
        	$scope.setError(true, exception.message + " (" + exception.code + ")", exception.additionalText);
        }
	};
	
	// Test the exception code
	ExceptionUtils.isExceptionCode = function(exception, code) {
		if (exception.code == code) {
			return true;
		}
		return false;
	};
	
	// Shortcut method for rest calls
	ExceptionUtils.processRestError = function($scope, $location, UrlUtils, errorData) {
		ExceptionUtils.processException($scope, $location, UrlUtils, ExceptionUtils.restErrorToException(errorData));
	};
	
	return ExceptionUtils;
});