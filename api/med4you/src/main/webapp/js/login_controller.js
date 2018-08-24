'use strict'

var app = angular.module('login', []);

app.controller('logincontroller', function ($scope, $http) {


    $scope.autenticate = function () {
        $http({
            method: 'POST',
            url: '/med4you/autenticate',
            data: $scope.user
        }).then(function (response) {
            $scope.session = response.data;
            $scope.isLogged = true;
        }, function (error) {
            console.log(error);
            $scope.isLogged = true;
        });
    };





});