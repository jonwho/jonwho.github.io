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

app.config(function($mdIconProvider) {
  $mdIconProvider
    .icon('github', '../bower_components/ionicons/src/social-octocat.svg', 24)
    .icon('linkedin', '../bower_components/ionicons/src/social-linkedin.svg', 24)
    .icon('twitter', '../bower_components/ionicons/src/social-twitter.svg', 24)
    .icon('email', '../bower_components/ionicons/src/email.svg', 24);
});

// TODO: Try and match to UCSD blue/yellow colors
// Theming Material Design
// app.config(function($mdThemingProvider) {
//   $mdThemingProvider.theme('default')
//     .primaryPalette('pink')
//     .accentPalette('orange');
// });