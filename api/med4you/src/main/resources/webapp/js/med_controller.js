'use strict'

var app = angular.module('medicine', []);

app.controller('medicinecontroller', function ($scope, $http) {


    $scope.listarCategorias = function () {
        $http({
            method: 'GET',
            url: '/med4you/medicine'
        }).then(function (response){
            console.log(response);
            $scope.medicines = response.data;
        },function (error){
            console.log(error);
        });
    };


    $scope.listarCategorias();

});