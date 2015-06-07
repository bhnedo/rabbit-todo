
angular.module('todo')
    .directive('ngPopupClose', function () {
        'use strict';

        return function (scope, elem, attrs) {
            elem.bind('click', function () {
                $(".modal-state:checked").prop("checked", false).change();
            });
        };
    });