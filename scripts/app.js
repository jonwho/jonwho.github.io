'use strict';

// Dependencies
var app = angular
  .module('jonwho', [
    'ngAria',
    'ngMaterial',
    'ngAnimate',
    'ngTouch',
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

// TODO: Try and match to UCSD blue/yellow colors
// Theming Material Design
// app.config(function($mdThemingProvider) {
//   $mdThemingProvider.theme('default')
//     .primaryPalette('pink')
//     .accentPalette('orange');
// });