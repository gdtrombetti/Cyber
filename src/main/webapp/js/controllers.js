 var app = angular.module('MyApp', [])
    app.controller('UserController', function ($scope) {
        $scope.IsVisible = false;
        $scope.ShowHide = function () {
            $scope.IsVisible = $scope.IsVisible ? false : true;
        }
    });
 app.controller('FormController', function ($scope, $http) {
	 $scope.submit = function (form) {
     $scope.errMsg = "";
     $scope.successMsg = "";
     $scope.submitted = true;
     $scope.IsVisible = $scope.IsVisible ? false : true;
     $scope.user_data = "";
     if (form.$invalid) {
         return;
       }
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
			$scope.rname = data.name;
			$scope.remail = data.email;
			$scope.rtelephone = data.telephone;
			$scope.rstreet = data.street;
			$scope.rcity = data.city;
			$scope.rstate = data.state;
			$scope.rzip = data.zip;
			$scope.show = true;
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	 }
 });

        