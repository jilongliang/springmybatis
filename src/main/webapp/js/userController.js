'use strict';

angular.module('app').controller('userController', [ '$rootScope', '$scope', '$http', '$state', 
       function($rootScope, $scope, $http, $state) {
		
				//$state.go(basePath+'/user3/list');
				$scope.title = '用户管理';
		        $scope.param = { };
		       // $scope.loading = true;
		       // alert(11)
				$scope.search = function () {
			        $scope.loading = true;
					$.ajax({
						url : basePath+'/user3/list',
						data: $scope.param
					}).then(function(result) {
				        $scope.loading = false;
						if (result.httpCode == 200) {
							$scope.pageInfo = result.data;
						} else {
							$scope.msg = result.msg;
						}
						$scope.$apply();
					});
				}
				
				$scope.search();
				
				$scope.clearSearch = function() {
					$scope.param.keyword= null;
					$scope.search();
				}
				
				$scope.disableItem = function(id, enable) {
					
				}
				
				// 翻页
		        $scope.pagination = function (page) {
		            $scope.param.pageNum=page;
		            $scope.search();
		        };
		} 


]);