(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-e18ca69c"],{"2d64":function(t,e,a){"use strict";var s=a("db72"),i=a("9256"),r={page:{url:"/authority/station/page",method:"GET"},save:{url:"/authority/station",method:"POST"},update:{url:"/authority/station",method:"PUT"},delete:{url:"/authority/station",method:"DELETE"}};e["a"]={page:function(t){return Object(i["a"])(Object(s["a"])({},r.page,{formData:!0,data:t}))},save:function(t){return Object(i["a"])(Object(s["a"])({},r.save,{data:t}))},update:function(t){return Object(i["a"])(Object(s["a"])({},r.update,{data:t}))},delete:function(t){return Object(i["a"])(Object(s["a"])({},r.delete,{data:t}))}}},"9fb8":function(t,e,a){"use strict";var s=a("db72"),i=a("9256"),r={page:{method:"GET",url:"/authority/user/page"},save:{method:"POST",url:"/authority/user"},update:{method:"PUT",url:"/authority/user"},avatar:{method:"PUT",url:"/authority/user/avatar"},delete:{method:"DELETE",url:"/authority/user"},reset:{method:"GET",url:"/authority/user/reset"},updatePassword:{method:"PUT",url:"/authority/user/password"}};e["a"]={page:function(t){return Object(i["a"])(Object(s["a"])({},r.page,{formData:!0,data:t}))},save:function(t){return Object(i["a"])(Object(s["a"])({},r.save,{data:t}))},update:function(t){return Object(i["a"])(Object(s["a"])({},r.update,{data:t}))},updatePassword:function(t){return Object(i["a"])(Object(s["a"])({},r.updatePassword,{data:t}))},delete:function(t){return Object(i["a"])(Object(s["a"])({},r.delete,{data:t}))},reset:function(t){return Object(i["a"])(Object(s["a"])({},r.reset,{data:t}))},avatar:function(t){return Object(i["a"])(Object(s["a"])({},r.avatar,{data:t}))}}},c2cf:function(t,e,a){"use strict";var s=a("9256"),i=a("5657"),r={dictionaryEnums:"/gate/dictionary/enums"};e["a"]={uploadFile:"".concat("http://localhost:8760").concat("/api","/file/attachment/upload"),loadImg:function(t){var e=i["a"].get("TOKEN",""),a=i["a"].get("TENANT","");return"".concat("http://localhost:8760").concat("/api","/file/attachment/download/").concat(t.bizType,"/").concat(t.bizId,"?token=").concat(e,"&tenant=").concat(a)},dictionaryEnums:function(){return Object(s["a"])({method:"GET",url:r.dictionaryEnums})},generateId:function(t){return Object(s["a"])({url:"/authority/common/generateId",method:"GET",data:t})},getAttachment:function(t){return Object(s["a"])({url:"/file/attachment",method:"get",data:t})},deleteAttachment:function(t){return Object(s["a"])({url:"/file/attachment",method:"delete",data:t})},downloadAttachment:function(t){return Object(s["a"])({url:"/file/attachment/download",method:"get",responseType:"blob",data:t})},downloadAttachmentBiz:function(t){return Object(s["a"])({url:"/file/attachment/download/biz",method:"get",responseType:"blob",data:t})}}},d1d3:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{"close-on-click-modal":!1,"close-on-press-escape":!0,title:t.title,type:t.type,visible:t.isVisible,width:t.width,top:"50px"},on:{"update:visible":function(e){t.isVisible=e}}},[a("el-form",{ref:"form",attrs:{model:t.user,rules:t.rules,"label-position":"right","label-width":"100px"}},[a("el-form-item",{attrs:{label:t.$t("table.user.account"),prop:"account"}},[a("el-input",{attrs:{readonly:"add"!==t.type&&"readonly"},model:{value:t.user.account,callback:function(e){t.$set(t.user,"account",e)},expression:"user.account"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.name"),prop:"name"}},[a("el-input",{model:{value:t.user.name,callback:function(e){t.$set(t.user,"name",e)},expression:"user.name"}})],1),t._v(" "),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:"add"===t.type,expression:"type === 'add'"}],attrs:{label:t.$t("table.user.password"),prop:"password"}},[a("el-tooltip",{staticClass:"item",attrs:{content:t.$t("tips.defaultPassword"),effect:"dark",placement:"top-start"}},[a("el-input",{attrs:{type:"password",value:"123456"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.avatar"),prop:"avatar"}},[a("imgUpload",{ref:"imgFileRef",attrs:{accept:t.accept,"accept-size":2097152,"auto-upload":!0,data:t.user.avatar,"file-list":t.imgFileList,"show-file-list":!1,"list-type":"picture-card"},on:{setId:t.setIdAndSubmit}},[a("i",{staticClass:"el-icon-plus"})])],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.orgId"),prop:"orgId"}},[a("treeselect",{staticStyle:{width:"100%"},attrs:{"clear-value-text":t.$t("common.clear"),"load-options":t.loadListOptions,multiple:!1,options:t.orgList,searchable:!0,placeholder:" "},model:{value:t.user.orgId,callback:function(e){t.$set(t.user,"orgId",e)},expression:"user.orgId"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.stationId"),prop:"orgId"}},[a("el-select",{attrs:{loading:t.remoteStationLoading,multiple:!1,filterable:"",placeholder:"请输入关键词"},model:{value:t.user.stationId,callback:function(e){t.$set(t.user,"stationId",e)},expression:"user.stationId"}},t._l(t.stationList,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.email"),prop:"email"}},[a("el-input",{model:{value:t.user.email,callback:function(e){t.$set(t.user,"email",e)},expression:"user.email"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.mobile"),prop:"mobile"}},[a("el-input",{model:{value:t.user.mobile,callback:function(e){t.$set(t.user,"mobile",e)},expression:"user.mobile"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.sex"),prop:"sex"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"",value:""},model:{value:t.user.sex.code,callback:function(e){t.$set(t.user.sex,"code",e)},expression:"user.sex.code"}},t._l(t.enums.Sex,(function(t,e,s){return a("el-option",{key:s,attrs:{label:t,value:e}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.status"),prop:"status"}},[a("el-radio-group",{model:{value:t.user.status,callback:function(e){t.$set(t.user,"status",e)},expression:"user.status"}},[a("el-radio",{attrs:{label:!0}},[t._v(t._s(t.$t("common.status.valid")))]),t._v(" "),a("el-radio",{attrs:{label:!1}},[t._v(t._s(t.$t("common.status.invalid")))])],1)],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("table.user.workDescribe"),prop:"workDescribe"}},[a("el-input",{model:{value:t.user.workDescribe,callback:function(e){t.$set(t.user,"workDescribe",e)},expression:"user.workDescribe"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{plain:"",type:"warning"},on:{click:function(e){t.isVisible=!1}}},[t._v(t._s(t.$t("common.cancel")))]),t._v(" "),a("el-button",{attrs:{plain:"",type:"primary"},on:{click:t.submitForm}},[t._v(t._s(t.$t("common.confirm")))])],1)],1)},i=[],r=a("db72"),o=(a("f559"),a("b170")),n=a("7026"),l=a.n(n),u=(a("542c"),a("8f4f")),c=a("9fb8"),d=a("2d64"),m={name:"UserEdit",components:{Treeselect:l.a,imgUpload:u["a"]},props:{dialogVisible:{type:Boolean,default:!1},type:{type:String,default:"add"}},data:function(){var t=this;return{accept:"image/jpeg, image/gif, image/png",remoteStationLoading:!1,user:this.initUser(),screenWidth:0,width:this.initWidth(),orgList:[],stationList:[],imgFileList:[],imgFileData:{bizId:"",bizType:"USER_AVATAR"},imgFileTotal:0,successNum:0,rules:{username:[{required:!0,message:this.$t("rules.require"),trigger:"blur"},{min:4,max:10,message:this.$t("rules.range4to10"),trigger:"blur"},{validator:function(e,a,s){t.user.userId?s():t.$get("system/user/check/".concat(a)).then((function(e){var a=e.data;a.data?s():s(t.$t("rules.usernameExist"))}))},trigger:"blur"}],email:{type:"email",message:this.$t("rules.email"),trigger:"blur"},mobile:{validator:function(e,a,s){""===a||Object(o["e"])(a)?s():s(t.$t("rules.mobile"))},trigger:"blur"},roleId:{required:!0,message:this.$t("rules.require"),trigger:"change"},sex:{required:!0,message:this.$t("rules.require"),trigger:"change"},status:{required:!0,message:this.$t("rules.require"),trigger:"blur"}}}},computed:{isVisible:{get:function(){return this.dialogVisible},set:function(){this.close(),this.reset()}},enums:function(){return this.$store.state.common.enums},title:function(){return"add"===this.type?this.$t("common.add"):this.$t("common.edit")}},watch:{"user.orgId":"orgSelect"},mounted:function(){var t=this;window.onresize=function(){return function(){t.width=t.initWidth()}()}},methods:{myAvatar:function(t){return t?t.startsWith("http://")||t.startsWith("https://")?t:a("a0fc")("./".concat(t)):a("fd89")},initUser:function(){return{id:"",account:"",name:"",orgId:null,stationId:null,email:"",mobile:"",sex:{code:"N"},status:!0,avatar:"",workDescribe:"",password:"123456"}},initWidth:function(){return this.screenWidth=document.body.clientWidth,this.screenWidth<991?"90%":this.screenWidth<1400?"45%":"800px"},loadListOptions:function(t){var e=t.callback;e()},orgSelect:function(t){this.loadStation(t)},loadStation:function(t){var e=this;t?d["a"].page({orgId:t,status:!0}).then((function(t){var a=t.data;e.stationList=a.data.records})):this.stationList=[]},setIdAndSubmit:function(t,e){var a=this;a.successNum+=1,a.imgFileData.bizId=t,a.user.avatar=e,a.user.id=t,a.successNum===a.imgFileTotal&&(a.$store.state.hasLoading=!1)},setUser:function(t,e){var a=this;t&&(a.user=Object(r["a"])({},t)),a.orgList=e,a.imgFileData.bizId=a.user["id"],a.$nextTick((function(){a.$refs.imgFileRef.init({bizId:a.user["id"],bizType:a.imgFileData.bizType,imageUrl:a.myAvatar(a.user["avatar"]),isSingle:!0,isDetail:!1})}))},close:function(){this.$emit("close")},reset:function(){this.$refs.form.clearValidate(),this.$refs.form.resetFields(),this.user=this.initUser(),this.imgFileData.bizId="",this.$refs.imgFileRef.init({bizId:"",bizType:"",imageUrl:"",isSingle:!0,isDetail:!1})},submitForm:function(){var t=this;this.$refs.form.validate((function(e){if(!e)return!1;t.editSubmit()}))},editSubmit:function(){var t=this;"add"===t.type?t.save():t.update()},save:function(){var t=this;c["a"].save(this.user).then((function(e){var a=e.data;a.isSuccess&&(t.isVisible=!1,t.$message({message:t.$t("tips.createSuccess"),type:"success"}),t.$emit("success"))}))},update:function(){var t=this;c["a"].update(this.user).then((function(e){var a=e.data;a.isSuccess&&(t.isVisible=!1,t.$message({message:t.$t("tips.updateSuccess"),type:"success"}),t.$emit("success"))}))}}},p=m,b=a("2877"),f=Object(b["a"])(p,s,i,!1,null,"efcd69a2",null);e["default"]=f.exports}}]);