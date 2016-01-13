 var app = angular.module('MyApp', [])
    app.controller('UserController', function ($scope) {
        $scope.IsVisible = false;
        $scope.ShowHide = function () {
            $scope.IsVisible = $scope.IsVisible ? false : true;
        }
    });
 app.controller('FormController', function ($scope, $http) {
	 $scope.submit = function () {
     $scope.errMsg = "";
     $scope.successMsg = "";
     $scope.submitted = true;
     var dataObj = {
				name : $scope.name,
				email : $scope.email,
				telephone : $scope.telephone,
				street : $scope.street,
				city : $scope.city,
				state : $scope.state,
				zip : $scope.zip				
		};
     var res = $http.post('add_user', dataObj);
		res.success(function(data, status, headers, config) {
			$scope.message = data;
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	 }
 });

        