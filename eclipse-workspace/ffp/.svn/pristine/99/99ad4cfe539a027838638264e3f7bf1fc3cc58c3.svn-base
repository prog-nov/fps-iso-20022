/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 包含树型结构的Page页面对象 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-2<br>
 */
define(['jquery','common','beneform4j-page'], function($, $b, Page){
	/**
     * 添加实例方法
     */
    $.extend(Page.prototype, {
		/**
		 * 树节点格式化
		 * 
		 * @param node 树节点
		 */
		nodeFormatter : function(node) {
		    if (node.count >= 1) {
		    	return node.text + '<font color="red">(' + node.count + ')</font>';
		    }
		    return node.text;
		},
	    /**
		 * 异步树数据加载过滤器(逐级加载)
		 *  设置节点状态
		 */
		asyncLoadFilter: function(data){
			if($.isArray(data)){
				$.each(data, function(i, node){
					node.state = node.count >= 1? 'closed':'open';
				});
			}
			return data;
		},
		/**
		 * 右键菜单事件函数
		 */
		onContextMenuFn: function(e, node, menu){
			e.preventDefault();
			menu.node = node;
			menu.target = node.target;//保存当前对象
			menu.menu('show',{left: e.pageX, top: e.pageY});
		},
		/**
		 * 进入某个拖动目标的事件函数
		 */
		onDragEnterFn : function(tree, target){
			if(!tree.tree('isLeaf', target)){
				var children = tree.tree('getChildren', target);
				if(children && children.length >= 1){//如果未展开父菜单并且父节点已经加载过子节点，则先展开	
					tree.tree('expand', target);
				}
			}
		}
    });

    return Page;
});