webpackJsonp([1],{"+MeO":function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("p",{staticClass:"vux-divider"},[t._t("default")],2)},staticRenderFns:[]}},"+kMp":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={name:"flexbox",props:{gutter:{type:Number,default:8},orient:{type:String,default:"horizontal"},justify:String,align:String,wrap:String,direction:String},computed:{styles:function(){return{"justify-content":this.justify,"-webkit-justify-content":this.justify,"align-items":this.align,"-webkit-align-items":this.align,"flex-wrap":this.wrap,"-webkit-flex-wrap":this.wrap,"flex-direction":this.direction,"-webkit-flex-direction":this.direction}}}}},"/JLp":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={name:"box",props:{gap:String}}},"0LiP":function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("div",{staticClass:"weui-cells__title"},[t._t("default")],2)},staticRenderFns:[]}},"1HIy":function(t,e,i){i("n3mJ");var n=i("VU/8")(i("QiPk"),i("0LiP"),null,null);t.exports=n.exports},"2sLL":function(t,e,i){i("Xhe+");var n=i("VU/8")(i("TCOM"),i("hsmS"),null,null);t.exports=n.exports},"3Lt7":function(t,e,i){var n=i("VU/8")(i("4rCc"),i("4gt1"),null,null);t.exports=n.exports},"3d+v":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={name:"divider"}},"4gt1":function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("div",{staticClass:"vux-flexbox-item",style:t.style},[t._t("default")],2)},staticRenderFns:[]}},"4rCc":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=["-moz-box-","-webkit-box-",""];e.default={name:"flexbox-item",props:{span:[Number,String],order:[Number,String]},beforeMount:function(){this.bodyWidth=document.documentElement.offsetWidth},methods:{buildWidth:function(t){return"number"==typeof t?t<1?t:t/12:"string"==typeof t?t.replace("px","")/this.bodyWidth:void 0}},computed:{style:function(){var t={},e="horizontal"===this.$parent.orient?"marginLeft":"marginTop";if(1*this.$parent.gutter!=0&&(t[e]=this.$parent.gutter+"px"),this.span)for(var i=0;i<n.length;i++)t[n[i]+"flex"]="0 0 "+100*this.buildWidth(this.span)+"%";return void 0!==this.order&&(t.order=this.order),t}},data:function(){return{bodyWidth:0}}}},"904R":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i("7+uW"),r=i("QXHr"),s=i.n(r);new n.a({render:function(t){return t(s.a)}}).$mount("#app")},QTi7:function(t,e,i){var n=i("VU/8")(i("/JLp"),i("mJJ4"),null,null);t.exports=n.exports},QXHr:function(t,e,i){i("rE1t");var n=i("VU/8")(i("uOco"),i("u2v0"),null,null);t.exports=n.exports},QiPk:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={name:"group-title"}},TCOM:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i("0FxO");e.default={name:"x-button",props:{type:{default:"default"},disabled:Boolean,mini:Boolean,plain:Boolean,text:String,actionType:String,showLoading:Boolean,link:[String,Object],gradients:{type:Array,validator:function(t){return 2===t.length}}},methods:{onClick:function(){!this.disabled&&Object(n.a)(this.link,this.$router)}},computed:{noBorder:function(){return Array.isArray(this.gradients)},buttonStyle:function(){if(this.gradients)return{background:"linear-gradient(90deg, "+this.gradients[0]+", "+this.gradients[1]+")",color:"#FFFFFF"}},classes:function(){return[{"weui-btn_disabled":!this.plain&&this.disabled,"weui-btn_plain-disabled":this.plain&&this.disabled,"weui-btn_mini":this.mini,"vux-x-button-no-border":this.noBorder},this.plain?"":"weui-btn_"+this.type,this.plain?"weui-btn_plain-"+this.type:"",this.showLoading?"weui-btn_loading":""]}}}},"Xhe+":function(t,e){},YxJB:function(t,e,i){i("mdOz");var n=i("VU/8")(i("+kMp"),i("mjtm"),null,null);t.exports=n.exports},fuQw:function(t,e){},hsmS:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("button",{staticClass:"weui-btn",class:t.classes,style:t.buttonStyle,attrs:{disabled:t.disabled,type:t.actionType},on:{click:t.onClick}},[t.showLoading?i("i",{staticClass:"weui-loading"}):t._e(),t._v(" "),t._t("default",[t._v(t._s(t.text))])],2)},staticRenderFns:[]}},mJJ4:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("div",{style:{margin:t.gap}},[t._t("default")],2)},staticRenderFns:[]}},mdOz:function(t,e){},mjtm:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("div",{staticClass:"vux-flexbox",class:{"vux-flex-col":"vertical"===t.orient,"vux-flex-row":"horizontal"===t.orient},style:t.styles},[t._t("default")],2)},staticRenderFns:[]}},n3mJ:function(t,e){},rE1t:function(t,e){},rGqP:function(t,e,i){i("fuQw");var n=i("VU/8")(i("3d+v"),i("+MeO"),null,null);t.exports=n.exports},u2v0:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"mainWarp"},[i("hb-head",{attrs:{headfont:"button按钮"}}),t._v(" "),i("divider",[t._v("default")]),t._v(" "),i("box",{attrs:{gap:"10px 10px"}},[i("x-button",[t._v("submit")]),t._v(" "),i("x-button",{attrs:{type:"primary"}},[t._v("primary")]),t._v(" "),i("x-button",{attrs:{type:"warn"}},[t._v("Delete")]),t._v(" "),i("divider",[t._v("action type")]),t._v(" "),i("x-button",{attrs:{type:"primary","action-type":"button"}},[t._v("submit")]),t._v(" "),i("x-button",{attrs:{type:"warn","action-type":"reset"}},[t._v("reset")]),t._v(" "),i("divider",[t._v("loading")]),t._v(" "),i("x-button",{attrs:{type:"default","show-loading":""}},[t._v("submit")]),t._v(" "),i("x-button",{attrs:{type:"primary","show-loading":""}},[t._v("submit")]),t._v(" "),i("x-button",{attrs:{type:"warn","show-loading":""}},[t._v("submit")]),t._v(" "),i("divider",[t._v("mini")]),t._v(" "),i("x-button",{attrs:{mini:""}},[t._v("submit")]),t._v(" "),i("x-button",{attrs:{mini:"",type:"primary"}},[t._v("primary")]),t._v(" "),i("x-button",{attrs:{mini:"",type:"warn"}},[t._v("Delete")]),t._v(" "),i("br"),t._v(" "),i("x-button",{attrs:{mini:"",plain:""}},[t._v("submit")]),t._v(" "),i("x-button",{attrs:{mini:"",plain:"",type:"primary"}},[t._v("primary")]),t._v(" "),i("divider",[t._v("plain")]),t._v(" "),i("x-button",{attrs:{plain:""}},[t._v("submit")]),t._v(" "),i("x-button",{attrs:{plain:"",type:"primary"}},[t._v("primary")]),t._v(" "),i("divider",[t._v("you can custom styles")]),t._v(" "),i("x-button",{staticStyle:{"border-radius":"99px"},attrs:{plain:"",type:"primary"}},[t._v("primary")]),t._v(" "),i("x-button",{staticClass:"custom-primary-red",attrs:{plain:"",type:"primary"}},[t._v("primary")]),t._v(" "),i("divider",[t._v("disabled")]),t._v(" "),i("x-button",{attrs:{disabled:""}},[t._v("disable submit")]),t._v(" "),i("x-button",{attrs:{type:"primary",disabled:""}},[t._v("disable primary")]),t._v(" "),i("x-button",{attrs:{type:"warn",disabled:""}},[t._v("disable Delete")]),t._v(" "),i("divider",[t._v("use :text and :disabled")]),t._v(" "),i("x-button",{attrs:{text:t.submit001,disabled:t.disable001,type:"primary"},nativeOn:{click:function(e){return t.processButton001(e)}}}),t._v(" "),i("divider",[t._v("combined with flexbox")]),t._v(" "),i("flexbox",[i("flexbox-item",[i("x-button",{attrs:{type:"primary"}},[t._v("primary")])],1),t._v(" "),i("flexbox-item",[i("x-button",{attrs:{type:"warn"}},[t._v("Delete")])],1)],1),t._v(" "),i("divider",[t._v("combined with flexbox")]),t._v(" "),i("flexbox",[i("flexbox-item",[i("x-button",{attrs:{type:"default"}},[t._v("default")])],1),t._v(" "),i("flexbox-item",[i("x-button",{attrs:{type:"primary"}},[t._v("primary")])],1),t._v(" "),i("flexbox-item",[i("x-button",{attrs:{type:"warn"}},[t._v("Delete")])],1)],1)],1)],1)},staticRenderFns:[]}},uOco:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=(i("JJgx"),i("2sLL")),r=i.n(n),s=i("QTi7"),a=i.n(s),o=i("1HIy"),u=i.n(o),l=i("rHil"),d=i.n(l),c=i("YxJB"),p=i.n(c),v=i("3Lt7"),b=i.n(v),f=i("rGqP"),_=i.n(f),x=i("tRiV"),m=i.n(x);e.default={components:{XButton:r.a,Box:a.a,GroupTitle:u.a,Group:d.a,Flexbox:p.a,FlexboxItem:b.a,Divider:_.a,HbHead:m.a},data:function(){return{submit001:"click me",disable001:!1}},methods:{change:function(t){console.log("change:",t)},processButton001:function(){this.submit001="processing",this.disable001=!0}}}}},["904R"]);