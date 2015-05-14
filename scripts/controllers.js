'use strict';

angular
  .module('users')
  .controller('NavCtrl', [
             '$mdSidenav',
             NavCtrl
  ]);

function NavCtrl($mdSidenav) {
  var self = this;

  self.selected     = null;
  self.users        = [ ];
  self.selectUser   = selectUser;
  self.toggleList   = toggleUsersList;
  self.share        = share;

  // *********************************
  // Internal methods
  // *********************************

  /**
    * Select the current avatars
    * @param menuId
    */
  function selectUser ( user ) {
    self.selected = angular.isNumber(user) ? $scope.users[user] : user;
    self.toggleList();
  }

  function toggleUsersList() {
    $mdSidenav('left').toggle();
  }
}
