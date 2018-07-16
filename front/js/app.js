var app = angular.module('app', ['ui.router', 'app.controllers']);

app.config(function($stateProvider, $urlRouterProvider) {
	
	$urlRouterProvider.otherwise("/home");

	$stateProvider
	  .state('home', {
		url : "/home",
		templateUrl : "partials/home.html",
		controller: "homecontroller"
	}).state('usercontroller', {
		url : "/user",
		templateUrl : "partials/user.html",
		controller: "usercontroller"
	});
});
