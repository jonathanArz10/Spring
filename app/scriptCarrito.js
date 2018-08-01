var app = angular.module("carrito", []);

app.controller("SalesController",['$scope', '$http',function($scope, $http) {
	$scope.total = 0;
	$scope.adulto = 0;
	$scope.kid = 0;
	$scope.terceraEdad = 0;
	$scope.salida = new Date();

	$scope.suma = function(precio) {
		$scope.total = ($scope.adulto * precio) + ($scope.kid * precio * .5) 
			+ ($scope.terceraEdad * precio * .5);
	}
}]);