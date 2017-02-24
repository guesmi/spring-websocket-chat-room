angular.module("messengerApp.controllers", []).controller("messengerCtrl",
	function($scope, messageService) {

	    $scope.newMsg;
	    
	    $scope.makeId = function()
		{
		    var text = "";
		    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		    for( var i=0; i < 5; i++ )
		        text += possible.charAt(Math.floor(Math.random() * possible.length));

		    return text;
		}
	    $scope.user = $scope.makeId();
	    $scope.postMessage = function() {
		var msg = {};
		msg.text = $scope.newMsg;
		msg.user = $scope.user;
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