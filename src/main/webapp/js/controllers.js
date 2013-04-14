'use strict';

var app = angular.module('WebflowHijackerApp', [ 'ngResource', 'ruleService' ]);

// ----------------------------------
//			Shared Service
// ----------------------------------

app.run(['$rootScope', function($rootScope) {
	$rootScope.notifyAll = function(eventName, item) {
		$rootScope.$broadcast(eventName, item);
	};
}]);


// ----------------------------------
//			Rule Controllers 
// ----------------------------------

var RuleListCtrl = app.controller('RuleListCtrl', function ($scope, Rule) {
	
	$scope.rules = Rule.query();

	$scope.addRule = function (newRule){

		var rule = new Rule({id: null, name: newRule.name, regex: newRule.regex, defaultEndpoint: newRule.defaultEndpoint});
		rule.$save();
		
		$scope.rules.push(rule);
	};

	$scope.removeRule = function (selectedRule){
		$scope.rules.remove(selectedRule);

		selectedRule.$delete();
	};

});
