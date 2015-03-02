'use strict';

/* Controllers */

angular.module('jonwho.controllers', [])
  .controller('HomeCtrl', ['$scope', function($scope) {
  	$scope.msg = 'home';
  }])
  .controller('CarouselCtrl', ['$scope', function($scope) {
  	
  }])
  .controller('MyCtrl2', ['$scope', function($scope) {
  	$scope.msg = 'nother ctrl';
  }]);