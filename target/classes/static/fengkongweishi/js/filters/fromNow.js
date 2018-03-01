'use strict';

/* Filters */
// need load the moment.js to use this filter. 
angular.module('app')
.filter("fromNow", function () {
    return function (date) {
        return moment(date).fromNow();
    }
})
    .filter('edition',function () {
    return function (items) {
        if(items === [] || items === undefined){
            return "--";
        }
        var str = items.join(" ");
        if(str.indexOf("PERSONJUNIOR") >= 0 ){
            str = str.replace("PERSONJUNIOR","基础版");
        }
        if(str.indexOf("PERSONMOBILE") >= 0 ){
            str = str.replace("PERSONMOBILE","通信版");
        }
        if(str.indexOf("PERSONECOMMERCE") >= 0 ){
            str = str.replace("PERSONECOMMERCE","淘宝版");
        }
        if(str.indexOf("PERSONSENIOR") >= 0 ){
            str = str.replace("PERSONSENIOR","全面版");
        }
        return str;
    }
})
    .filter('editionType',function () {
        return function (type) {
            var str;
            switch (type){
                case "PERSONJUNIOR":
                    str = "基础版";
                    break;
                case "PERSONMOBILE":
                    str = "通信版";
                    break;
                case "PERSONECOMMERCE":
                    str = "淘宝版";
                    break;
                case "PERSONSENIOR":
                    str = "全面版";
                    break;
            }
            return str;
        }
    })
  .filter('licenseType',function(){
      return function (type) {
          if(type === "GENERAL"){
              return "普通营业执照"
          }else{
              return "多证合一营业执照"
          }
      }
  })
  .filter('role',function(){
    return function (name) {
        switch (name){
            case "ROLE_ADMIN":
                return "系统管理员";
                break;
            case "ROLE_MANAGER":
                return "超级管理员";
                break;
            case "ROLE_LEADER":
                return "管理员";
                break;
            case "ROLE_EMPLOYEE":
                return "员工";
                break;
            case "ROLE_USER":
                return "用户";
                break;
        }
    }
})    .filter('datetime', function () {
    return function (date) {
        if (parseInt(date) > 1400000) {
            date = (date + '000') * 1;
        }
        return moment(date).format('YYYY-MM-DD HH:mm:ss');
    };
}).filter('phone',function () {
    return function (number) {
        var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证规则
        var flag = reg.test(number);
        return flag;
    }
});
