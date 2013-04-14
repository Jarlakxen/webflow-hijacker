'use strict';


var BASE_URL = '/hijack/rest'

function $service($resource, $http, $name){
    var service = $resource(BASE_URL + '/' + $name + '/:id', {id:'@id'}, {});

    var fnquery = service.query;

    service.query = function(success, error) {

        switch(arguments.length) {
            case 2:
                return $ext_array(fnquery(success, error));
            case 1:
                return $ext_array(fnquery(success));
            case 0: 
                return $ext_array(fnquery());
            default:
                throw "Expected between 0-2 arguments [success, error], got " + arguments.length + " arguments.";
        }
    }

    service.prototype.$update = function() {
        $http.post(BASE_URL + '/' + $name + '/', this);
    }

    return service;
}

// ----------------------------------
// 			Rules Services
// ----------------------------------

angular.module('ruleService', ['ngResource']).factory('Rule', function($resource, $http){

	return $service($resource, $http, 'rule');
  
});
