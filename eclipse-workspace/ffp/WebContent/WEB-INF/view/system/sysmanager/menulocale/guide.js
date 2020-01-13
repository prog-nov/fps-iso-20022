/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单国际化指引维护<br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-25<br> 
 */
require(['beneform4j-treepage','locale'], function(Page,locale) {
	
	Page.create(function($){
		
		
		var me = this,
			tree = $('#treeGuide'),
			tabs = $('#guideTabs'),
			tabsHead = $('#tabsHead'),
			saveBtn = $('#guideTabs').find('.SaveBtn'),
			firstState = 0,
			checkedId,
			localeClick,
			checkedItem ,
			apis ={guide:'guide',
				saveGuide:'saveGuide'
			},
			//先判断数据是否修改
			isChange = false;
			
		//获取编辑器
		editor = $b.App.iniKindEditor("guideContent");
		me.setComboDatas('USER_LOCALE');
		return {
			init :  function(){
				//关闭进度条	
				$b.Msg.closeProgress();
				//构建页面中的html标签切换模板。
				this.buildHtml();
				//构建树菜单
				tree.tree({
					url : apis.guide,
					animate: true,
					//初始化状态
					loadFilter: function(data){
						me.data = data;
						if($.isArray(data)){
							$.each(data, function(i, node){
								node.id = node.menuId;
								node.text = node.menuName;
								node.state = node.count >= 1? 'closed':'open';
								node.attributes = node;
							});
						};
						return data;
					},
					//点击菜单树 节点后将数据绑定到html模板
					onClick:function(node){
						var guideItemText,
							itemObj={};
						node.attributes.attributes = null;
						checkedItem = node.attributes.guideMenus;
						checkedId = node.id;
						
						//绑定head标题
						$('#guideContent').find('h3').text(locale.system.sysmanager.menulocale.guide.menuName+node.text);
						
						//将获取的数据展示到编辑器中
						editor.html("");
						
						tabsHead.find('.active').removeClass('active').end().find('li:first').addClass('active');
						
						//localeClick = 'zh_CN';
						//重新构建对象
						$.each(checkedItem,function(i,v){
							itemObj[v.locale] = v.guideContent;
						});
						checkedItem = [];
						$.each(locales,function(i,v){
							locale = v.id;
							checkedItem[i] = {};
							checkedItem[i]["locale"] = locale;
							checkedItem[i]["guideContent"] = itemObj[locale]?itemObj[locale]: '';
							checkedItem[i]["menuId"] = checkedId;
						});
						node.attributes.guideMenus = checkedItem;
						
						tabsHead.find('li:first').click();
					},
					onLoadSuccess : function(){
						if(firstState === 0){
							$('#treeGuide').find('li:first div').click();
							firstState ++ ;
						};
					}
				});
				
				//点击tabs切换标签页
				tabsHead.delegate('li','click',function(){
					
					var $this = $(this),
						localeClass,
						guideItemText;
					
					locale = $this.data('locale');
					localeClick = locale;
					//获取数据
					guideItemText = me.getGuide(checkedItem,locale);
					//将获取的数据展示到编辑器中
					editor.html("");
					editor.html(guideItemText);
					
					$.each(checkedItem,function(i,v){
						if(v.locale == locale){
							if(v.newGuideContent){
								editor.html(v.newGuideContent);
							};
						};
					});
					
					tabsHead.find('.active').removeClass('active');
					$this.addClass('active');
				});
				
				//失去焦点就更新结点数据
				editor.afterBlur = function(){
					var node = tree.tree('find', checkedId);
					
					$.each(checkedItem,function(i,v){
						if(v.locale == localeClick){
							v.newGuideContent = editor.html();
						};
					});
					if(node){
						node.attributes.guideMenus = checkedItem;
					};
				};
				
				//点击保存按钮提交数据。
				saveBtn.on('click',function(){
					
					$.each(checkedItem,function(i,v){
						if(v.newGuideContent || v.newGuideContent==""){
							isChange = true;
						};
						if(v.newGuideContent === v.guideContent){
							isChange = false;
						};
					});
					
					//收集数据并提交
					if(isChange){
						me.saveData(checkedItem);
					}else{
						$b.Msg.warning(locale.system.sysmanager.menulocale.guide.alertmm);
					};
				});
			},
			//获取指定语言的数据
			getGuide : function(nod,localeStr){
				var guideItemText;
				$.each(nod,function(i,v){
					if(v.locale === localeStr){
						guideItemText = v.guideContent;
						return ;
					};
				});
				return guideItemText;
			},
			
			//插入标签页面 ，li
			buildHtml : function(){
				var liStr = "";
				if(me.comboDatas.USER_LOCALE.data){
					locales = me.comboDatas.USER_LOCALE.data;
					$.each(locales,function(i,v){
						var template;
						template = '<li class="'+v.id+'" data-locale="'+v.id+'">'+v.text+'</li>';
						liStr += template;
					});
					tabsHead.append(liStr).find('li:first').addClass('active');
				};	
			},
			//处理提交数据
			saveData : function( Obj ){
				var params = {},
					index = 0,
					newGuideContent,
					oldGuideContent;
				//IE 8 不支持trim()
				if(!String.prototype.trim){
			         String.prototype.trim = function () {
			             return this.replace(/^\s+|\s+$/g,'');
			        };
			    };
			    //提交已经更改的数据
			    if(Obj){
			    	params['menuId'] =  checkedId;
					$.each(Obj,function(i,v){
						oldGuideContent = v.guideContent.trim();
						if(v.newGuideContent){
							newGuideContent = v.newGuideContent;
								params['guides['+index+'].menuId'] = v.menuId;
								params['guides['+index+'].locale'] = v.locale;
								params['guides['+index+'].guideContent'] = newGuideContent;
								index++;
						}else if(v.newGuideContent == ""){
							
						}else{
							if(oldGuideContent){
								params['guides['+index+'].menuId'] = v.menuId;
								params['guides['+index+'].locale'] = v.locale;
								params['guides['+index+'].guideContent'] = oldGuideContent;
								index++;
							};
						};
					});
					$b.Submit.ajaxSubmit(apis.saveGuide, params , me.saveCallback);
					return ;
				};
			},
			saveCallback : function(){
				$.each(checkedItem,function(i,v){
					if(v.newGuideContent){
						v.guideContent =v.newGuideContent;
					};
				});
				isChange = false;
				$b.Msg.alert(locale.operate.doOk);
			}
		};
		
	});
});