import{f as C}from"./shopOrder-DPpOEaSa.js";import{u as w}from"./index-BJJCNVtC.js";import{d as A,I as B,a as D,o as T,w as E,u as a,c as g,e as t,f as o,O as $,C as m,g as I,h as d,n as s,l as p,t as u,G as N,q as V}from"./index-a7oZ5LST.js";import{_ as q}from"./order-product.vue_vue_type_script_setup_true_lang-5harAGAA.js";const F={key:0},S=A({__name:"index",setup(G){const{bool:h,toggle:_}=w(),b=B(),f=D([]),x=()=>{_(),C(b.id).then(l=>{f.value=l.list||[]}).finally(()=>_())},y=l=>{var i;return((i=l.carts)==null?void 0:i.reduce((n,c)=>{var r;return n+=n+(((r=c.product)==null?void 0:r.price)||0)*c.count,n},0).toFixed(2))??0},k=$(),v=l=>{k.push(`/c/pay?id=${l.id}`)};return T(()=>{x()}),(l,i)=>{const n=m("el-tag"),c=m("el-button"),r=I("loading");return E((d(),g("div",null,[t(a(V),{data:f.value,border:""},{default:o(()=>[t(a(s),{prop:"id",label:"订单ID",align:"center",width:"80px"}),t(a(s),{prop:"carts",label:"购买商品",align:"center"},{default:o(({row:e})=>[t(a(q),{data:e.carts},null,8,["data"])]),_:1}),t(a(s),{prop:"total",label:"总价",align:"center",width:"120px"},{default:o(({row:e})=>[t(n,{type:"danger"},{default:o(()=>[p("￥"+u(y(e)),1)]),_:2},1024)]),_:1}),t(a(s),{prop:"paid",label:"已付款",align:"center",width:"80px"},{default:o(({row:e})=>[t(n,{type:e.paid?"success":"danger"},{default:o(()=>[p(u(e.paid?"是":"否"),1)]),_:2},1032,["type"])]),_:1}),t(a(s),{prop:"createdAt",label:"订单创建时间",align:"center",width:"180px"}),t(a(s),{prop:"updatedAt",label:"付款时间",align:"center",width:"180px"},{default:o(({row:e})=>[e.paid?(d(),g("span",F,u(e.updatedAt),1)):(d(),N(c,{key:1,type:"success",link:"",onClick:L=>v(e)},{default:o(()=>[p("去付款")]),_:2},1032,["onClick"]))]),_:1})]),_:1},8,["data"])])),[[r,a(h)]])}}});export{S as default};
