
var app = angular.module('todo', ['ngRoute', 'ngFx', 'ngAnimate', 'angular-underscore']);

app.config(['$routeProvider', function($routeProvider) {

    var routeConfig = {
        controller: 'TodoController',
        templateUrl : '/'
    };

    $routeProvider
        .when('/', routeConfig)
        .otherwise({
            redirectTo: '/'
        });

}]);
