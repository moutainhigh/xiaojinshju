'use strict';

/* Controllers */

angular.module('app')
  .controller('AppCtrl', ['$rootScope','$scope', '$translate', '$localStorage', '$window', '$http','$timeout','$state',
    function(              $rootScope,$scope,   $translate,   $localStorage,   $window, $http, $timeout, $state) {
      // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      if(isIE){ angular.element($window.document.body).addClass('ie');}
      if(isSmartDevice( $window ) ){ angular.element($window.document.body).addClass('smart')}
      window.role_dir = '/user';

      $http.get("/user/myInfo").success(function(resResult){
          console.log(resResult);
            resResult = resResult.data;
            $rootScope.myinfo=resResult;
            console.log($rootScope.myinfo);
        });

      //获取当前日期 格式20180101
        $rootScope.getDate = function () {
            var d = new Date();
            var day = d.getDate() <10 ? d.getDate()+'0' : d.getDate();
            var month = d.getMonth()+1;
            month = month <10 ? month + '0' : month;
            var year = String(d.getFullYear());
            return year+month+day;
        };

        //弹出框
        var layer;
        layui.use('layer',function () {
            layer = layui.layer;
        });
        $rootScope.infoBox = function (text) {
            layer.open({
                type: 1
                ,offset: 'rb'
                ,content: '<div style="padding: 20px 100px;">'+ text +'</div>'
                ,btn: '关闭'
                ,btnAlign: 'c' //按钮居中
                ,shade: 0 //不显示遮罩
                ,yes: function(){
                    layer.closeAll();
                }
            });
        };

        //正则验证
        //手机号正则验证
        $rootScope.phoneCheck = function (number) {
            var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
            return reg.test(number);
        };

        //身份证正则验证
        $rootScope.idCardCheck = function (number) {
            var reg = /(^[1-9]\d{5}(19|20)\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$)/;
            return reg.test(number);
        };

        // //loading
        // $rootScope.load = function () {
        //     var div = document.createElement("div");
        //     div.className = "loading";
        //     var body = document.getElementsByTagName("body");
        //     body.appendChild(div);
        // };




      /*
       * 设置分页的函数
       * @author  金杭
       * @param   selector  {string}    分页的选择器
       * @param   pageList  {object}    分页参数
       * @param   callback  {function}  点击页码后的回调函数
       * */
      $rootScope.setPaginator = function (selector, pageList, callback) {
          //分页的配置
          var pageOptions = {
              bootstrapMajorVersion: 3, //版本
              currentPage: pageList.current_page + 1, //当前页数
              // numberOfPages:pageList.num_page,//显示页数
              totalPages: pageList.total_page, //总页数
              tooltipTitles: function (type, page, current) {
                  //修改鼠标悬停title为中文
                  switch (type) {
                      case "next":
                          return "下一页";
                      case "last":
                          return "末页";
                      case "page":
                          return "第" + page + "页";
                  }
              },
              itemTexts: function (type, page, current) {
                  //修改按钮文字为中文
                  switch (type) {
                      case "first":
                          return "首页";
                      case "prev":
                          return "上一页";
                      case "next":
                          return "下一页";
                      case "last":
                          return "末页";
                      case "page":
                          return page;
                  }
              },
              onPageChanged: function (event, oldPage, newPage) {
                  //Ajax来刷新整个list列表
                  callback(newPage - 1);
              }
          };
          //设置分页插件
          $(selector).bootstrapPaginator(pageOptions);
      };

      //权限设置
        $rootScope.menuClick = function (menu) {
            console.log(menu,$rootScope.myinfo.role.name);
            if($rootScope.myinfo.role.name === "ROLE_ADMIN"){
                if(menu === "management"){
                    $state.go(menu);
                }else{
                    alert("当前角色无权限执行此操作");
                }
            }else if($rootScope.myinfo.role.name === "ROLE_USER"){
                if(menu !== "welcome"){
                    alert("当前没有权限，请立即前往认证");
                }else{
                    $state.go(menu);
                }
            }else if(!$rootScope.myinfo.company){
                if(menu !== "welcome"){
                    alert("当前没有权限，请立即前往认证");
                }else{
                    $state.go(menu);
                }
            }else if($rootScope.myinfo.company.status === 'VERIFYING'){
                if(menu !== "welcome"){
                    alert("当前没有权限，请等待认证");
                }else{
                    $state.go(menu);
                }
            }else{
                $state.go(menu);
            }
        };
      // config
      $scope.app = {
        name: 'Angulr',
        version: '2.2.0',
        // for chart colors
        color: {
          primary: '#7266ba',
          info:    '#23b7e5',
          success: '#27c24c',
          warning: '#fad733',
          danger:  '#f05050',
          light:   '#e8eff0',
          dark:    '#3a3f51',
          black:   '#1c2b36'
        },
        settings: {
          themeID: 1,
          navbarHeaderColor: 'bg-black',
          navbarCollapseColor: 'bg-white-only',
          asideColor: 'bg-black',
          headerFixed: true,
          asideFixed: false,
          asideFolded: false,
          asideDock: false,
          container: false
        }
      };

      // save settings to local storage
      if ( angular.isDefined($localStorage.settings) ) {
        $scope.app.settings = $localStorage.settings;
      } else {
        $localStorage.settings = $scope.app.settings;
      }
      $scope.$watch('app.settings', function(){
        if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
          // aside dock and fixed must set the header fixed.
          $scope.app.settings.headerFixed = true;
        }
        // for box layout, add background image
        $scope.app.settings.container ? angular.element('html').addClass('bg') : angular.element('html').removeClass('bg');
        // save to local storage
        $localStorage.settings = $scope.app.settings;
      }, true);

      // angular translate
      $scope.lang = { isopen: false };
      $scope.langs = {en:'English', de_DE:'German', it_IT:'Italian'};
      $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
      $scope.setLang = function(langKey, $event) {
        // set the current lang
        $scope.selectLang = $scope.langs[langKey];
        // You can change the language during runtime
        $translate.use(langKey);
        $scope.lang.isopen = !$scope.lang.isopen;
      };

      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }

  }]);
app.directive('onFinishRender', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
//			    scope.$last(判断条件，这里直接跳过);
            if (true) {
                $timeout(function() {
                    scope.$emit(attr.onFinishRender);
                });
            }
        }
    };
});