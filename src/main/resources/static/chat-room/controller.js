angular.module("messengerApp.controllers", []).controller("messengerCtrl",
	function($scope, messageService) {

	    $scope.newMsg;
	    $scope.postMessage = function() {
		var msg = {};
		msg.text = $scope.newMsg;

		messageService.postMessage(msg).then(function(data) {
		    $scope.newMsg = '';
		})
	    }

	    $scope.getMessages = function() {

		messageService.getMessages().then(function(result) {
		    $scope.messages = result.data;
		})
	    }
	    
		
            $scope.connect = function() {
		 $scope.getMessages();
        	 var socket = new SockJS('/messenger');
        	    $scope.stompClient = Stomp.over(socket);
        	    $scope.stompClient.connect({}, function (frame) {
        	        console.log('Connected: ' + frame);
        	        $scope.stompClient.subscribe('/topic/notify', function () {
        	            $scope.getMessages();
        	        });
        	    });
	};
	
	
	
	    /** ************* */
	    $scope.connect();
	    
	    
	});