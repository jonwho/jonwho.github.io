'use strict';


// Declare app level module which depends on filters, and services
angular
	.module('jonwho', [
		'ngResource',
		'ui.router',
		'jonwho.controllers'
	]).
	config(function($stateProvider, $urlRouterProvider) {

		$urlRouterProvider.otherwise('/home');

		$stateProvider
			// home states and nested views
			.state('home', {
				url: '/home',
				templateUrl: 'views/home.html'
			})
			.state('home.welcome', {
				url: '/welcome',
				templateUrl: 'views/partials/home-welcome.html'
			})
			.state('home.tritonjuice', {
				url: '/tritonjuice',
				templateUrl: 'runnables/TritonJuice/TritonJuice.html'
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