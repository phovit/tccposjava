'use strict'

var app = angular.module('reminder', []);

app.controller('remindercontroller', function ($scope, $http) {
    $scope.reminders = [];
    $scope.showAddForm = false;

    $scope.getReminders = function () {
        $http({
            method: 'GET',
            url: '/med4you/users/logged'
        }).then(function (response) {
            $scope.user = response.data;
            $http({
                method: 'GET',
                url: '/med4you/reminders/findByUserId/' + $scope.user.id
            }).then(function (response) {
                $scope.reminders = response.data;
            });
        });

    };

    $scope.delete = function (id) {
        $http({
            method: 'DELETE',
            url: '/med4you/reminders/' + id
        }).then(function (response) {
            $scope.reminders = response.data;
        });

    };


    function saveAllReminders(reminder) {
        var qtdDoses =  parseInt($scope.qtdDoses);

        for (var i = 0; i < qtdDoses; i++) {
            reminder.firstDose = angular.copy(addHours(reminder.firstDose, $scope.intervalo));
            reminder.onLoop = true;
            $scope.save(angular.copy(reminder));
        }
    }

    function addHours(data, incremento) {
        return new Date(new Date(data).getTime() + 60 * 60 * incremento * 1000);
    }

    $scope.save = function (reminder) {
        reminder.user = $scope.user;
        var method = '';

        if (!$scope.isEditing) {
            method = 'POST';
        } else {
            method = 'PUT';
        }

        if (!!$scope.qtdDoses && $scope.qtdDoses > 1 && !!$scope.intervalo && !reminder.onLoop && method == 'POST') {
            saveAllReminders( reminder );
            return;
        }else{
            console.log(reminder.firstDose);
            $http({
                method: method,
                url: '/med4you/reminders',
                data: reminder
            }).then(function (response) {
                $scope.getReminders();
                $scope.clean();
                $scope.showAddForm = false;
                $scope.isEditing = false;
            }, function (error) {
                console.log(error);
            });
        }
    }
    $scope.edit = function (reminder) {
        $scope.isEditing = true;
        $scope.reminder = angular.copy(reminder);
        $scope.medicineSearch = angular.copy(reminder.medicine.name);
    }

    $scope.getReminders();

    $scope.cancel = function () {
        $scope.getReminders();
        $scope.isEditing = false;
    };
    $scope.clean = function () {
        $scope.reminder = {};
        $scope.medicineSearch = "";
    }

    $scope.findMedicines = function () {
        if ($scope.medicineSearch.length >= 3) {
            $http({
                method: 'GET',
                url: '/med4you/medicine/findByName/' + $scope.medicineSearch
            }).then(function (response) {
                $scope.medicineSearchResults = response.data;
            }, function (error) {
                console.log(error);
            });
            $scope.showMedResults = true;
        }
    }

    $scope.selectMedicine = function (med) {
        console.log("OnClick()");
        if (!$scope.reminder) {
            $scope.reminder = {};
        }
        $scope.medicineSearch = med.name;
        $scope.reminder.medicine = med;
        $scope.isMedSelected = true;
        $scope.showMedResults = false;
    }
});