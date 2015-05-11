'use strict';

// Dependencies
var app = angular
  .module('jonwho', [
    'ngAria',
    'ngMaterial',
    'ui.router',
    'ng-mfb'
]);

// Routing
app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
      .state('/', {
        url: '/',
        templateUrl: '../index.html'
      });
});
