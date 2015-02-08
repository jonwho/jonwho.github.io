'use strict';


// Declare app level module which depends on filters, and services
angular
	.module('jonwho', [
		'ngResource',
		'ui.router',
		'ngAnimate',
		'ngAria',
		'ngMaterial',
		'jonwho.controllers', // technically another Angular app
		'twentyfourtyeightApp' // nesting Angular apps
	])
	.config(function($stateProvider, $urlRouterProvider) {

		$urlRouterProvider.otherwise('/home');

		$stateProvider
			// home states and nested views
			.state('home', {
				url: '/home',
				templateUrl: 'views/home.html'
			})
				// nested views
				.state('home.welcome', {
					url: '/welcome',
					templateUrl: 'views/partials/home-welcome.html'
				})
				.state('home.ng2048', {
					url: '/2048',
					templateUrl: 'runnables/2048-in-AngularJS/ng2048.html'
				})
				.state('home.tritonjuice', {
					url: '/tritonjuice',
					templateUrl: 'runnables/TritonJuice/TritonJuice.html'
				})
			.state('about', {
				url: '/about',
				templateUrl: 'views/about.html'
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
