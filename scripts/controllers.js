'use strict';

/* Controllers */

angular.module('jonwho.controllers', [])
  .controller('HomeCtrl', ['$scope', function($scope) {
  	$scope.msg = 'home';
  }])
  .controller('CarouselCtrl', ['$scope', '$animate', '$timeout', function($scope, $animate, $timeout) {
  	$scope.interval = 5000;
  	var slides = $scope.slides = [];

  	$scope.addSlide = function() {
  		var newWidth = 600 + slides.length + 1;
  		slides.push({
  			image: 'http://placekitten.com/' + newWidth + '/300',
  			text: ['More', 'Extra', 'Lots of', 'Surplus'][slides.length % 4] + ' ' +
  			  ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
  		});
  	};

  	for(var i = 0; i < 4; i++) {
  		$scope.addSlide();
  	}

  	// need this to fix conflict issue on ui.bootstrap vs ngAnimate
  	$timeout(function() {
  		$animate.enabled(false, angular.element(".carousel"));
  	})
  }])
  .controller('MyCtrl2', ['$scope', function($scope) {
  	$scope.msg = 'nother ctrl';
  }]);