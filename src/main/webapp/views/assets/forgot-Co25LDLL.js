import{u as b}from"./index-7XaKpovQ.js";import{v as x}from"./auth-CqcSMgfa.js";import{d as w,r as i,w as y,u as k,c as B,b as e,e as a,G as C,B as n,f as D,g as R,k as q,z as E}from"./index-CTdGs5XT.js";const N={class:"container flex-center"},M=w({__name:"forgot",setup(U){const{bool:c,toggle:s}=b(),u=i(),o=i({username:"",phone:""}),p=()=>{var l;(l=u.value)==null||l.validate(t=>{t&&_()})},f=C(),_=()=>{s(),x(o.value).then(({data:l})=>{s(),E.success("验证已通过"),f.push(`/reset?id=${l.id}&username=${l.username}`)}).finally(()=>s())};return(l,t)=>{const m=n("el-input"),d=n("el-form-item"),v=n("el-button"),g=n("el-form"),h=n("el-card"),V=D("loading");return y((R(),B("div",N,[e(h,{header:"找回密码",style:{width:"420px"}},{default:a(()=>[e(g,{ref_key:"formRef",ref:u,model:o.value,"label-position":"top"},{default:a(()=>[e(d,{prop:"username",label:"用户名",rules:[{required:!0,message:"请输入用户名"}]},{default:a(()=>[e(m,{modelValue:o.value.username,"onUpdate:modelValue":t[0]||(t[0]=r=>o.value.username=r),placeholder:"请输入用户名"},null,8,["modelValue"])]),_:1}),e(d,{prop:"phone",label:"手机号",rules:[{required:!0,message:"请输入手机号"}]},{default:a(()=>[e(m,{modelValue:o.value.phone,"onUpdate:modelValue":t[1]||(t[1]=r=>o.value.phone=r),placeholder:"请输入预留的手机号",maxlength:11},null,8,["modelValue"])]),_:1}),e(v,{type:"primary",class:"w-full",onClick:p},{default:a(()=>[q("找回密码")]),_:1})]),_:1},8,["model"])]),_:1})])),[[V,k(c)]])}}});export{M as default};
