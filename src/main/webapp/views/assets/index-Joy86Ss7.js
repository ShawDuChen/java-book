import{l as g}from"./auth-BJgEvMpP.js";import{d as h,a as _,_ as U,o as k,$ as C,c as R,e,f as o,P as B,C as s,h as N,l as u,x as q,u as f,a0 as c,a1 as L}from"./index-D8xB8DU9.js";const D={class:"container flex-center"},E={class:"flex-between"},T=h({__name:"index",setup(M){const p=_(),a=_({username:"",password:"",type:1}),i=B(),v=U(),V=()=>{var d;(d=p.value)==null||d.validate(t=>{if(t){const r=a.value;g(r).then(l=>{v.updateUser(l.data),L(l.data),i.push(r.type===1?"/user":"/c/dashboard")})}})};return k(()=>{C()}),(d,t)=>{const r=s("el-input"),l=s("el-form-item"),m=s("el-radio"),b=s("el-radio-group"),y=s("el-button"),w=s("el-form"),x=s("el-card");return N(),R("div",D,[e(x,{header:"购物系统登录",style:{width:"420px"}},{default:o(()=>[e(w,{ref_key:"formRef",ref:p,model:a.value,"label-position":"top"},{default:o(()=>[e(l,{prop:"username",label:"用户名",rules:[{required:!0,message:"请输入用户名"}]},{default:o(()=>[e(r,{modelValue:a.value.username,"onUpdate:modelValue":t[0]||(t[0]=n=>a.value.username=n),placeholder:"请输入用户名",autocomplete:"off",clearable:""},null,8,["modelValue"])]),_:1}),e(l,{prop:"password",label:"用户密码",rules:[{required:!0,message:"请输入用户密码"}]},{default:o(()=>[e(r,{modelValue:a.value.password,"onUpdate:modelValue":t[1]||(t[1]=n=>a.value.password=n),type:"password",autocomplete:"off",clearable:""},null,8,["modelValue"])]),_:1}),e(l,{prop:"type",label:"登录平台"},{default:o(()=>[e(b,{modelValue:a.value.type,"onUpdate:modelValue":t[2]||(t[2]=n=>a.value.type=n)},{default:o(()=>[e(m,{value:1},{default:o(()=>[u("管理后台")]),_:1}),e(m,{value:2},{default:o(()=>[u("购物平台")]),_:1})]),_:1},8,["modelValue"])]),_:1}),e(l,null,{default:o(()=>[e(y,{type:"primary",class:"w-full",onClick:V},{default:o(()=>[u("登录")]),_:1})]),_:1}),q("div",E,[e(f(c),{to:"/forgot"},{default:o(()=>[u("忘记密码？")]),_:1}),e(f(c),{to:"/register"},{default:o(()=>[u("注册账号")]),_:1})])]),_:1},8,["model"])]),_:1})])}}});export{T as default};
