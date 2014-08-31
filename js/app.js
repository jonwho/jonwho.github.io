'use strict';


// Declare app level module which depends on filters, and services
angular.module('jonwho', [
	'ngRoute',
	'jonwho.controllers'
]).
config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'index.html'});
	$routeProvider.when('/about', {templateUrl: 'views/about.html'});
	$routeProvider.when('/contact', {templateUrl: 'views/contact.html'});
	$routeProvider.when('/calendar', {templateUrl: 'views/calendar.html'});
	$routeProvider.when('/resume', {templateUrl: 'views/resume.html'});
	$routeProvider.otherwise({redirectTo: '/index.html'});
}]);