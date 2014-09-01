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
				templateUrl: 'views/home.html',
				controller: 'MyCtrl1'
			})
			.when('/home', {
				templateUrl: 'views/home.html',
				controller: 'MyCtrl1'
			})
			.when('/about', {
				templateUrl: 'views/about.html',
				controller: 'MyCtrl1'
			})
			.when('/contact', {
				templateUrl: 'views/contact.html',
				controller: 'MyCtrl1'
			})
			.when('/calendar', {
				templateUrl: 'views/calendar.html',
				controller: 'MyCtrl1'
			})
			.when('/resume', {
				templateUrl: 'views/resume.html',
				controller: 'MyCtrl1'
			})
			.otherwise({
				redirectTo: '/'
			});
	}]);