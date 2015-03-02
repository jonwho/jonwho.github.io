'use strict';

/* Controllers */

angular.module('jonwho.controllers', [])
  .controller('HomeCtrl', ['$scope', function($scope) {
  	$scope.msg = 'home';
  }])
  .controller('CarouselCtrl', ['$scope', '$animate', '$timeout', function($scope, $animate, $timeout) {
  	$scope.interval = 5000;
  	var slides = $scope.slides = [];

  	// add intern game
  	slides.push({
  		image: '../res/projects/scotttheintern/banner.png',
  		text: 'A little video game written in C#'
  	});

  	// add ubi comp project
  	slides.push({
  		image: '../res/projects/speedydragons/speedy.jpg',
  		text: 'Ubiquitous Computing project'
  	});

  	// add augmonted
  	slides.push({
  		image: '../res/projects/augmonted/augmonted.jpg',
  		text: 'Augmented Reality video game for Android'
  	});

  	// need this to fix conflict issue on ui.bootstrap vs ngAnimate
  	$timeout(function() {
  		$animate.enabled(false, angular.element(".carousel"));
  	})
  }])
  .controller('MyCtrl2', ['$scope', function($scope) {
  	$scope.msg = 'nother ctrl';
  }]);