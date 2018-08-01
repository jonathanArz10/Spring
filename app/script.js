var app = angular.module("terminales", []);

app.controller("TerminalesController",['$scope', '$http',function($scope, $http) {
	
	var url = "api/terminales";
	
	$http({"method" : "GET", "url" : url})
	.then(function(respdata) {
		$scope.terminales = respdata.data;
	}, function(errResp, status, headers, config) {
		console.log("Error en la llamada ajax" + status);
	});
	
	var actualizarTerminales = function() {
		$http({"method" : "GET", "url" : url})
		.then(function(respdata) {
			$scope.terminales = respdata.data;
		}, function(errResp, status, headers, config) {
			console.log("Error en la llamada ajax" + status);
		});
	}
	
	$scope.nuevaTerminalPost = function() {
		if($scope.id === undefined || $scope.id === null){
			var datos = {
					"name" : $scope.nombre,
					"city" : $scope.ciudad,
					"direction" : $scope.direccion,
					"phoneNumber" : $scope.telefono
			}
			$http({"method" : "POST", "url" : url, data : datos})
			.then(function(respdata) {
				$scope.creadoCorrectamente = "Terminal nueva agregada";
				$scope.terminales.push(datos);
				$scope.nombre = "";
				$scope.ciudad = "";
				$scope.direccion = "";
				$scope.telefono = "";
			}, function(errResp, status, headers, config) {
				$scope.creadoError = "Ya existe una terminal con este nombre";
			});
		} else {
			var datos = {
					"id" : $scope.id,
					"name" : $scope.nombre,
					"city" : $scope.ciudad,
					"direction" : $scope.direccion,
					"phoneNumber" : $scope.telefono
			}
			$http({"method" : "PUT", "url" : url + "/" + $scope.id, data : datos})
			.then(function(respdata) {
				$scope.creadoCorrectamente = "Terminal actualizada";
				$scope.nombre = "";
				$scope.ciudad = "";
				$scope.direccion = "";
				$scope.telefono = "";
				$scope.id = "";
				actualizarTerminales();
			}, function(errResp, status, headers, config) {
				console.log("Error en la llamada ajax" + status);
			});
		}
	}
	
	$scope.editarTerminal = function(terminal_id) {
		$http({"method" : "GET", "url" : url + "/" + terminal_id})
		.then(function(respdata) {
			$scope.terminal = respdata.data;
			$scope.nombre = $scope.terminal.name;
			$scope.ciudad = $scope.terminal.city;
			$scope.direccion = $scope.terminal.direction;
			$scope.telefono = $scope.terminal.phoneNumber;
			$scope.id = $scope.terminal.id;
		}, function(errResp, status, headers, config) {
			console.log("Error en la llamada ajax" + status);
		});
	}
	
	$scope.eliminarTerminal = function(terminal_id) {
		$http({"method" : "DELETE", "url" : url + "/" + terminal_id})
		.then(function(respdata) {
			$scope.creadoCorrectamente = "Terminal eliminada";
			actualizarTerminales();
		}, function(errResp, status, headers, config) {
			$scope.creadoError = "No puedes eliminar está terminal, ya que cuenta con viajes";
		});
	}
}]);