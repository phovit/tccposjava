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
        .state('user', {
            url: "/user",
            templateUrl: "screens/user.html",
            controller: "usercontroller"
        });
});
