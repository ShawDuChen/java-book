import{r as u,d as E,b as x,g as z,w as r,u as e,h,G as v,f as n,x as a,t as d,V as B,e as l,T as I,P as p,W as N,H as T,X as A,k as y,Y as D,l as i,p as _,Q as S,J as U,I as q,A as F}from"./index-a7oZ5LST.js";import{u as G}from"./index-BJJCNVtC.js";const X=s=>u({url:"/cart/all",method:"get",params:{userId:s}}),H=s=>u({url:"/cart/create",method:"post",data:{model:s}}),Y=s=>u({url:"/cart/update",method:"post",data:{model:s}}),j=s=>u({url:"/cart/delete",method:"get",params:{id:s}}),J={class:"flex-between"},M={style:{"font-size":"20px","font-weight":"bold"}},P={class:"flex-between",style:{"margin-top":"6px"}},K=E({__name:"product-card",props:{data:{},onlyshow:{type:Boolean}},setup(s){const C=s,{bool:w,toggle:m}=G(),t=x({count:1,edit:!1}),k=()=>{t.edit=!0,t.count=1},f=()=>{t.edit=!1,t.count=1},V=async()=>{m(),await H({productId:C.data.id,userId:q().id,count:t.count}).finally(()=>m()),F.success("添加成功"),f()};return(o,g)=>{const b=z("loading");return r((h(),v(e(U),null,{header:n(()=>[a("div",J,[a("span",M,d(o.data.name),1),o.onlyshow?N("",!0):r((h(),v(e(B),{key:0,color:"#f56c6c",size:20,class:"pointer",onClick:k},{default:n(()=>[l(e(I))]),_:1},512)),[[p,!t.edit]])]),r(a("div",P,[a("div",null,[l(e(T),{modelValue:t.count,"onUpdate:modelValue":g[0]||(g[0]=c=>t.count=c),size:"small",min:1,max:999},null,8,["modelValue"])]),a("div",null,[l(e(y),{type:"danger",circle:"",icon:e(A),size:"small",onClick:f},null,8,["icon"]),l(e(y),{type:"success",circle:"",icon:e(D),size:"small",onClick:V},null,8,["icon"])])],512),[[p,t.edit]])]),default:n(()=>[l(e(S),{direction:"vertical",alignment:"start"},{default:n(()=>{var c;return[a("div",null,"商品描述: "+d(o.data.description),1),a("div",null,"商品分类: "+d(((c=o.data.category)==null?void 0:c.name)||"-"),1),a("div",null,[i(" 商品销量: "),l(e(_),{type:"success"},{default:n(()=>[i(d(o.data.sellCount||0),1)]),_:1})]),a("div",null,[i("商品单价: "),l(e(_),{type:"danger"},{default:n(()=>[i("￥"+d(o.data.price||0),1)]),_:1})])]}),_:1}),r(a("div",{class:"text-right",style:{"font-size":"24px",color:"#f56c6c"}},"￥"+d((o.data.price*t.count).toFixed(2)),513),[[p,t.edit]])]),_:1})),[[b,e(w)]])}}});export{K as _,j as d,X as f,Y as u};
