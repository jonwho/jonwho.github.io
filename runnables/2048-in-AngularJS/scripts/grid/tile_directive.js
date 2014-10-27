'use strict';

angular.module('Grid')
.directive('tile', function() {
  return {
    restrict: 'A',
    scope: {
      ngModel: '='
    },
    templateUrl: 'runnables/2048-in-AngularJS/scripts/grid/tile.html'
  };
});