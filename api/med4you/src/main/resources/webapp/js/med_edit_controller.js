'use strict'

var app = angular.module('medicineedit', []);

app.controller('medicineeditcontroller', function ($scope, $http, $state, $stateParams) {


    $scope.find = function (medicineId) {
        $http({
            method: 'GET',
            url: '/med4you/medicine/' + medicineId
        }).then(function (response) {
            $scope.medicine = response.data;
        }, function (error) {
            alert("Medicamento não encontrado com o ID: " + medicineId);
            $state.go("medicineregister");
        });

    }

    $scope.find($stateParams.id);

    $scope.clear = function () {
        $scope.medicine = null;
    }
    $scope.save = function () {
        $http({
            method: 'POST',
            url: '/med4you/medicine',
            data: $scope.medicine
        }).then(function (response) {
            console.log(response);
            alert('Medicamento cadastrado com sucesso');
        }, function (error) {
            alert('Erro ao cadastrar medicamento');
        });

    }

});