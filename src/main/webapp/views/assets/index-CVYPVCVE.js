import{u as B}from"./index-6PsREaeB.js";import{f as F}from"./category-B8soOlq5.js";import{f as L,a as N,b as D}from"./product-B97NJueJ.js";import{_ as $}from"./product-card.vue_vue_type_script_setup_true_lang-VeBBTyg9.js";import{d as M,a as g,B as A,b as H,o as O,w as T,u as e,c as f,e as a,f as t,G as i,C as U,g as j,h as n,E as c,p as y,l as m,s as q,F as v,D as E,j as G,m as R,O as w,M as S,N as z,v as J,L as K}from"./index-D8xB8DU9.js";const ee=M({__name:"index",setup(P){const{bool:b,toggle:r}=B(),p=g([]),k=async()=>{const s=await F();p.value=s.list||[]},C=A(()=>p.value.reduce((s,o)=>(s[`${o.id}`]=o.name,s),{})),u=H({}),d=g([]),_=async()=>{r();const s=await L(u).finally(()=>r());d.value=s.list||[]},h=async()=>{r();const s=await N().finally(()=>r());d.value=s.list||[]},x=async()=>{r();const s=await D().finally(()=>r());d.value=s.list||[]};return O(()=>{k(),_()}),(s,o)=>{const V=U("el-button"),I=j("loading");return T((n(),f("div",null,[a(e(R),{inline:""},{default:t(()=>[a(e(c),null,{default:t(()=>[a(e(y),{type:"danger",class:"pointer",onClick:h},{default:t(()=>[m("热门商品")]),_:1})]),_:1}),a(e(c),null,{default:t(()=>[a(e(y),{type:"danger",class:"pointer",onClick:x},{default:t(()=>[m("最新商品")]),_:1})]),_:1}),a(e(c),{prop:"categoryId"},{default:t(()=>[a(e(q),{modelValue:u.categoryId,"onUpdate:modelValue":o[0]||(o[0]=l=>u.categoryId=l),placeholder:"商品分类",style:{width:"200px"},clearable:"",filterable:""},{default:t(()=>[(n(!0),f(v,null,E(p.value,l=>(n(),i(e(J),{key:l.id,label:`${l.parentId?C.value[l.parentId]+"-"+l.name:l.name}`,value:l.id},null,8,["label","value"]))),128))]),_:1},8,["modelValue"])]),_:1}),a(e(c),{prop:"name"},{default:t(()=>[a(e(G),{modelValue:u.name,"onUpdate:modelValue":o[1]||(o[1]=l=>u.name=l),placeholder:"商品名称",clearable:""},null,8,["modelValue"])]),_:1}),a(e(c),null,{default:t(()=>[a(V,{type:"primary",onClick:_},{default:t(()=>[m("查询")]),_:1})]),_:1})]),_:1}),a(e(w)),d.value.length?(n(),i(e(S),{key:0,class:"w-full"},{default:t(()=>[(n(!0),f(v,null,E(d.value,l=>(n(),i(e(K),{key:l.id,xl:3,lg:4,md:6,sm:8,xs:12,span:24},{default:t(()=>[a(e($),{data:l},null,8,["data"])]),_:2},1024))),128))]),_:1})):(n(),i(e(z),{key:1})),a(e(w))])),[[I,e(b)]])}}});export{ee as default};
