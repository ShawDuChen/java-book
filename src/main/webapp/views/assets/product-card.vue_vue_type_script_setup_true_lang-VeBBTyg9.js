import{r as p,d as E,b as x,g as z,w as r,u as e,h,G as v,f as n,x as a,t as c,S as B,e as l,R as N,Q as u,T,H as A,U as D,k as y,V as I,l as i,p as _,W as S,K as U,J as q,A as F}from"./index-D8xB8DU9.js";import{u as G}from"./index-6PsREaeB.js";const W=s=>p({url:"/cart/all",method:"get",params:{userId:s}}),H=s=>p({url:"/cart/create",method:"post",data:{model:s}}),j=s=>p({url:"/cart/delete",method:"get",params:{id:s}}),J={class:"flex-between"},K={style:{"font-size":"20px","font-weight":"bold"}},M={class:"flex-between",style:{"margin-top":"6px"}},L=E({__name:"product-card",props:{data:{},onlyshow:{type:Boolean}},setup(s){const C=s,{bool:w,toggle:m}=G(),t=x({count:1,edit:!1}),k=()=>{t.edit=!0,t.count=1},f=()=>{t.edit=!1,t.count=1},V=async()=>{m(),await H({productId:C.data.id,userId:q().id,count:t.count}).finally(()=>m()),F.success("添加成功"),f()};return(o,g)=>{const b=z("loading");return r((h(),v(e(U),null,{header:n(()=>[a("div",J,[a("span",K,c(o.data.name),1),o.onlyshow?T("",!0):r((h(),v(e(B),{key:0,color:"#f56c6c",size:20,class:"pointer",onClick:k},{default:n(()=>[l(e(N))]),_:1},512)),[[u,!t.edit]])]),r(a("div",M,[a("div",null,[l(e(A),{modelValue:t.count,"onUpdate:modelValue":g[0]||(g[0]=d=>t.count=d),size:"small",min:1,max:999},null,8,["modelValue"])]),a("div",null,[l(e(y),{type:"danger",circle:"",icon:e(D),size:"small",onClick:f},null,8,["icon"]),l(e(y),{type:"success",circle:"",icon:e(I),size:"small",onClick:V},null,8,["icon"])])],512),[[u,t.edit]])]),default:n(()=>[l(e(S),{direction:"vertical",alignment:"start"},{default:n(()=>{var d;return[a("div",null,"商品描述: "+c(o.data.description),1),a("div",null,"商品分类: "+c(((d=o.data.category)==null?void 0:d.name)||"-"),1),a("div",null,[i(" 商品销量: "),l(e(_),{type:"success"},{default:n(()=>[i(c(o.data.sellCount||0),1)]),_:1})]),a("div",null,[i("商品单价: "),l(e(_),{type:"danger"},{default:n(()=>[i("￥"+c(o.data.price||0),1)]),_:1})])]}),_:1}),r(a("div",{class:"text-right",style:{"font-size":"24px",color:"#f56c6c"}},"￥"+c((o.data.price*t.count).toFixed(2)),513),[[u,t.edit]])]),_:1})),[[b,e(w)]])}}});export{L as _,j as d,W as f};
