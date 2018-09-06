'use strict'

var app = angular.module('reminder', []);

app.controller('remindercontroller', function ($scope, $http) {
    $scope.reminders =[];
    $scope.getReminders = function () {
        $http({
            method: 'GET',
            url: '/med4you/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
            $http({
                method: 'GET',
                url: '/med4you/reminders/findByUserId/'+ $scope.user.id
            }).then(function (response) {
                $scope.reminders = response.data;
            });
        });

    };

    $scope.add = function(){
        $scope.reminder.user = $scope.user;
        $http({
            method: 'POST',
            url: '/med4you/reminders',
            data: $scope.reminder
        }).then(function (response) {
            $scope.getReminders();
        }, function (error) {
            console.log(error);
        });
    }

    $scope.getReminders();

    $scope.cancel = function () {
        $scope.getReminders();
    };


});