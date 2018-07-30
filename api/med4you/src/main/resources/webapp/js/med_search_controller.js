'use strict'

var app = angular.module('medicinesearch', []);

app.controller('medicinesearchcontroller', function ($scope, $http) {

        $scope.clear = function(){
            $scope.medicine = null;
        }
        $scope.findByName = function(){
            $http({
                method: 'GET',
                url: '/med4you/medicine/findByName/' + $scope.medicine.name
            }).then(function (response){
                $scope.medicines = response.data;
            },function (error){
                console.log(error);
            });

        }

});