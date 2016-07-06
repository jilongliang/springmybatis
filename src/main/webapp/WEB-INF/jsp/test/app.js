'use strict';

var flong = {};

var App = angular.module('flong', ['flong.filters', 'flong.services', 'flong.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/users', {
        templateUrl: 'test/layout',
        controller: UserController
    });

    $routeProvider.otherwise({redirectTo: '/users'});
}]);
