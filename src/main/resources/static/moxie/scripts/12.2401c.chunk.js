webpackJsonp([12,52],{14:function(e,t,a){try{(function(){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}function n(e,t){var a={};for(var r in e)t.indexOf(r)>=0||Object.prototype.hasOwnProperty.call(e,r)&&(a[r]=e[r]);return a}function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0});var i=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}(),l=function(e,t,a){for(var r=!0;r;){var n=e,o=t,s=a;r=!1,null===n&&(n=Function.prototype);var i=Object.getOwnPropertyDescriptor(n,o);if(void 0!==i){if("value"in i)return i.value;var l=i.get;if(void 0===l)return;return l.call(s)}var u=Object.getPrototypeOf(n);if(null===u)return;e=u,t=o,a=s,r=!0,i=u=void 0}},u=a(2),c=r(u),d=a(16),f=a(10),p=(r(f),a(1)),m=function(e){function t(e,a){o(this,t),l(Object.getPrototypeOf(t.prototype),"constructor",this).call(this,e,a),this.state={}}return s(t,e),i(t,[{key:"componentDidMount",value:function(){}},{key:"render",value:function(){var e=this.props,t=e.showTips,a=e.showOrHideSpinner,r=e.size,o=e.color,s=e.boxSize,i=(n(e,["showTips","showOrHideSpinner","size","color","boxSize"]),{display:a,width:"100%",maxWidth:p.qsParams.maxWidth?p.qsParams.maxWidth:"800px",height:p.qsParams.height?p.qsParams.height+"px":"100%",position:"fixed",right:0,bottom:0,margin:"0 auto",left:0,top:0,zIndex:996,background:"rgba(0,0,0,.4)"});return c.default.createElement("div",{style:i},c.default.createElement("div",{className:"SpinerBox",style:{overflow:"hidden",background:"rgba(22,22,22,0.8)",position:"absolute",top:"40%",left:"50%",width:""+s,height:""+s,borderRadius:"10px"}},c.default.createElement("div",{className:"newSpiner",style:{top:t?s/6+"px":(s-r)/2+"px"}},c.default.createElement("svg",{xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 32 32",width:r,height:r,fill:o},c.default.createElement("path",{opacity:".25",d:"M16 0 A16 16 0 0 0 16 32 A16 16 0 0 0 16 0 M16 4 A12 12 0 0 1 16 28 A12 12 0 0 1 16 4"}),c.default.createElement("path",{d:"M16 0 A16 16 0 0 1 32 16 L28 16 A12 12 0 0 0 16 4z"}))),c.default.createElement("div",{style:{color:"#fff",textAlign:"center",width:"100%",fontSize:"13px",position:"absolute",top:""+2*s/3}},t)))}}]),t}(c.default.Component);t.default=m,m.contextTypes={history:function(){return d.PropTypes.history}},m.defaultProps={boxSize:120,size:48,showTips:"正在加载",color:"#fff"},e.exports=t.default}).call(this)}finally{}},17:function(e,t,a){try{(function(){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var e={DEFAULT:"DEFAULT",SUCC:"SUCC",FAIL:"FAIL"};t.TASK_STATUS=e}).call(this)}finally{}},19:function(e,t,a){try{(function(){"use strict";function e(e){return e&&e.__esModule?e:{default:e}}function r(e){var t=e.task,a=e.callbacks;a=l({waitCode:u.noop,doneSucc:u.noop,doneFail:u.noop,doneTimeout:u.noop,doneLogin:u.noop,pendingTimeout:u.noop,doing:u.noop,error:u.noop,spiner:u.noop,showProgress:u.noop,importSucc:u.noop,onError:u.noop,errorCatch:u.noop,refreshImgCode:u.noop,setqrCodeTaskInfo:u.noop,doneScan:u.noop,zxBankSpiner:u.noop,showQrcodeInCrawlStatus:u.noop,hideQrCode:u.noop},a);var d,f=n(t),p=u.request.get(f+("?d="+Date.now())).set(l({},m.default.addDefaultHeaders())).set("Accept","application/json").set("Cache-Control","no-store");return d="carrier"==t.taskType?(0,u.guardTimeoutLongtime)(p):(0,u.guardTimeout)(p),d.then(function(e){var n=!1;if(a.setqrCodeTaskInfo&&"qrcode"==t.loginType&&a.setqrCodeTaskInfo(t),a.showProgress&&a.showProgress(e.body.description),"LOGIN"!=e.body.phase&&(a.doneLogin&&(n=a.doneLogin(t,e.body)),"alipay"==t.taskType&&"WAIT_CODE"!=e.body.phase_status&&a.hideQrCode&&a.hideQrCode(),o(t),(c.qsParams.quitOnLoginDone||c.qsParams.canLeave)&&"zhengxin"!=t.taskType))return n=!0,!1;switch("LOGIN"==e.body.phase&&"WAIT_CODE"!=e.body.phase_status&&a.doneScan&&a.doneScan(),e.body.phase_status){case g.WAIT_CODE:a.spiner&&a.spiner(),a.zxBankSpiner&&a.zxBankSpiner(),a.waitCode&&(n=a.waitCode(t,e.body));break;case g.CONTINUE:a.spiner&&a.spiner(),a.zxBankSpiner&&a.zxBankSpiner(),a.waitCode&&a.waitCode(t,e.body);break;case g.DONE_SUCC:e.body.finished===!0?(a.zxBankSpiner&&a.zxBankSpiner(),storage.setItem(t.taskType+"ImportStatus","succ"),setTimeout(function(){a.spiner&&a.spiner(),a.showProgress&&a.showProgress(""),v.default.refreshStatus("1",e.body.description)},3e3),a.importSucc&&a.importSucc(e.body),a.doneSucc&&a.doneSucc(t,e.body),n=!0,s(t)):a.doing&&(n=a.doing(t,e.body));break;case g.DONE_FAIL:a.hideQrCode&&a.hideQrCode(),storage.setItem(t.taskType+"ImportStatus","fail"),"LOGIN"===e.body.phase?("BALO-23001-10"==e.body.error_code&&a.doneTimeout&&a.doneTimeout(t,e.body),a.refreshImgCode(),v.default.refreshStatus("-4",e.body.description)):v.default.refreshStatus("0",e.body.description),a.zxBankSpiner&&a.zxBankSpiner(),setTimeout(function(){a.spiner&&a.spiner(),a.showProgress&&a.showProgress("")},3e3),a.doneFail&&a.doneFail(t,e.body),n=!0,i(t,e);break;case g.DONE_TIMEOUT:a.hideQrCode&&a.hideQrCode(),storage.setItem(t.taskType+"ImportStatus","fail"),"LOGIN"===e.body.phase?v.default.refreshStatus("-4",e.body.description,t.loginType,e.body.phase):v.default.refreshStatus("0",e.body.description,t.loginType,e.body.phase),a.doneTimeout&&a.doneTimeout(t,e.body),n=!0,i(t,e);break;case g.DOING:v.default.refreshStatus("2",e.body.description,t.loginType,e.body.phase),a.doing&&(n=a.doing(t,e.body));break;default:v.default.refreshStatus("-3","异常错误"),a.doneFail&&a.doneFail(t,e.body),n=!0,i(t,e)}"LOGIN"!=e.body.phase,n||setTimeout(function(){r({task:t,callbacks:a})},b)}).catch(function(e){var r=e&&e.body&&e.body.detail||e;v.default.alert("服务异常，请稍候再试！"+r),a.zxBankSpiner&&a.zxBankSpiner(),a.spiner&&a.spiner(),a.onError&&a.onError(t,e),a.error&&a.error(t,e),a.errorCatch&&a.errorCatch(e)})}function n(e){var t="";return t="bank"==e.taskType?f.default.checkImportState.replace("{taskid}",e.taskId):"carrier"==e.taskType?f.default.checkCarrierImportStatev2.replace("{taskid}",e.taskId):"chsi"==e.taskType?f.default.checkChsiImportState.replace("{taskid}",e.taskId):"fund"==e.taskType?f.default.checkFundImportState.replace("{taskid}",e.taskId):"email"==e.taskType||"qq"==e.taskType?f.default.checkEmailImportState.replace("{taskid}",e.taskId):"tax"==e.taskType?f.default.taxStatus.replace("{task_id}",e.taskId)+("?d="+Date.now()):"security"==e.taskType?f.default.checkSecurityImportState.replace("{taskid}",e.taskId)+("?d="+Date.now()):"alipay"==e.taskType||"wechat"==e.taskType?f.default.commonImportStateUrlv2.replace("{taskid}",e.taskId):"sametrade"==e.taskType?f.default.checkSametradeTaskStatus.replace("{task_id}",e.taskId):f.default.commonImportStateUrl.replace("{taskid}",e.taskId)}function o(e){v.default.mxHideWebView(),v.default.mxCanLeave(!0),v.default.loginDone=1,(c.qsParams.canLeave||c.qsParams.quitOnLoginDone)&&"zhengxin"!=e.taskType&&(console.log("finishImport"),v.default.finishImport(e.taskId,"2","登录认证成功","SUCC"),(0,y.default)({taskType:e.taskType}))}function s(e){v.default.finishImport(e.taskId,"1","认证成功","SUCC"),(0,y.default)({taskType:e.taskType})}function i(e,t){console.log("handleDoneFail"),"LOGIN"===t.body.phase?"zhengxin"!=e.taskType&&("qrcode"==e.loginType&&"DONE_TIMEOUT"==t.body.phase_status||v.default.alert(t.body.description)):(v.default.finishImport(e.taskId,"0",t.description,"FAIL"),c.qsParams.quitOnFail&&(0,y.default)({taskType:e.taskType}))}Object.defineProperty(t,"__esModule",{value:!0});var l=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var r in a)Object.prototype.hasOwnProperty.call(a,r)&&(e[r]=a[r])}return e},u=a(1),c=a(1),d=a(25),f=e(d),p=a(12),m=e(p),h=a(29),y=e(h),k=a(4),v=e(k),b=2500,g={DOING:"DOING",WAIT_CODE:"WAIT_CODE",DONE_SUCC:"DONE_SUCC",DONE_FAIL:"DONE_FAIL",DONE_TIMEOUT:"DONE_TIMEOUT"};t.STATE=g,t.default=r}).call(this)}finally{}},21:function(e,t,a){try{(function(){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0});var s=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}(),i=function(e,t,a){for(var r=!0;r;){var n=e,o=t,s=a;r=!1,null===n&&(n=Function.prototype);var i=Object.getOwnPropertyDescriptor(n,o);if(void 0!==i){if("value"in i)return i.value;var l=i.get;if(void 0===l)return;return l.call(s)}var u=Object.getPrototypeOf(n);if(null===u)return;e=u,t=o,a=s,r=!0,i=u=void 0}},l=a(2),u=r(l),c=function(e){function t(e,a){n(this,t),i(Object.getPrototypeOf(t.prototype),"constructor",this).call(this,e,a)}return o(t,e),s(t,null,[{key:"propTypes",value:{desc:l.PropTypes.string.isRequired},enumerable:!0}]),s(t,[{key:"render",value:function(){var e=u.default.createElement("img",{style:{width:"350px"},src:a(45)});return u.default.createElement("div",{className:"waiting-view"},u.default.createElement("h3",{style:{width:"80%",textAlign:"center",fontSize:"16px",color:"#70BEEB",height:"50px",lineHeight:"50px",borderBottom:"1px solid #efeff4"}},"获取结果中..."),u.default.createElement("h4",{style:{height:"20px",fontSize:"14px",color:"#666666"}},this.props.desc),u.default.createElement("div",{className:"waiting-gif"},e))}}]),t}(u.default.Component);t.default=c,e.exports=t.default}).call(this)}finally{}},22:function(e,t,a){try{(function(){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0});var s=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}(),i=function(e,t,a){for(var r=!0;r;){var n=e,o=t,s=a;r=!1,null===n&&(n=Function.prototype);var i=Object.getOwnPropertyDescriptor(n,o);if(void 0!==i){if("value"in i)return i.value;var l=i.get;if(void 0===l)return;return l.call(s)}var u=Object.getPrototypeOf(n);if(null===u)return;e=u,t=o,a=s,r=!0,i=u=void 0}},l=a(2),u=r(l),c=a(1),d=a(23),f=a(5),p=r(f),m=(a(17),function(e){function t(e,a){n(this,t),i(Object.getPrototypeOf(t.prototype),"constructor",this).call(this,e,a),this.state={}}return o(t,e),s(t,null,[{key:"propTypes",value:{succ:l.PropTypes.bool.isRequired,taskInfo:l.PropTypes.any.isRequired,canQuitAuth:l.PropTypes.bool},enumerable:!0}]),s(t,[{key:"render",value:function(){var e=this,t={color:void 0!=c.qsParams.themeColor?"#"+c.qsParams.themeColor:d.DEFAULT_THEMECOLOR,fontSize:"12px"},a=void 0!=c.qsParams.themeColor?"#"+c.qsParams.themeColor:d.DEFAULT_THEMECOLOR,r=null;return r=this.props.succ?u.default.createElement("div",{className:"finish-status succ"},u.default.createElement("svg",{width:"48",height:"48",viewBox:"0 0 48 48"},u.default.createElement("g",{className:"transform-group"},u.default.createElement("g",{transform:"scale(0.046875, 0.046875)"},u.default.createElement("path",{d:"M511.996387 9.862132c-277.328216 0-502.134255 224.813265-502.134255 502.137868 0 277.328216 224.80604 502.137868 502.134255 502.137868 277.320991 0 502.137868-224.809652 502.137868-502.137868C1014.137868 234.675397 789.320991 9.862132 511.996387 9.862132L511.996387 9.862132zM824.889692 388.748635l-351.388132 338.834686c-4.670966 4.663741-9.952445 8.337656-15.587949 11.028971-22.065166 15.761349-52.915939 13.75641-72.730516-6.061779l-150.897848-150.897848c-22.065166-22.065166-22.065166-57.832555 0-79.883271 22.054329-22.065166 57.821717-22.065166 79.886884 0l113.291695 113.28447 317.531758-306.192112c22.061554-22.061554 57.832555-22.061554 79.883271 0C846.954858 330.919692 846.954858 366.687081 824.889692 388.748635L824.889692 388.748635zM824.889692 388.748635",fill:a})))),u.default.createElement("p",{style:t},"验证成功"),c.qsParams.token?u.default.createElement(p.default,{types:"full",style:"width:50%;height:44px;",onTap:function(){e.onSuccDetailButtonClick()}},"详情查询"):null):u.default.createElement("div",{className:"finish-status fail"},u.default.createElement("i",{className:"import-fail-img"}),u.default.createElement("p",{className:"fail-desc",style:{marginBottom:"20px"}},this.props.desc),"YES"!=c.qsParams.notRetryOnFail&&1!=c.qsParams.notRetryOnFail?u.default.createElement(p.default,{types:"full",style:"width:50%;height:44px;",onTap:function(){e.retryButtonClick()}},"点击重试"):null),u.default.createElement("div",{className:"waiting-view"},u.default.createElement("div",{className:"waiting-gif"},r))}},{key:"retryButtonClick",value:function(){window.location.reload()}},{key:"retryButtonClick",value:function(){window.location.reload()}},{key:"onSuccDetailButtonClick",value:function(){if(void 0==c.qsParams.token)return void PG.alert("缺少token值");var e=this.props.taskInfo,t=(0,c.getENV)(),a="";a="test"==t?"http://192.168.0.11:8282":"pre"==t||"qa"==t?"https://qa.51datakey.com":"https://api.51datakey.com";var r="";switch(e.taskType){case"email":r=a+"/h5/resultV3/index.html#/emailResult?taskId="+e.taskId+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl;break;case"bank":r=a+"/h5/resultV3/index.html#/bankResult/?taskId="+e.taskId+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl;break;case"carrier":r=a+"/h5/resultV4/carrier/index.html?taskId="+e.account+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl;break;case"shixin":r=a+"/h5/resultV4/shixin/index.html?taskId="+e.taskId+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl;break;case"tax":r=a+"/h5/resultV4/tax/index.html?taskId="+e.taskId+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl;break;case"zhixing":r=a+"/h5/resultV4/zhixing/index.html?taskId="+e.taskId+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl;break;default:r=a+"/h5/resultV3/index.html#/"+e.taskType+"?taskId="+e.taskId+"&token="+c.qsParams.token+"&mainTitle="+c.qsParams.mainTitle+"&icoUrl="+c.qsParams.icoUrl}"test"==t?r+="&env=test":"pre"!=t&&"qa"!=t||(r+="&env="+t),window.location.href=r}}]),t}(u.default.Component));t.default=m,e.exports=t.default}).call(this)}finally{}},23:function(e,t,a){try{(function(){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var e="#58B5EB";t.DEFAULT_THEMECOLOR=e}).call(this)}finally{}},24:function(e,t,a){try{(function(){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var e={LOGIN:"LOGIN",RECEIVE:"RECEIVE",EXTRACT:"EXTRACT",STORE:"STORE",DONE:"DONE"};t.PHASE=e}).call(this)}finally{}},231:function(e,t,a){try{(function(){"use strict";function r(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var n=function(){function e(e,t){var a=[],r=!0,n=!1,o=void 0;try{for(var s,i=e[Symbol.iterator]();!(r=(s=i.next()).done)&&(a.push(s.value),!t||a.length!==t);r=!0);}catch(e){n=!0,o=e}finally{try{!r&&i.return&&i.return()}finally{if(n)throw o}}return a}return function(t,a){if(Array.isArray(t))return t;if(Symbol.iterator in Object(t))return e(t,a);throw new TypeError("Invalid attempt to destructure non-iterable instance")}}(),o=a(2),s=r(o),i=a(1),l=a(9),u=a(8),c=r(u),d=a(4),f=r(d),p=a(15),m=r(p),h=a(13),y=r(h),k=a(5),v=r(k),b=a(11),g=(r(b),a(18)),T=r(g),S=a(6),w=r(S),E=a(7),P=r(E),O="qq",I="QQ",C=s.default.createClass({displayName:"ImportForm",propTypes:{setTaskStore:o.PropTypes.func},getInitialState:function(){return{phoneInfo:null,unsupport:!1,showUnsupport:!1,isStart:!1,defaultUsername:"",Type:"password",bgImg:"url("+a(20)+")"}},componentDidMount:function(){},componentWillUnmount:function(){this.unmount=!0},render:function(){var e=this,t=this.handleImport,a=this.handleProtocol,r=this.state,n=r.unsupport,o=r.showUnsupport,l=r.isStart,u=(r.defaultUsername,r.defaultCode,m.default.get(O,0,0,0)),d="";u&&u.loginParam&&(d=u.loginParam.phone);var f=void 0!=i.qsParams.agreementEntryText?i.qsParams.agreementEntryText:"同意《用户使用协议》",p=s.default.createElement(P.default,{onTap:c.default.bind(this,null)},s.default.createElement("i",{className:"ion-chevron-left"})),h=("YES"==this.props.isWeb?s.default.createElement(w.default,{title:I,left:p}):null,o?s.default.createElement("div",{className:"carrier-unsupport"},s.default.createElement("i",null),s.default.createElement("p",null,n)):s.default.createElement("div",null,s.default.createElement("div",{className:"input-item"},s.default.createElement(y.default,{type:"text",ref:"username",id:"zxUsername",placeholder:"请输入QQ号",label:"登录账号",defaultValue:d}),s.default.createElement("div",{onClick:function(){return e.clearValue("#zxUsername")},className:"tex_box name"},s.default.createElement("span",{className:"textt"}))),s.default.createElement("div",{className:"input-item"},s.default.createElement(y.default,{type:this.state.Type,ref:"password",id:"zxPassword",placeholder:"请输入登录密码",label:"密码"}),s.default.createElement("div",{onClick:this.showPwd,className:"tex_box"},s.default.createElement("span",{style:{backgroundImage:this.state.bgImg},className:"password password_show"}))),s.default.createElement("div",{style:{display:"NO"==i.qsParams.showAgreement||0==i.qsParams.showAgreement?"none":"block"},className:"protocol"},s.default.createElement("input",{id:"protocol",ref:"protocol",type:"checkbox",defaultChecked:!0}),s.default.createElement("label",{htmlFor:"protocol"},s.default.createElement("span",{style:{background:i.qsParams.themeColor?"#"+i.qsParams.themeColor:"rgb(126, 195, 235)"}})),s.default.createElement("a",{onClick:a},f)),i.qsParams.tenantAgreementUrl&&"undefined"!=i.qsParams.tenantAgreementUrl?s.default.createElement("div",{className:"protocol"},s.default.createElement("input",{id:"tanent_protocol",ref:"tanent_protocol",type:"checkbox",defaultChecked:!0}),s.default.createElement("label",{htmlFor:"tanent_protocol"},s.default.createElement("span",{style:{background:i.qsParams.themeColor?"#"+i.qsParams.themeColor:"rgb(126, 195, 235)"}})),s.default.createElement("a",{onClick:this.handleTanentProtocol},i.qsParams.tenantAgreementEntryText||"同意《用户使用协议》")):null,s.default.createElement("div",{className:"page-wrapper"},s.default.createElement(v.default,{types:"full",onTap:t,disable:l,state:l,getState:function(e){switch(e){case!1:return"提交";case!0:return"登录中...";default:return"提交"}}}))));return s.default.createElement("div",null,s.default.createElement(T.default,null,h))},clearValue:function(e){document.querySelector(e).parentNode.querySelector("input").value=""},showPwd:function(){"text"==this.state.Type?this.setState({Type:"password",bgImg:"url("+a(20)+")"}):this.setState({Type:"text",bgImg:"url("+a(37)+")"})},handleImport:function(){var e=this,t=this.props.onSubmitTaskDone,a=(this.state.phoneInfo,this.refs.username.getValue()),r=this.refs.password.getValue();if(!a)return void setTimeout(function(){return f.default.alert("请输入正确的登录名","")});if(!r)return void setTimeout(function(){return f.default.alert("请输入正确的登录密码","")});if(!this.refs.protocol.checked)return void setTimeout(function(){return f.default.alert("请勾选同意《用户使用协议》","")});if(this.refs.tanent_protocol&&!this.refs.tanent_protocol.checked)return void setTimeout(function(){return f.default.alert("请勾选同意《用户使用协议》","")});this.setState({isStart:!0});var n=void 0;(0,l.submitTaskHelper)({taskType:O,username:a,password:r}).then(function(r){if(n=r,r.body.task_id){e.setState({isStart:!1}),f.default.refreshStatus("2","创建任务成功"),f.default.mxSaveTaskId(r.body.task_id);var o={taskId:r.body.task_id,username:a,mappingId:r.body.mapping_id,taskType:O,loginParam:{phone:a}};t(o)}}).catch(function(t){e.setState({isStart:!1});var a=t&&t.response&&t.response.body||{};a.errors&&a.errors[0]&&(10220011===a.errors[0].code||10220012===a.errors[0].code)?(f.default.refreshStatus("-2",a.errors[0].message),e.showUnsupport(a.errors[0].message)):(f.default.refreshStatus("-3","服务异常，创建任务失败"),e.handleError(t,a.detail))})},handleError:function(e){var t=arguments.length<=1||void 0===arguments[1]?"任务提交失败":arguments[1];try{t=e&&e.body&&e.body.detail||t}catch(e){}if("任务提交失败"==t){if(e&&e.message&&e.message.indexOf("the network is offline")!=-1)return void f.default.alert("网络开小差啦，请稍后再试");f.default.alert(t+"\n"+e)}else f.default.alert(t);this.setState({isStart:!1})},handleProtocol:function(){var e=f.default.Device.SDK?0:i.qsParams.showTitleBar,t=i.qsParams.themeColor,a=i.qsParams.agreementUrl&&"undefined"!=i.qsParams.agreementUrl?i.qsParams.agreementUrl:"https://api.51datakey.com/h5/agreement.html?showTitleBar="+e+"&themeColor="+t;f.default.openWebView("用户使用协议",a,"","agreement")},handleTanentProtocol:function(){var e=i.qsParams.tenantAgreementUrl&&"undefined"!=i.qsParams.tenantAgreementUrl?i.qsParams.tenantAgreementUrl:"";f.default.openWebView("用户使用协议",e,"","agreement")},handleForgetSMS:function(e){var t=n(e,3),a=t[0],r=t[1],o=t[2];f.default.confirm(r,"","取消,发送",function(e){2==e&&((0,i.isIOS)()?window.location.href="sms:"+o+"&body="+a:window.location.href="sms:"+o+"?body="+a)})},showUnsupport:function(e){this.setState({unsupport:e||this.state.unsupport,showUnsupport:!0})}});t.default=C,e.exports=t.default}).call(this)}finally{}},818:function(e,t,a){try{(function(){"use strict";function t(e){return e&&e.__esModule?e:{default:e}}function r(e,t){var a={};for(var r in e)t.indexOf(r)>=0||Object.prototype.hasOwnProperty.call(e,r)&&(a[r]=e[r]);return a}function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var s=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var r in a)Object.prototype.hasOwnProperty.call(a,r)&&(e[r]=a[r])}return e},i=function(){function e(e,t){for(var a=0;a<t.length;a++){var r=t[a];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,a,r){return a&&e(t.prototype,a),r&&e(t,r),t}}(),l=function(e,t,a){for(var r=!0;r;){var n=e,o=t,s=a;r=!1,null===n&&(n=Function.prototype);var i=Object.getOwnPropertyDescriptor(n,o);if(void 0!==i){if("value"in i)return i.value;var l=i.get;if(void 0===l)return;return l.call(s)}var u=Object.getPrototypeOf(n);if(null===u)return;e=u,t=o,a=s,r=!0,i=u=void 0}},u=a(2),c=t(u),d=a(1),f=a(19),p=t(f),m=a(9),h=a(231),y=t(h),k=a(13),v=(t(k),a(5)),b=(t(v),a(8)),g=t(b),T=a(6),S=t(T),w=a(7),E=t(w),P=a(21),O=t(P),I=a(17),C=a(22),x=t(C),_=a(15),q=t(_),U=a(24),A=a(14),N=t(A),D="此次尝试失败，请重试",L="验证完成",j="QQImportTask",F=function(e){function t(e,a){n(this,t),l(Object.getPrototypeOf(t.prototype),"constructor",this).call(this,e,a),this.state={currentUsername:d.qsParams.username?d.qsParams.username:"",showSpiner:!0,taskInfo:null,taskStatus:I.TASK_STATUS.DEFAULT,desc:"",isLoginDone:!1}}return o(t,e),i(t,null,[{key:"propTypes",value:{showVCodeModal:u.PropTypes.func,hideVCodeModal:u.PropTypes.func},enumerable:!0}]),i(t,[{key:"componentDidMount",value:function(){PG.refreshTitle("QQ登录")}},{key:"render",value:function(){var e=this,t=this.state.currentUsername,a=r(this.props,[]),n=void 0;return n=this.state.isLoginDone?this.state.taskStatus==I.TASK_STATUS.DEFAULT?c.default.createElement(O.default,{desc:this.state.desc}):c.default.createElement(x.default,{succ:this.state.taskStatus==I.TASK_STATUS.SUCC,taskInfo:this.state.taskInfo,desc:this.state.desc}):c.default.createElement("div",null,c.default.createElement(y.default,s({defaultUsername:t,onSubmitTaskDone:this.onSubmitTaskDone.bind(this)},a)),this.state.taskInfo&&this.state.showSpiner?c.default.createElement(N.default,{color:"#fff",showTips:this.state.desc}):null),c.default.createElement("div",{className:"import-carrier"},c.default.createElement("div",{className:"mx-view"},"NO"==d.qsParams.showTitleBar||0==d.qsParams.showTitleBar||PG.Device.SDK?null:c.default.createElement("div",{style:{height:"NO"==d.qsParams.showTitleBar||PG.Device.SDK?0:44}},c.default.createElement(S.default,{title:"QQ登录",left:c.default.createElement(E.default,{onTap:function(){g.default.call(e)}},c.default.createElement("i",{className:"ion-chevron-left"}))})),n))}},{key:"onSubmitTaskDone",value:function(e){this.props.hideVCodeModal(),window.storage.setItem(j,JSON.stringify(e)),this.setState({taskInfo:e}),this.startPoll()}},{key:"startPoll",value:function(){(0,p.default)({task:this.state.taskInfo,isLogin:!1,callbacks:{waitCode:this.initVCode.bind(this),doneLogin:this.doneLogin.bind(this),doneSucc:this.onSucc.bind(this),doneFail:this.onFail.bind(this),doneTimeout:this.onFail.bind(this),doing:this.updateState.bind(this),pendingTimeout:this.onPendingTimeout.bind(this),error:this.onError.bind(this)}})}},{key:"initVCode",value:function(e,t){var a=this;return this.setState({showSpiner:!1}),this.props.showVCodeModal({title:"验证码",desc:t.description,waitSeconds:0,type:t.input.type,imgSrc:"img"==t.input.type?t.input.value:"",onConfirm:function(t){a.submitVCode(t,e),a.setState({showSpiner:!0})},onCancel:function(){a.storeLoginInfo(e,!0),a.setState({taskInfo:null,showSpiner:!0})}}),this.updateState(e,t),!0}},{key:"submitVCode",value:function(e,t){var a=this;(0,m.submitVCode)(e,t).catch(function(e){}).then(function(){return a.startPoll()})}},{key:"doneLogin",value:function(e,t){this.updateState(e,t),this.setState({isLoginDone:!0})}},{key:"removeTaskStore",value:function(){window.storage.removeItem(j)}},{key:"updateState",value:function(e,t){return this.setState({phase:t.phase,desc:t.description,isLoginDone:t.phase!==U.PHASE.LOGIN}),!!this.state.hasQuit}},{key:"storeLoginInfo",value:function(e){var t=!(arguments.length<=1||void 0===arguments[1])&&arguments[1];this.removeTaskStore(),this.props.params.cardType||d.qsParams.cardType,void 0==e.bankId&&(e.bankId=0),void 0==e.loginModel&&(e.loginModel=0),void 0==e.loginType&&(e.loginType=0),q.default.set(e.taskType,0,0,e.loginType,{loginParam:e.loginParam,loginFail:t})}},{key:"onSucc",value:function(e,t){this.storeLoginInfo(e),t.description=L,this.updateState(e,t),this.setState({taskStatus:I.TASK_STATUS.SUCC})}},{key:"onFail",value:function(e,t){this.storeLoginInfo(e,!0),t.phase!=U.PHASE.LOGIN&&this.setState({taskStatus:I.TASK_STATUS.FAIL,desc:t.description}),this.setState({taskInfo:null})}},{key:"onPendingTimeout",value:function(e){this.storeLoginInfo(e),this.setState({taskStatus:I.TASK_STATUS.FAIL,desc:D,taskInfo:null})}},{key:"onError",value:function(e,t){this.storeLoginInfo(e,!0),this.setState({taskStatus:I.TASK_STATUS.FAIL,desc:"服务异常,请稍后再试",taskInfo:null})}}]),t}(c.default.Component);e.exports=F}).call(this)}finally{}}});