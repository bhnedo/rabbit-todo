angular.module('todo')
    .factory('todoService', function($http, $q, $rootScope) {
        'use strict';

        var API_ENDPOINT = 'api/v1/todos';

        var todoApi = {

            stomp : null,

            connect : function(endpoint) {
                var socket = new SockJS(endpoint);
                var defer = $q.defer();
                todoApi.stomp  = Stomp.over(socket);
                todoApi.stomp.connect({}, function(frame) {
                    defer.resolve();
                });
                return defer.promise;
            },

            subscribe : function(topic, callback) {
                todoApi.stomp.subscribe(topic, function(data) {
                    $rootScope.$apply(function() {
                        callback(data.body);
                    });
                });
            },

            list : function() {
               var promise = $http.get(API_ENDPOINT)
                    .then(function(res) {

                        return res.data;

                    });
               return promise;

            },

            create : function(name, description, tasks) {
                $http.post(API_ENDPOINT, { name : name,
                                         description : description,
                                         tasks: tasks});

            },

            delete : function(id) {
                $http.delete(API_ENDPOINT + '/' + id);
            },


            setDone : function(todoId, taskId) {
                $http.put(API_ENDPOINT + '/' + todoId + '/tasks/' + taskId);
            },

            changePriority : function(todoId, taskId, priority) {
                $http.put(API_ENDPOINT + '/' + todoId + '/tasks/' + taskId + '/priority/' + priority);
            }
        };

        return todoApi;

});
