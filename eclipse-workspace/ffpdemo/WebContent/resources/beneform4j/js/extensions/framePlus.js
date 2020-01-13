/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 针对主框架页面的实现的插件 <br>
 * Author : leo yang <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-5<br>
 */
var framePlus={
	    /*************************
	      仿windows开始式快捷菜单
	      create user : leo yang
	      create date : 20160331
	     *************************/
	    pointMenu: {
	        doing: false,
	        _t: null,
	        init: function (jqselector, options) {
	            _t = jqselector;
	            var _this = this;
	            _t.find(" .pointStart").click(function (event) {
	                //左下角开始收缩效果
	                if (!_this.doing) {
	                    _this.doing = true;
	                    var pointM = $(this);
	                    if (pointM.hasClass("point-open")) {
	                        setTimeout(function () { pointM.removeClass("point-open"); }, 300);//等大窗口收缩完
	                    } else {
	                        pointM.addClass("point-open");
	                    }
	                    $(this).next(".pointPanel").css("height", options.height).css("width", options.width).toggle(300, function () {
	                        _this.doing = false;
	                    }).removeClass("bigPanel");
	                }
	                event.stopPropagation();
	
	            });
	
	            $(document).click(function (event) {
	                _this.close();
	                event.stopPropagation();
	            });
	
	            _t.find(".qieicon").click(function (event) {
	                if (_t.find(".pointPanel").hasClass("bigPanel")) {
	                    _t.find(".pointPanel").animate({ height: options.height, width: options.width }).removeClass("bigPanel");
	                } else {
	                	_t.find(".pointPanel").animate({ height: options.maxHeight, width: options.maxWidht }).addClass("bigPanel");
	                }
	                event.stopPropagation();
	            });
	            _t.find(".seticon").click(function (event) {
	            	$b.App.openUrl('common/favorites/search','快捷菜单设置',{targetPage : 'tab',path : '快捷菜单设置',menuId:'900000206'});
	            	_this.close();
	            	event.stopPropagation();
	            });
	            _t.click(function (event) { event.stopPropagation(); });
	        },
	        close: function () {
	        	var _this = this;
            	if(_this.doing){return;}
	        	try{
	        		//左下角开始收缩效果
	        		_this.doing = true;
	        		_t.find(".pointPanel").animate({ height: 0, width: 0 }, function () {
	        			_t.find(".pointStart").removeClass("point-open");
	        			_this.doing=false;
	        			$(this).hide();
	        		});
	        	}catch (e) {
	        	}
	        }
	    }
	  ,
	    /*************************
	      顶部树形浮动菜单对象
	      create user : leo yang
	      create date : 20160331
	    *************************/
	    topTree: {
	    	//默认参数
	    	options:{ data: [],contextPath:"",nodeClick:function(){}},
	        //jqselector:父ul容器
	        //options   :扩展参数
	        init: function (_jqselector, _options) {
	            var _t = _jqselector;
	            var _this = this;
	            
	            if (_options && _options != undefined) {
	                $.extend(_this.options, _options);
	            }
	            CreateHtml(_this.options.data);
	            $(window).resize(function () {
	                AutoWidth();
	            });
	        	
	            /***根据json集合创建html***/
	            function CreateHtml(jsonList) {
	
	                var $Html = "";
	                var getChildrenLen = function (children) {
	                    if (children && children != undefined) { return children.length; }
	                    return 0;
	                };
	
	                //递归获取子项html
	                var getItemHtml = function (jsonList) {
	                    var $ul = "";
	                    if (jsonList != undefined && jsonList.length > 0) {
	                        $ul = "<ul class=\"twolevel\" >";
	                        $.each(jsonList, function (i, s) {
	                            var $lefti = "<i class=\"" + s.iconCls + "\" ></i>";
	                            var $cspan = "<span>" + s.text + "</span>";
	                            var $righti = "<i class=\"none\" ></i>";
	                            var $items = "";
	                            var $clickfun="";
	                            if (getChildrenLen(s.children) > 0) {
	                                $righti = "<i class=\"sicon-right\" ></i>";
	                            }else{
	                            	$clickfun="onclick='framePlus.topTree.nodeClick(this,"+JSON.stringify(s)+")'";
	                            }
	                            var $disablestr="";
	                            if(s.attributes.disable=="true"){
	                            	$disablestr="class='disable'";
	                            }
	                            $ul += "<li "+$disablestr+" ><a "+$clickfun+"  >" + $lefti + $cspan + $righti + "</a>" + getItemHtml(s.children) + "</li>";
	                        });
	                        $ul += "</ul>";
	                    }
	                    return $ul;
	                };
	
	                //拼接一级html
	                $.each(jsonList, function (i, s) {
	                    var $lefti = "<i class=\"" + s.iconCls + "\" ></i>";
	                    var $cspan = "<span>" + s.text + "</span>";
	                    var $righti = "<i class=\"none\" ></i>";
	                    var $items = "";
	                    var $clickfun="";
	                    if (getChildrenLen(s.children) > 0) {
	                        $righti = "<i class=\"down\" ></i>";
	                    }else{
	                    	$clickfun="onclick='framePlus.topTree.nodeClick(this,"+JSON.stringify(s)+")'";
	                    }
	                    var $disablestr="";
	                    if(s.attributes.disable=="true"){
	                    	$disablestr="disable";
	                    }
	                    $Html += "<li class=\"onelevel rootmenu "+$disablestr+" \"  ><a  "+$clickfun+" >" + $lefti + $cspan + $righti + "</a>" + getItemHtml(s.children) + "</li>";
	                });
	                if ($Html != "") {
	                    _jqselector.html($Html);
	                }
	                AutoWidth();
	            }
	
	            /***[ 响应式处理 ]***/
	            function AutoWidth() {
	                if (_t.data("OLDTREEHTML") == undefined) {
	                    _t.data("OLDTREEHTML", _t.html());
	                } else {
	                    _t.empty().append(_t.data("OLDTREEHTML"));
	                }
	                //如果li总宽度大于可用宽度
	                if (getLiTotalWidth() > _t.width()) {
	                    var overIndex = getOverffolwLiIndex();
	                    //新增更多menu
	                    _t.append($('<li class="onelevel more-menu"><a href="#"><i class="sicon-more"></i></a><ul class="twolevel" ><li class="replaceMe" ></li><ul></li>'));
	                    //将超出的节点替换到更多menu中
	                    if (overIndex == 0) {
	                        _t.find(".replaceMe").replaceWith(_t.children(".rootmenu").removeClass("onelevel"));
	                    } else {
	                        _t.find(".replaceMe").replaceWith(_t.children("li").eq(overIndex - 1).nextAll(".rootmenu").removeClass("onelevel"));
	                    }
	                }
	                //绑定鼠标事件
	                bindTreeEvent();
	            }
	
	            //绑定根菜单事件
	            function bindTreeEvent() {
	                _t.children(".onelevel").unbind("mousemove").bind("mousemove", function (event) {
	                    if ($(this).children("ul").length > 0) {
	                        bindChildren($(this));//递归绑定
	                        $(this).children("a").addClass("select-hasitem").addClass("root-hover").next("ul").show();
	                    } else {
	                        $(this).children("a").addClass("select").addClass("root-hover");
	                    }
	                    event.stopPropagation();
	                }).unbind("mouseleave").bind("mouseleave", function (i, s) {
	                    $(this).find("a").removeClass("select").removeClass("select-hasitem").removeClass("root-hover").next("ul").hide();
	                });
	            }
	
	            //绑定下级事件
	            function bindChildren(parent) {
	                parent.children("ul").children("li").unbind("mousemove").bind("mousemove", function (event) {
	                    if ($(this).children("ul").length > 0) {
	                        bindChildren($(this));
	                        autoDivPostion($(this).children("ul"));
	                        $(this).children("a").addClass("select-hasitem2").addClass("tree-node-hover").next("ul").show();
	
	                    } else {
	                        $(this).children("a").addClass("select2").addClass("tree-node-hover");
	                    }
	                    event.stopPropagation();
	                }).unbind("mouseleave").bind("mouseleave", function (i, s) {
	                    $(this).find("a").removeClass("select2").removeClass("select-hasitem2").removeClass("tree-node-hover").next("ul").hide();
	                });
	            }
	
	            //浮动层自适应位置(left or right)
	            function autoDivPostion(ulselector) {
	                //如果超出屏幕则往左边显示
	                if (ulselector.offset().left + 178 - $(document).scrollLeft() > document.documentElement.clientWidth) {
	                    ulselector.css("left", "-179px");
	                    ulselector.parent().children("a").children(".sicon-right").attr("class", "sicon-left");
	                    ulselector.parent().children("a").children(".down").attr("class", "sicon-left");
	                }
	                var parentZindex = 30;
	                if (ulselector.parent().css("z-index") != "auto") {
	                    parentZindex = ulselector.parent().css("z-index");
	                }
	                ulselector.css("z-index", (parentZindex + 1));
	            }
	
	            //取得li总个数的宽度之和
	            function getLiTotalWidth() {
	                var width = 0;
	                $.each(_t.children("li"), function (i, s) {
	                    width += parseInt($(s).width());
	                });
	                return width;
	            }
	
	            //取得超屏li索引2
	            function getOverffolwLiIndex() {
	                var index = 0;
	                var width = 30;//给更多留出的空间
	                $.each(_t.children("li"), function (i, s) {
	                    width += parseInt($(s).outerWidth());
	                    if (width >= _t.width()) {
	                        index = i;
	                        return false;
	                    }
	                });
	                return index;
	            }
	        },
	        //点击链接
	        nodeClick:function(selector,node)
	        {
	        	var _this = this;
	        	if(node.attributes.disable=="true"){$b.Msg.warning($b.Base.i18n("operate.nothingRole")); return ;}
	    		if (node && node.attributes.url) {
		        	var url = node.attributes.url;
		        	//如果url第一位字符是"/"，那么代表打开的是本地的资源,否则是跨域资源
					if (url.indexOf('/') == 0) {
						url = _this.options.contextPath + url;
					} 
					if(url.indexOf("http://")==-1&&!node.targetPage)
					{
						$b.Msg.openProgress();
					}
					if(node.targetPage)
					{
						window.open(url);
						return;
					}
					
					// temp start 解决快捷菜单兼容问题
			   		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>',
						t = $('#index_tabs'),
						//TODO 使用属性合并的公共方法
						opts = {
							title : "<span title='"+node.text+"'  path='" + (node.attributes.path||'') + "' menuId='" 
								+ (node.id ||'') + "' menuFlag='$"+(node.id ||'')+"$' style='width: 80px;overflow: hidden;display: block;float: left;boder=0;text-align: center;'>" + node.text + "</span>",
							closable : true,
							iconCls : '',
							content : iframe,
							border : false,
							fit : true
						};
			   		var tabs = t.find(".tabs-title");
					for(var i = 0; i<tabs.length; i++){
						if($(tabs[i]).html().indexOf("$"+node.id+"$")>-1){
							t.tabs('select', i);
							$b.Msg.closeProgress();
							return;
						}
					}
					if(t.tabs('tabs').length > server_consts.maxTabsNum){
						$b.Msg.closeProgress();
						$b.Msg.warning($b.Base.i18n("operate.openTabMax"));
						return false;
					} else {
						t.tabs('add', opts);
					}
			   		// temp end
//			   		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>',
//					t = $('#index_tabs'),
//					//TODO 使用属性合并的公共方法
//					opts = {
//						title : "<span title='"+node.text+"'  path='" + (node.attributes.path||'') + "' menuId='" 
//							+ (node.id ||'') + "' style='width: 80px;overflow: hidden;display: block;float: left;boder=0;text-align: center;'>" + node.text + "</span>",
//						closable : true,
//						iconCls : '',
//						content : iframe,
//						border : false,
//						fit : true
//					};
//					if (t.tabs('exists', opts.title)) {
//						t.tabs('select', opts.title);
//						$b.Msg.closeProgress();
//					} else {
//						if(t.tabs('tabs').length > server_consts.maxTabsNum){
//							parent.$.messager.progress('close');
//							$b.Msg.warning($b.Base.i18n("operate.openTabMax"));
//							return false;
//						} else {
//							t.tabs('add', opts);
//						}
//					}
	        	}
	    		_this.options.nodeClick(selector, node);
	    		
	        }
	    }, //, next plus..
	    /*************************
	      左侧迷你树形菜单
	      create user : leo yang
	      create date : 20160426
	    *************************/
	    miniTree: {
	    	//默认参数
	    	options:{ data: [],contextPath:"",nodeClick:function(){}},
	        //jqselector:父ul容器
	        //options   :扩展参数
	        init: function (_jqselector, _options) {
	            var _t = _jqselector;
	            var _this = this;
	            
	            if (_options && _options != undefined) {
	                $.extend(_this.options, _options);
	            }
	            
	            CreateHtml(_this.options.data);
	            
	            /***根据json集合创建html***/
	            function CreateHtml(jsonList) {
	
	                var $Html = "";
	                var getChildrenLen = function (children) {
	                    if (children && children != undefined) { return children.length; }
	                    return 0;
	                };
	
	                //递归获取子项html
	                var getItemHtml = function (jsonList) {
	                    var $ul = "";
	                    if (jsonList != undefined && jsonList.length > 0) {
	                        $ul = "<ul class=\"twolevel\" >";
	                        $.each(jsonList, function (i, s) {
	                            var $lefti = "<i class=\"" + s.iconCls + "\"  ></i>";
	                            var $cspan = "<span>" + s.text + "</span>";
	                            var $righti = "<i class=\"none\" ></i>";
	                            var $items = "";
	                            var $clickfun="";
	                            if (getChildrenLen(s.children) > 0) {
	                                $righti = "<i class=\"sicon-right\" ></i>";
	                            }else{
	                            	$clickfun="onclick='framePlus.topTree.nodeClick(this,"+JSON.stringify(s)+")'";
	                            }
	                            var $disablestr="";
	                            if(s.attributes.disable=="true"){
	                            	$disablestr="class='disable'";
	                            }
	                            $ul += "<li "+$disablestr+" ><a "+$clickfun+"  >" + $lefti + $cspan + $righti + "</a>" + getItemHtml(s.children) + "</li>";
	                        });
	                        $ul += "</ul>";
	                    }
	                    return $ul;
	                };
	
	                //拼接一级html
	                $.each(jsonList, function (i, s) {
	                    var $lefti = "<i class=\"" + s.iconCls + "\"  ></i>";
	                    var $righti = "<i class=\"none\" ></i>";
	                    var $items = "";
	                    var $clickfun="";
	                    var rootclass="rootmenu-a";
	                    if (getChildrenLen(s.children) > 0) {
	                        $righti = "<i class=\"sicon-right\" ></i>";
	                        rootclass="";
	                    }else{
	                    	$clickfun="onclick='framePlus.topTree.nodeClick(this,"+JSON.stringify(s)+")'";
	                    }
	                    var $disablestr="";
	                    if(s.attributes.disable=="true"){
	                    	$disablestr="disable";
	                    }
	                    $Html += "<li class=\"onelevel rootmenu "+$disablestr+" \"  ><a  class=\""+rootclass+"\" title=\""+s.text+"\" "+$clickfun+" >" + $lefti  + $righti + "</a>" + getItemHtml(s.children) + "</li>";
	                });
	                if ($Html != "") {
	                    _jqselector.html($Html);
	                }
	                _jqselector.find(".rootmenu-a").tooltip({
	                		 position: 'right',
	                		 onShow: function(){
	                			 $(this).tooltip('tip').css({backgroundColor: '#666',borderColor: '#666',color:'#ffffff'});   
	                		}}
	                );
	                bindTreeEvent();
	            }
	
	            //绑定根菜单事件
	            function bindTreeEvent() {
	                _t.children(".onelevel").unbind("mousemove").bind("mousemove", function (event) {
	                    if ($(this).children("ul").length > 0) {
	                        bindChildren($(this));//递归绑定
	                        $(this).children("a").addClass("select-hasitem").addClass("root-hover").next("ul").show();
	                    } else {
	                        $(this).children("a").addClass("select").addClass("root-hover");
	                    }
	                    $(this).children("a").next("ul").css("top",($(this).position().top+82)+"px");
	                    event.stopPropagation();
	                }).unbind("mouseleave").bind("mouseleave", function (i, s) {
	                    $(this).find("a").removeClass("select").removeClass("select-hasitem").removeClass("root-hover").next("ul").hide();
	                });
	            }
	
	            //绑定下级事件
	            function bindChildren(parent) {
	                parent.children("ul").children("li").unbind("mousemove").bind("mousemove", function (event) {
	                    if ($(this).children("ul").length > 0) {
	                        bindChildren($(this));
	                        $(this).children("a").addClass("select-hasitem2").addClass("tree-node-hover").next("ul").show();
	
	                    } else {
	                        $(this).children("a").addClass("select2").addClass("tree-node-hover");
	                    }
	                    $(this).children("a").next("ul").css("top",$(this).position().top+"px");
	                    event.stopPropagation();
	                }).unbind("mouseleave").bind("mouseleave", function (i, s) {
	                    $(this).find("a").removeClass("select2").removeClass("select-hasitem2").removeClass("tree-node-hover").next("ul").hide();
	                });
	            }
	        }
	    }
};

