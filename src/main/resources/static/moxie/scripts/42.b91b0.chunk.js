webpackJsonp([42],{830:function(e,t,r){try{(function(){"use strict";function t(e){return e&&e.__esModule?e:{default:e}}function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=function(){function e(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}return function(t,r,n){return r&&e(t.prototype,r),n&&e(t,n),t}}(),l=function(e,t,r){for(var n=!0;n;){var o=e,a=t,l=r;n=!1,null===o&&(o=Function.prototype);var i=Object.getOwnPropertyDescriptor(o,a);if(void 0!==i){if("value"in i)return i.value;var c=i.get;if(void 0===c)return;return c.call(l)}var u=Object.getPrototypeOf(o);if(null===u)return;e=u,t=a,r=l,n=!0,i=u=void 0}},i=r(2),c=t(i),u=(r(16),r(1)),s=r(10),f=t(s),d=r(8),p=t(d),m=r(6),h=t(m),v=r(7),y=t(v),b=function(e){function t(e,r){n(this,t),l(Object.getPrototypeOf(t.prototype),"constructor",this).call(this,e,r),this.state={themeColor:u.qsParams.themeColor?"#"+u.qsParams.themeColor:"#c11000"}}return o(t,e),a(t,[{key:"componentDidMount",value:function(){}},{key:"render",value:function(){var e=this;return c.default.createElement("div",{className:"zx-new mx-view",style:{padding:"0 10px"}},"NO"==u.qsParams.showTitleBar||0==u.qsParams.showTitleBar||PG.Device.SDK?null:c.default.createElement("div",{style:{height:"NO"==u.qsParams.showTitleBar||PG.Device.SDK?0:44}},c.default.createElement(h.default,{title:"征信报告查询",left:c.default.createElement(y.default,{onTap:function(){p.default.call(e)}},c.default.createElement("i",{className:"ion-chevron-left"}))})),c.default.createElement("div",{className:"ques-title",style:{padding:"0 10px",color:"#666",height:"50px",lineHeight:"50px"}},"个人信用信息服务平台"),c.default.createElement("div",{className:"ques-content",style:{padding:"10px",background:"#fff"}},c.default.createElement("div",{className:"agree_title",style:{lineHeight:"55px",color:"#666",fontWeight:"700",borderBottom:"#c11000 1px solid",borderColor:this.state.themeColor}},"个人信用信息服务平台用户服务协议"),c.default.createElement("div",{className:"agreementText"},c.default.createElement("p",null,'中国人民银行征信中心（简称"我们"或者"征信中心"）拥有征信中心个人信用信息服务平台（简称"本网站"）， 为明确用户与征信中心在接受和提供中国人民银行征信中心个人信用信息服务平台查询服务过程中的权利和义务，本着平等自愿的原则，双方就相关事宜达成如下协议。 第一条：服务事项 本协议所指的中国人民银行征信中心个人信用信息服务平台查询服务包括个人信贷交易逾期信息提示、个人信用信息概要和本人简版信用报告。 第二条：用户的权利和义务 （一）用户必须为年满18岁并具有完全民事行为能力的自然人。 （二）用户只能查询自己的信用信息，不得要求征信中心提供他人的信用信息。 （三）用户点击"同意"，即是向征信中心证明、声明和保证用户为所查询信用信息的主体。 （四）用户应对以该用户名义进行的申请、查询等所有操作行为承担法律责任。 （五）用户应注意以下事项，否则用户承担由此带来的不利后果： 1.用户应向中心提供正确、完整、真实的用户注册申请资料和其它表单，并根据实际变化情况及时更新。因注册资料有误引起的后果由用户承担。 2.用户妥善保管本人信息，包括并不限于账号、密码。如因用户本人保管个人信息不善，或网银系统、互联网邮箱系统出现安全问题，导致他人获得您的个人信息，或因此导致个人信用报告被他人取得可能导致用户遭受损失的后果由用户承担。 （六）查询获得的信用信息仅供用户了解自己的信用状况、了解信用报告上的信息是否正确、完整，并因此提出异议申请。 （七）用户不得传送任何包含病毒、木马、蠕虫等可能破坏，感染，密码拦截任何系统，数据和信息的程序，不得通过黑客、密码破译等方式违法侵入计算机和网络系统，他人账户。 第三条：征信中心的权利和义务 （一）征信中心有权制定中国人民银行征信中心个人信用信息服务平台查询服务的相关业务操作规范。 （二）征信中心有权依据法律、法规、规章或业务需要对个人信用信息服务平台查询的服务内容、操作流程进行调整，并在征信中心网站对外公告有关变更事项后实施，不另行单独通知用户。 （三）征信中心收集本人信息，只为核实并确认身份及从数据库中抽取正确的信用信息，不会对外提供或泄露，不会用作其他用途。 （四）为处理用户使用征信中心个人信用信息服务平台查询要求，以确定用户即为上述所证明、声明和保证的人士，征信中心可查阅和使用目前由征信中心保留在系统中任何和全部用户的信用报告信息，以及用户在使用征信中心个人信用信息服务平台时所提供的用于配对的资料。 （五）个人信用信息服务平台是征信中心的专有系统，征信中心可将用户在使用个人信用信息服务平台时提供的材料与征信中心保留在其系统中任何和全部用户的信用报告信息进行配对。 （六）用户使用个人信用信息服务平台时所提供给征信中心的电话号码包括手机号码，征信中心或者授权第三者将来任何时候，可在个人信用信息服务平台或其相关系统，用于鉴别用户身份的用途。 （七）征信中心建立和完善内部管理制度，维护本网站的正常运行，保证按照有关业务规则公布的时间对外提供服务。 第四条 不可抗力条款 因下列原因致使个人信用信息服务平台不能正常提供服务而可能导致的损失，征信中心不承担责任： 1.因台风、地震、海啸、洪水、战争等不可抗力因素，造成互联网不能正常执行业务； 2.计算机病毒、黑客攻击、网络通信故障等征信中心不能控制的因素； 3.为了维护个人信用信息服务平台的正常运行，征信中心定期或不定期地对系统运行的相关设备进行维护或者检修，因此而造成查询服务在合理时间内的中断，征信中心不承担责任。征信中心将在合理时间段内通过征信中心网站公告维护时间。 第五条 协议的变更和终止 鉴于网络服务的特殊性，征信中心变更本协议及其附件的若干条款时，将提前通过征信中心的互联网站公告有关变更事项。如用户在征信中心发布上述协议变更的有关公告后继续使用互联网查询的，视为用户已接受协议的有关变更，并受其约束。本协议中的相关条款根据该变更而自动做相应修改，双方无须另行签订书面协议。 第六条 版权 （一）本互联网上包括享有版权的材料、商标和其他拥有所有权的信息，包括文本、软件、照片、影像、图形、音乐和声音等。征信中心拥有对这些内容及这些内容的原作进行选择、协调、安排和增加的版权。用户不能进行修改、出版、传播、参加转让或售卖、创作派生作品、或以任何方式部分或全部利用这些内容的任何一种。 （二）用户只可因个人使用而下载享有版权的材料。除了经版权法特别允许的，任何未经我们特别允许的复制、再分发、再传播、对下载材料的出版或商业性的利用都是不允许的。任何经允许的复制、再分发、再传播、对下载材料的出版中不得对原创人意图、商标图例或版权说明做任何改动。 （三）用户承认不对下载下来的材料享有任何所有权。 （四）用户不得在网站上上传、张贴任何受版权、商标或其他所有权保护却未经版权拥有者特别同意的材料，商标或其他所有权和决定任何材料不受版权保护的责任均在用户。 （五）用户须对由侵犯版权、所有权造成的危害，或任何其他由于这样一种提交而导致的危害负独立责任。 （六）如用户在网站的任何公共区域提交材料，则用户自动允许或授权该材料的所有者已特别授权我们享有免费使用、永久的、不能变更的、非专利的权利，并拥有在全世界范围内使用、复制、修改、改编、出版、翻译、分发这些材料（部分或全部地）和/或在这些材料中包含的任何版权期限内以任何形式、媒体或者现在已知或以后将发展的技术合并这些材料的特许。用户也允许任何其他用户出于个人使用的目的访问、查看、保存或复制这些材料。用户因此允许我们享有编辑、复制、出版和分发任何用户放置在网站上可自由获得的材料的权利。 （七）用户使用征信中心商标的，须在所有副本上均清楚地标明商标所属征信中心；没有征信中心的书面许可，用户不得修改或在用户的团体外透露商标信息。征信中心保留关于商标信息的其他权利。 第七条 免责条款 （一）不管基于任何责任理论的直接的、间接的、特殊的、惩罚性的、惩戒性的、附带的、或结果性的损害、损失或费用，我们均不对其承担责任。即使有人告知我们或我们的员工存在出现这些损害、损失或费用的可能性。这些损害、损失或费用由以下这些情况引起或与这些情况有关： 1.使用我们网站上或其他链接网站上的信息； 2.无法使用这些信息； 3.任何在操作或传输中出现的操作失败、错误、遗漏、中断、缺陷、延迟，计算机病毒，断线或系统运行失败。 （二）征信中心可以在不事先通知的情况下更改信息，并且不承担更新这些信息的义务。不经任何种类的授权，不做任何专门或暗指或法定的不侵犯第三方权利、名称、可出售性、出于某种特殊目的适当措施或不携带计算机病毒的保证。 （三）征信中心不对用户查询信息内容的正确性、适当性、完整性、准确性、可靠性或适时性做出任何证明、声明和保证。征信中心不对任何因个人信用信息服务平台产生的错误、遗漏及失准承担任何责任。 （四）在任何情况下，征信中心不声明及保证只要用户提供正确资料，就一定能通过个人信用信息服务平台获取本人相关信用信息。 （五）使用个人信用信息服务平台不应作为针对征信中心及/或其高级人员、雇员、代表、代理人作出任何申诉、起诉、要求或者诉讼或者其他法律程序因由的依据。 （六）对于由于用户违反本协议导致任何第三方针对征信中心及/或其高级人员、雇员、代表、代理人提出的任何申诉、起诉、要求或者诉讼或者其他法律程序，用户同意自费作出赔偿并令其免受上述损害。 第八条 法律适用条款以及争议解决方式 本协议的生效、解释、履行及争议的解决均适用中华人民共和国法律。在协议履行期间，凡由本协议引起的或与本协议有关的一切争议、纠纷，当事人应首先协商解决。协商不成，在乙方所在地法院提起诉讼。 第九条 附则 （一）本协议的某一条款被确认无效，均不影响本协议其他条款的效力。 （二）本协议未尽事宜，根据我国相关法律、法规及征信中心相关业务规定办理。如需制定补充协议，其法律效力同本协议。 （三）本协议自使用者点击"同意"并取得用户身份，则协议成立。至用户办理完毕用户注销时终止。 （四）若本协议中有任何条文被任何司法管辖区具有适当司法管辖权的法官裁定为非法、无效或不可强制执行的，本合同的其他条款仍继续有效。')),c.default.createElement(f.default,{onTap:this.correct.bind(this)},c.default.createElement("button",{style:{width:"70%",background:this.state.themeColor,border:"none",borderRadius:"5px",display:"block",margin:"20px auto",color:"#fff",fontSize:"16px",height:"44px",lineHeight:"44px",outline:"none"},className:"agree_btn"},"确认"))))}},{key:"correct",value:function(){p.default.call(this)}}]),t}(c.default.Component);e.exports=b}).call(this)}finally{}}});