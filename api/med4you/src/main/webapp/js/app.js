var app = angular.module('app', ['ui.router', 'app.controllers']);

app.config(function($stateProvider, $urlRouterProvider) {
	
	$urlRouterProvider.otherwise("/home");

	$stateProvider
	  .state('medicine', {
		url : "/medicine",
		templateUrl : "screens/medicine.html",
		controller: "medicinecontroller"
	}).state('user', {
		url : "/user",
		templateUrl : "screens/user.html",
		controller: "usercontroller"
	});
});
