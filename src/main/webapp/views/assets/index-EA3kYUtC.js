import{r as V,d as S,a as k,b as j,o as z,c as x,w as L,u as e,e as l,f as t,i as O,F as G,g as H,h as q,E as r,j as m,k as f,l as n,m as w,n as d,p as D,t as B,q as J,s as F,v,x as K,y as P,z as Q,A as b}from"./index-D8xB8DU9.js";import{u as A}from"./index-6PsREaeB.js";import{l as W}from"./lodash-BNbUVmZH.js";const X=p=>V({url:"/user/all",method:"get",params:{username:p}}),Y=p=>V({url:"/user/create",method:"post",data:{model:p}}),Z=p=>V({url:"/user/update",method:"post",data:{model:p}}),ee=p=>V({url:"/user/delete",method:"get",params:{id:p}}),le={slot:"footer",class:"text-right"},se=S({__name:"index",setup(p){const C=k([]),{bool:y,toggle:i}=A(),_=j({}),g=()=>{X(_.username).then(u=>{C.value=u.list||[]})},U=u=>{var o;s.value=W.cloneDeep(u),c(),(o=h.value)==null||o.resetFields()},T=u=>{Q.confirm("确定删除吗？","提示",{type:"warning"}).then(()=>{i(),ee(u).then(()=>{b.success("删除成功~"),g()}).finally(()=>i())}).catch(()=>{b.info("取消操作~")})},{bool:E,toggle:c}=A(),s=k({}),h=k(),M=()=>{var u;(u=h.value)==null||u.validate(o=>{o&&(s.value.id?N(s.value):R(s.value))})},N=u=>{i(),Z(u).then(()=>{b.success("更新成功~"),c(),g()}).finally(()=>i())},R=u=>{i(),Y(u).then(()=>{b.success("创建成功~"),c(),g()}).finally(()=>i())};return z(()=>{g()}),(u,o)=>{const $=H("loading");return q(),x(G,null,[L((q(),x("div",null,[l(e(w),{inline:""},{default:t(()=>[l(e(r),{prop:"username"},{default:t(()=>[l(e(m),{modelValue:_.username,"onUpdate:modelValue":o[0]||(o[0]=a=>_.username=a),placeholder:"用户名",clearable:""},null,8,["modelValue"])]),_:1}),l(e(r),null,{default:t(()=>[l(e(f),{type:"primary",onClick:g},{default:t(()=>[n("查询")]),_:1}),l(e(f),{type:"success",onClick:o[1]||(o[1]=a=>U({}))},{default:t(()=>[n("新增")]),_:1})]),_:1})]),_:1}),l(e(J),{data:C.value,border:""},{default:t(()=>[l(e(d),{prop:"username",label:"用户名",align:"center"}),l(e(d),{prop:"password",label:"密码",align:"center"},{default:t(()=>[n("******")]),_:1}),l(e(d),{prop:"phone",label:"手机号",align:"center"}),l(e(d),{prop:"email",label:"邮箱",align:"center"}),l(e(d),{prop:"address",label:"住址",align:"center"}),l(e(d),{prop:"gender",label:"性别",align:"center"},{default:t(({row:a})=>[l(e(D),{type:a.gender===1?"primary":"danger"},{default:t(()=>[n(B(a.gender===1?"男":"女"),1)]),_:2},1032,["type"])]),_:1}),l(e(d),{prop:"status",label:"状态",align:"center"},{default:t(({row:a})=>[l(e(D),{type:a.status===1?"success":"danger"},{default:t(()=>[n(B(a.status===1?"正常":"禁用"),1)]),_:2},1032,["type"])]),_:1}),l(e(d),{prop:"createdAt",label:"创建时间",align:"center"}),l(e(d),{prop:"updatedAt",label:"更新时间",align:"center"}),l(e(d),{prop:"action",label:"操作",align:"center","min-width":"120px"},{default:t(({row:a})=>[l(e(f),{type:"primary",link:"",onClick:I=>U(a)},{default:t(()=>[n("编辑")]),_:2},1032,["onClick"]),l(e(f),{type:"danger",link:"",onClick:I=>T(a.id)},{default:t(()=>[n("删除")]),_:2},1032,["onClick"])]),_:1})]),_:1},8,["data"])])),[[$,e(y)]]),l(e(P),{modelValue:e(E),"onUpdate:modelValue":o[9]||(o[9]=a=>O(E)?E.value=a:null),title:s.value.id?"编辑用户":"添加用户",width:"30%"},{default:t(()=>[l(e(w),{ref_key:"formRef",ref:h,model:s.value,"label-position":"top"},{default:t(()=>[l(e(r),{prop:"username",label:"用户名",rules:[{required:!0,message:"请输入用户名"}]},{default:t(()=>[l(e(m),{modelValue:s.value.username,"onUpdate:modelValue":o[2]||(o[2]=a=>s.value.username=a),disabled:!!s.value.id,placeholder:"请输入用户名"},null,8,["modelValue","disabled"])]),_:1}),l(e(r),{prop:"password",label:"密码",rules:[{required:!0,message:"请输入密码"}]},{default:t(()=>[l(e(m),{modelValue:s.value.password,"onUpdate:modelValue":o[3]||(o[3]=a=>s.value.password=a),type:"password",disabled:!!s.value.id,placeholder:"请输入密码"},null,8,["modelValue","disabled"])]),_:1}),l(e(r),{prop:"phone",label:"手机号",rules:[{required:!0,message:"请输入手机号"}]},{default:t(()=>[l(e(m),{modelValue:s.value.phone,"onUpdate:modelValue":o[4]||(o[4]=a=>s.value.phone=a),placeholder:"请输入手机号",maxlength:11},null,8,["modelValue"])]),_:1}),l(e(r),{prop:"email",label:"邮箱",rules:[{required:!0,type:"email",message:"请输入邮箱"}]},{default:t(()=>[l(e(m),{modelValue:s.value.email,"onUpdate:modelValue":o[5]||(o[5]=a=>s.value.email=a),placeholder:"请输入邮箱"},null,8,["modelValue"])]),_:1}),l(e(r),{prop:"gender",label:"性别",rules:[{required:!0,type:"number",message:"请选择性别"}]},{default:t(()=>[l(e(F),{modelValue:s.value.gender,"onUpdate:modelValue":o[6]||(o[6]=a=>s.value.gender=a),placeholder:"请选择性别"},{default:t(()=>[l(e(v),{label:"男",value:1}),l(e(v),{label:"女",value:0})]),_:1},8,["modelValue"])]),_:1}),l(e(r),{prop:"address",label:"住址"},{default:t(()=>[l(e(m),{modelValue:s.value.address,"onUpdate:modelValue":o[7]||(o[7]=a=>s.value.address=a),placeholder:"请输入住址"},null,8,["modelValue"])]),_:1}),l(e(r),{prop:"status",label:"状态"},{default:t(()=>[l(e(F),{modelValue:s.value.status,"onUpdate:modelValue":o[8]||(o[8]=a=>s.value.status=a),placeholder:"请选择状态"},{default:t(()=>[l(e(v),{label:"正常",value:1}),l(e(v),{label:"禁用",value:0})]),_:1},8,["modelValue"])]),_:1})]),_:1},8,["model"]),K("div",le,[l(e(f),{loading:e(y),onClick:e(c)},{default:t(()=>[n("取 消")]),_:1},8,["loading","onClick"]),l(e(f),{loading:e(y),type:"primary",onClick:M},{default:t(()=>[n("确 定")]),_:1},8,["loading"])])]),_:1},8,["modelValue","title"])],64)}}});export{se as default};
