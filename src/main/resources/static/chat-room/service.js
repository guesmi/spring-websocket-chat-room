angular.module("messengerApp.services", [ "ngResource" ]).factory(
	"messageService",
	function($resource, $q, $http) {
	    return {
		postMessage : function(message) {
		    var deferred = $q.defer();
		    var promise = $http.put('/message', message)
			    .then(function(data) {

				deferred.resolve(data);
			    });

		    return deferred.promise;
		},
		getMessages : function() {
		    var deferred = $q.defer();
		    var promise = $http.get('/message')
			    .then(function(data) {

				deferred.resolve(data);
			    });

		    return deferred.promise;
		}
	    }
	});