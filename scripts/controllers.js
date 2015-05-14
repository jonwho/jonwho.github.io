'use strict';

angular
  .module('jonwho')
  .controller('NavCtrl', ['$scope', '$mdSidenav', function($scope, $mdSidenav) {
    $scope.users = [
      {
        name: 'Jon Ho',
        avatar: '../bower_components/ionicons/src/person.svg',
        templateUrl: '../views/me.html'
      },
      {
        name: 'Experience',
        avatar: '../bower_components/ionicons/src/briefcase.svg',
        templateUrl: '../views/experience.html'
      },
      {
        name: 'Skills',
        avatar: '../bower_components/ionicons/src/settings.svg',
        templateUrl: '../views/skills.html'
      },
      {
        name: 'Projects',
        avatar: '../bower_components/ionicons/src/code.svg',
        templateUrl: '../views/projects.html'
      },
      {
        name: 'Contact',
        avatar: '../bower_components/ionicons/src/person-add.svg',
        templateUrl: '../views/contact.html'
      }
    ];

    $scope.selected = $scope.users[0];

    $scope.selectUser = function(user) {
      $scope.selected = angular.isNumber(user) ? $scope.users[user] : user;
      $scope.toggleList();
    };

    $scope.toggleList = function() {
      $mdSidenav('left').toggle();
    };
  }]);