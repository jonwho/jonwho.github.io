'use strict';


// Declare app level module which depends on filters, and services
angular
	.module('jonwho', [
		'ngRoute',
		'ngResource',
		'ui.router',
		'jonwho.controllers'
	]).
	// config(['$routeProvider', function($routeProvider) {
	// 	$routeProvider
	// 		.when('/', {
	// 			templateUrl: 'views/home.html'
	// 		})
	// 		.when('/home', {
	// 			templateUrl: 'views/home.html'
	// 		})
	// 		.when('/about', {
	// 			templateUrl: 'views/about.html'
	// 		})
	// 		.when('/contact', {
	// 			templateUrl: 'views/contact.html'
	// 		})
	// 		.when('/calendar', {
	// 			templateUrl: 'views/calendar.html'
	// 		})
	// 		.when('/resume', {
	// 			templateUrl: 'views/resume.html'
	// 		})
	// 		.otherwise({
	// 			redirectTo: '/'
	// 		});
	// }]);

	// use ui-router instead
	config(function($stateProvider, $urlRouterProvider) {

		$urlRouterProvider.otherwise('/home');

		$stateProvider
			// home states and nested views
			.state('home', {
				url: '/home',
				templateUrl: 'views/home.html'
			})
			.state('about', {
				url: '/about',
				templateUrl: 'views/about.html'
			})
			.state('contact', {
				url: '/contact',
				templateUrl: 'views/contact.html'
			})
			.state('calendar', {
				url: '/calendar',
				templateUrl: 'views/calendar.html'
			})
			.state('resume', {
				url: '/resume',
				templateUrl: 'views/resume.html'
			});
	});