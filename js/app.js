'use strict';


// Declare app level module which depends on filters, and services
angular
	.module('jonwho', [
		'ngRoute',
		'jonwho.controllers'
	]).
	config(['$routeProvider', function($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl: 'views/home.html'
			})
			.when('/home', {
				templateUrl: 'views/home.html'
			})
			.when('/about', {
				templateUrl: 'views/about.html'
			})
			.when('/contact', {
				templateUrl: 'views/contact.html'
			})
			.when('/calendar', {
				templateUrl: 'views/calendar.html'
			})
			.when('/resume', {
				templateUrl: 'views/resume.html'//,
				//controller: 'MyCtrl1'
			})
			.otherwise({
				redirectTo: '/'
			});
	}]);