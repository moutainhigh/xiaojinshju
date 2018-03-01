'use strict';

/**
 * Config for the router
 */
angular.module('app')
        .run(
                ['$rootScope', '$state', '$stateParams',
                    function ($rootScope, $state, $stateParams) {
                        $rootScope.$state = $state;
                        $rootScope.$stateParams = $stateParams;
                    }
                ]
                )
        .config(
                ['$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG',
                    function ($stateProvider, $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {

                        $urlRouterProvider.otherwise('/welcome');

                        $stateProvider
                                .state('welcome', {
                                    url: '/welcome',
                                    templateUrl: '/fengkongweishi/pages/welcome.html'
                                })
                                .state('developing', {
                                    url: '/developing',
                                    templateUrl: '/fengkongweishi/pages/developing.html'
                                })
                                .state('finance', {
                                    url: '/finance',
                                    templateUrl: '/fengkongweishi/pages/finance.html'
                                })
                                .state('deposit', {
                                    url: '/deposit',
                                    templateUrl: '/fengkongweishi/pages/deposit.html'
                                })
                                .state('employee_list', {
                                    url: '/employee_list',
                                    templateUrl: '/fengkongweishi/pages/employee_list.html',
                                    resole:load(['bootstrapPaginator'])
                                })
                                .state('team_list', {
                                    url: '/team_list',
                                    templateUrl: '/fengkongweishi/pages/team_list.html',
                                    resole:load(['bootstrapPaginator'])
                                })
                                .state('management', {
                                    url: '/management',
                                    templateUrl: '/fengkongweishi/pages/management.html',
                                    resole:load(['bootstrapPaginator'])
                                })
                                .state('editionSearch', {
                                    url: '/editionSearch',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/editionSearch.html',
                                    params:{
                                        searFlag:null,
                                        edition:null,
                                        name:null,
                                        idCard:null,
                                        mobile:null,
                                        bankCard:null,
                                        address:null
                                    }
                                })
                                .state('personmobile', {
                                    url: '/personmobile',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/personmobile.html'
                                })
                                .state('personloan_list', {
                                    url: '/personloan_list',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/personloan_list.html',
                                    resole:load(['bootstrapPaginator'])
                                })
                                .state('personloan_list.notes', {
                                    url: '/:id/notes',
                                    templateUrl: '/fengkongweishi/pages/personloan_notes.html',
                                     params:{id:null}
                                })
                                .state('personloan_form', {
                                    url: '/personloan_form',
                                    templateUrl: '/fengkongweishi/pages/personloan_form.html',
                                    params:{data:{}}
                                })
                                .state('personloan_detail', {
                                    url: '/personloan_detail/{id}_{name}_{idCard}_个人征信报告（通讯版）',
                                    templateUrl: '/fengkongweishi/pages/personloan_detail.html',
                                    params:{id:null,name:null,idCard:null}
                                })
                                .state('personweb_list', {
                                    url: '/personweb_list',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/personweb_list.html'
                                })
                                .state('approve', {
                                    url: '/approve',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/approve.html',
                                    params:{data:{}}
                                })
                                .state('newSearch', {
                                    url: '/newSearch',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/newSearch.html',
                                    params:{data:{}}
                                })
                                .state('searchList', {
                                    url: '/search_list',
                                    cache:true,
                                    templateUrl: '/fengkongweishi/pages/search_list.html',
                                    params:{data:{}}
                                })
                                .state('personweb_list.notes', {
                                    url: '/:id/notes',
                                    templateUrl: '/fengkongweishi/pages/personweb_notes.html',
                                     params:{id:null}
                                })
                                .state('personweb_form', {
                                    url: '/personweb_form',
                                    templateUrl: '/fengkongweishi/pages/personweb_form.html',
                                    params:{data:{}}
                                })
                                .state('personweb_detail', {
                                    url: '/personweb_detail/{id}_{name}_{idCard}_个人征信报告（网络版）',
                                    templateUrl: '/fengkongweishi/pages/personweb_detail.html',
                                    params:{id:null,name:null,idCard:null}
                                })
                                .state('carSearch', {
                                    url: '/carSearch',
                                    templateUrl: '/fengkongweishi/pages/carSearch.html',
                                    params:{reportId: null,edition: null,username:null,phone:null,idCard:null,appCode:null,searFlag:null}
                                })
                                .state('carrier', {
                                    url: '/carrier',
                                    templateUrl: '/fengkongweishi/pages/carrier.html',
                                    params:{reportId: null,edition: null,username:null,phone:null,idCard:null,appCode:null,searFlag:null}
                                })
                                .state('taobao', {
                                    url: '/taobao',
                                    templateUrl: '/fengkongweishi/pages/taobao.html',
                                    params:{reportId: null,edition: null,username:null,phone:null,idCard:null,appCode:null,searFlag:null}
                                })
                                .state('search_detail', {
                                    url: '/search_detail/{id}',
                                    templateUrl: '/fengkongweishi/pages/search_detail.html',
                                    params:{id:null}
                                });
                        function load(srcs, callback) {
                            return {
                                deps: ['$ocLazyLoad', '$q',
                                    function ($ocLazyLoad, $q) {
                                        var deferred = $q.defer();
                                        var promise = false;
                                        srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                                        if (!promise) {
                                            promise = deferred.promise;
                                        }
                                        angular.forEach(srcs, function (src) {
                                            promise = promise.then(function () {
                                                if (JQ_CONFIG[src]) {
                                                    return $ocLazyLoad.load(JQ_CONFIG[src]);
                                                }
                                                angular.forEach(MODULE_CONFIG, function (module) {
                                                    if (module.name == src) {
                                                        name = module.name;
                                                    } else {
                                                        name = src;
                                                    }
                                                });
                                                return $ocLazyLoad.load(name);
                                            });
                                        });
                                        deferred.resolve();
                                        return callback ? promise.then(function () {
                                            return callback();
                                        }) : promise;
                                    }]
                            }
                        }


                    }
                ]
                );
