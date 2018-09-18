var app = angular.module('app', ['ui.router', 'app.controllers']);

app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/home");

    $stateProvider
        .state('medicine', {
            url: "/medicine",
            templateUrl: "screens/medicine.html",
            controller: "medicinecontroller"
        })
        .state('medicineregister', {
            url: "/medicine_register",
            templateUrl: "screens/medicine_register.html",
            controller: "medicineregistercontroller"
        })
        .state('medicineedit', {
            url: "/medicine/:id",
            templateUrl: "screens/medicine_edit.html",
            controller: "medicineeditcontroller"
        })
        .state('medicinesearch', {
            url: "/medicine_search",
            templateUrl: "screens/medicine_search.html",
            controller: "medicinesearchcontroller"
        })
        .state('reminder', {
            url: "/reminder",
            templateUrl: "screens/reminder.html",
            controller: "remindercontroller"
        })
        .state('user', {
            url: "/user",
            templateUrl: "screens/user.html",
            controller: "usercontroller"
        })
        .state('medicalprescription', {
            url: "/medicalprescription",
            templateUrl: "screens/medical_prescription.html",
            controller: "medicalprescriptioncontroller"
        });
});


app.controller('indexcontroller', function ($scope, $http) {

    $scope.modal = {};
    $scope.isLogged = false;

    $scope.loginOptions = function () {
        $scope.modal.url = "screens/partials/login.html";
    };

    $scope.getIsLogged = function(){
        $http({
            method: 'GET',
            url: '/med4you/users/isLogged'
        }).then(function (response) {
            $scope.isLogged = response.data;
        });
    };
    $scope.infoUser = function(){
        $http({
            method: 'GET',
            url: '/med4you/users/logged'
        }).then(function (response) {
            $scope.loggedUser = response.data;
        });
    };


    $scope.getIsLogged();
    $scope.infoUser();


    if(location.search.slice(1)==="login=true" &&  !$scope.isLogged ){
        $scope.loginOptions();
    }

});