
angular.module('todo')
    .directive('ngEnter', function ($parse) {
        'use strict';

        var ENTER_KEY = 13;

        return function (scope, elem, attrs) {
            elem.bind('keydown', function (event) {

                if (event.keyCode === ENTER_KEY) {
                    event.preventDefault();
                    scope.$apply(attrs.ngEnter);
                    elem.val("");
                }
            });

            scope.$on('$destroy', function () {
                elem.unbind('keydown');
            });
        };
    });