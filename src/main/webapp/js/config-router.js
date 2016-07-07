'use strict';

var app = angular.module('app')
	.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider,   $urlRouterProvider) {
            // 默认地址
            $urlRouterProvider.otherwise('/index');
            // 状态配置
            $stateProvider
                .state('main', {
                    abstract: true,
                    url: '',
                    templateUrl: basePath+'/jsp/tpl/app.html'
                })
                .state('main.sys.user', {
                    url: '/user',
                    template: '<div ui-view class="fade-in-right-big smooth"></div>'
                })
                .state('main.sys.user.list', {
                    url: '/user/list',
                    templateUrl: basePath+'jsp/tpl/sys/user/user.html',
                    controller: 'userController',
                    resolve: {
                    	deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                            return uiLoad.load(basePath+'jsp/tpl/sys/user/userController.js');
                        }]
                      }
                })
               ;
    }])
     
    .run(['$rootScope', '$state', '$stateParams','$timeout', '$templateCache',
          function ($rootScope,$state,$stateParams,$timeout, $templateCache) {
              $rootScope.$state = $state;
              $rootScope.$stateParams = $stateParams;
              $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
                  var from =  fromState.name, to = toState.name;
                  if(from && to){ // 解决 相应模块从列表进入编辑后 状态丢失问题
                      var s1= from.substring(0,from.lastIndexOf(".")),
                          g1 = from.substring(from.lastIndexOf(".")+1),
                          s2 = to.substring(0,to.lastIndexOf(".")),
                          g2 = to.substring(to.lastIndexOf(".")+1);
                      if(s1 == s2){
                          if(g1 =='list' && (g2=='update'||g2=='view')) { //进行编辑
                              toParams['params'] = window.location.hash;
                          }
                          //返回列表
                          if((g1 == "update"||g1 =='view') && g2=='list') {
                              var h = fromParams['params'];
                              if(h){
                                  $timeout(function(){
                                      window.location.hash = h;
                                  });
                              }
                          }
                      }
                  }
              });
          }
      ]);