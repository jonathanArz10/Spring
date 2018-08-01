var app = angular.module("autobuses", []);

app.controller("autobusesController",['$scope', '$http',function($scope, $http) {
	
	var url = "api/autobuses";
	
	$http({"method" : "GET", "url" : url})
	.then(function(respdata) {
		$scope.autobuses = respdata.data;
	}, function(errResp, status, headers, config) {
		console.log("Error en la llamada ajax" + status);
	});
	
	var actualizarautobuses = function() {
		$http({"method" : "GET", "url" : url})
		.then(function(respdata) {
			$scope.autobuses = respdata.data;
			$scope.creadoError = "";
			$scope.creadoCorrectamente = "";
		}, function(errResp, status, headers, config) {
			console.log("Error en la llamada ajax" + status);
		});
	}
	
	$scope.nuevoAutobusPost = function() {
		if($scope.id === undefined || $scope.id === null){
			var datos = {
					"enrollment" : $scope.enrollment,
					"model" : $scope.model,
					"type" : $scope.type
			}
			$http({"method" : "POST", "url" : url, data : datos})
			.then(function(respdata) {
				$scope.creadoCorrectamente = "autobus nuevo agregado";
				$scope.enrollment = "";
				$scope.model = "";
				$scope.type = "";
				actuzarautobuses();
			}, function(errResp, status, headers, config) {
				$scope.creadoError = "Ya existe una autobus con esta matricula";
			});
		} else {
			var datos = {
					"id" : $scope.id,
					"enrollment" : $scope.enrollment,
					"model" : $scope.model,
					"type" : $scope.type
			}
			$http({"method" : "PUT", "url" : url + "/" + $scope.id, data : datos})
			.then(function(respdata) {
				$scope.enrollment = "";
				$scope.model = "";
				$scope.type = "";
				$scope.id = "";
				actualizarautobuses();
			}, function(errResp, status, headers, config) {
				console.log("Error en la llamada ajax" + status);
			});
		}
	}
	
	$scope.editarautobus = function(autobus_id) {
		$http({"method" : "GET", "url" : url + "/" + autobus_id})
		.then(function(respdata) {
			$scope.autobus = respdata.data;
			$scope.enrollment = $scope.autobus.enrollment;
			$scope.model = $scope.autobus.model;
			$scope.type = $scope.autobus.type;
			$scope.id = $scope.autobus.id;
		}, function(errResp, status, headers, config) {
			console.log("Error en la llamada ajax" + status);
		});
	}
	
	$scope.eliminarautobus = function(autobus_id) {
		$http({"method" : "DELETE", "url" : url + "/" + autobus_id})
		.then(function(respdata) {
			$scope.creadoCorrectamente = "autobus eliminado";
			actualizarautobuses();
		}, function(errResp, status, headers, config) {
			$scope.creadoError = "No puedes eliminar está autobus, ya que cuenta con viajes";
		});
	}
}]);