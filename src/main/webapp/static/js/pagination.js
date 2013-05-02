/**
 * Pagination object
 * @param active button can be clicked
 * @param number page index
 * @param url page URL
 * @param current user is on this page
 * @returns object
 */
var PaginationPage = function(active, number, url, current) {
	this.active = active;
	this.number = number;
	this.url = url;
	this.current = current;
};

// CSS class of the button
PaginationPage.prototype.getClass = function() {
	if (!this.active || this.current) {
		return "active";
	}
	return "";
};

// All parameters are computed to build the entire pagination
var Pagination = function(UrlUtils, baseUrl, textSearched, firstResult, maxResults, orderBy, order, count) {
	
	// if the current first result is greater than 0, it means that user is not on the 1st page : we can add a clickable button to go on the 1st
	if (firstResult > 0) {
		this.previousPage = new PaginationPage(
				true,
				(firstResult - maxResults) / maxResults + 1,
				UrlUtils.buildURL(baseUrl, UrlUtils.buildListParameters(textSearched, (firstResult - maxResults), maxResults, orderBy, order)),
				false);
	} else {
		this.previousPage = new PaginationPage(false, null, null, null);
	}
	
	// same kind of computation for the last page
	if (count > (firstResult + maxResults)) {
		this.nextPage = new PaginationPage(
				true,
				(firstResult + maxResults) / maxResults + 1,
				UrlUtils.buildURL(baseUrl, UrlUtils.buildListParameters(textSearched, (firstResult + maxResults), maxResults, orderBy, order)),
				false);
	} else {
		this.nextPage = new PaginationPage(false, null, null, null);
	}
	
	// the following code is used by the numbered buttons 1 ... X
	this.pages = new Array();
	
	var index = 0;
	while (index < count) {
		this.pages.push(
				new PaginationPage(
						true,
						index / maxResults + 1,
						UrlUtils.buildURL(baseUrl, UrlUtils.buildListParameters(textSearched, index, maxResults, orderBy, order)),
						index == firstResult)
		);
		index = index + maxResults;
	}
};
