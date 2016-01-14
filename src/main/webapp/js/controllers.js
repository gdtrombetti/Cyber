 var app = angular.module('MyApp', [])
    app.controller('UserController', function ($scope, $http) {
        $scope.IsVisible = false;
        $scope.ListVisible = false;
        $scope.ShowHide = function () {
            $scope.IsVisible = $scope.IsVisible ? false : true;
        }
        $scope.ShowList = function () {
            $scope.ListVisible = $scope.ListVisible ? false : true;
        }
        $scope.get_user = function() {
    		$http.get('getUser').
    		success(function(data, status, headers, config){
    			console.log(data);
    		}).
    		error(function(data, status, headers, config) {
    			alert( "failure message: " + JSON.stringify({data: data}));
    		});
    	};
    });

 app.controller('FormController', function ($scope, $http) {
	 $scope.submit = function (form, user) {
     $scope.submitted = true;
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
     	var res = $http.post('addUser', dataObj);
		res.success(function(data, status, headers, config) {
			$scope.rname = data.name;
			$scope.remail = data.email;
			$scope.rtelephone = data.telephone;
			$scope.rstreet = data.street;
			$scope.rcity = data.city;
			$scope.rstate = data.state;
			$scope.rzip = data.zip;
			$scope.show = true;
			$scope.name = "";
			$scope.email = "";
			$scope.telephone = "";
			$scope.street = "";
			$scope.city = "";
			$scope.state = "";
			$scope.zip = "";
		});
		res.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});
	 }
 });

        