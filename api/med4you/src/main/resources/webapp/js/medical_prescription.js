'use strict'

var app = angular.module('medicalprescription', []);

app.controller('medicalprescriptioncontroller', function ($scope, $http) {
    $scope.medicalPrescriptions = [];
    $scope.showAddForm = false;

    $scope.getmedicalPrescriptions = function () {
        $http({
            method: 'GET',
            url: '/med4you/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
            $http({
                method: 'GET',
                url: '/med4you/medicalPrescriptions/findByUserId/' + $scope.user.id
            }).then(function (response) {
                $scope.medicalPrescriptions = response.data;
            });
        });

    };

    $scope.delete = function (id) {
        $http({
            method: 'DELETE',
            url: '/med4you/medicalPrescriptions/' + id
        }).then(function (response) {
            $scope.medicalPrescriptions = response.data;
        });

    };


    function saveAllmedicalPrescriptions(medicalPrescription) {
        var qtdDoses =  parseInt($scope.qtdDoses);

        for (var i = 0; i < qtdDoses; i++) {
            medicalPrescription.firstDose = angular.copy(addHours(medicalPrescription.firstDose, $scope.intervalo));
            medicalPrescription.onLoop = true;
            $scope.save(angular.copy(medicalPrescription));
        }
    }

    function addHours(data, incremento) {
        return new Date(new Date(data).getTime() + 60 * 60 * incremento * 1000);
    }

    $scope.save = function (medicalPrescription) {
        medicalPrescription.user = $scope.user;
        var method = '';

        if (!$scope.isEditing) {
            method = 'POST';
        } else {
            method = 'PUT';
        }

        if (!!$scope.qtdDoses && $scope.qtdDoses > 1 && !!$scope.intervalo && !medicalPrescription.onLoop && method == 'POST') {
            saveAllmedicalPrescriptions( medicalPrescription );
            return;
        }else{
            console.log(medicalPrescription.firstDose);
            $http({
                method: method,
                url: '/med4you/medicalPrescriptions',
                data: medicalPrescription
            }).then(function (response) {
                $scope.getmedicalPrescriptions();
                $scope.clean();
                $scope.showAddForm = false;
                $scope.isEditing = false;
            }, function (error) {
                console.log(error);
            });
        }
    }
    $scope.edit = function (medicalPrescription) {
        $scope.isEditing = true;
        $scope.medicalPrescription = angular.copy(medicalPrescription);
        $scope.medicineSearch = angular.copy(medicalPrescription.medicine.name);
    }

    $scope.getmedicalPrescriptions();

    $scope.cancel = function () {
        $scope.getmedicalPrescriptions();
        $scope.isEditing = false;
    };
    $scope.clean = function () {
        $scope.medicalPrescription = {};
        $scope.medicineSearch = "";
    }

    $scope.findDoctors = function () {
        if ($scope.medicineSearch.length >= 3) {
            $http({
                method: 'GET',
                url: '/med4you/medicine/findByName/' + $scope.medicineSearch
            }).then(function (response) {
                $scope.doctorSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
        }
    }

    $scope.selectMedicine = function (med) {
        console.log("OnClick()");
        if (!$scope.medicalPrescription) {
            $scope.medicalPrescription = {};
        }
        $scope.medicineSearch = med.name;
        $scope.medicalPrescription.medicine = med;
        $scope.isMedSelected = true;
        $scope.showMedResults = false;
    }
});