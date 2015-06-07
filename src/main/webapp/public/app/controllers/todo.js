angular.module('todo')
        .controller('TodoController', function ($scope, $rootScope, $http, $filter, $timeout, todoService) {

    $scope.newTasks = [];

    $scope.selectedPriority = "MEDIUM";

    var init = function() {

        todoService.connect('/rabbit-todo/io').then(function() {

            todoService.subscribe('/topic/todos/created', function(todo) {

                $scope.todos.push(angular.fromJson(todo));

            });

            todoService.subscribe('/topic/todos/deleted', function(id) {
                $scope.todos = $filter('filter')($scope.todos, function(todo, i) {return todo.id !== id;});
            });

            todoService.subscribe('/topic/todos/tasks/done', function(data) {
                var t = angular.fromJson(data);
                var todo = $scope.findWhere($scope.todos , {id : t.todoId });
                if (todo) {
                    var task = $scope.findWhere(todo.tasks, {id : t.taskId});
                    if (task) {
                        task.done = true;
                    }
                }
            });

            todoService.subscribe('/topic/todos/tasks/priority/changed', function(data) {
                var t = angular.fromJson(data);
                var todo = $scope.findWhere($scope.todos , {id : t.todoId });
                if (todo) {
                    var task = $scope.findWhere(todo.tasks, {id : t.taskId});
                    if (task) {
                        task.priority = t.priority;
                    }
                }
            });


        });

        todoService.list().then(function(todos) {
            $scope.todos = todos;

        });
    };

    $scope.createTodo = function(name, description) {
        todoService.create(name, description, $scope.newTasks);
    }

    $scope.deleteTodo = function(id) {
        todoService.delete(id);
    }

    $scope.addNewTask = function(task) {
        $scope.newTasks.push({title : task});
    };

    $scope.removeNewTask = function(index) {
        $scope.newTasks.splice(index, 1);
    };

    $scope.setTaskDone = function(todoId, taskId) {
        todoService.setDone(todoId, taskId);
    };

    $scope.changeTaskPriority = function(todoId, taskId, priority) {
        todoService.changePriority(todoId, taskId, priority);
    }

    init();

});


