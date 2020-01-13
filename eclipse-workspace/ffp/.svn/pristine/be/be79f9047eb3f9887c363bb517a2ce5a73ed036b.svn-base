///**
// * Copy Right Information : Forms Syntron </br>
// * Project : 四方精创 Java EE 开发平台 </br>
// * Description : 使用require定义的公共JS模块</br>
// * Author : LinJisong </br>
// * Version : 1.0.0 </br>
// * Since : 1.0.0 </br>
// * Date : 2016-4-5</br>
// */
define(['jquery','locale','appLocale','ext-jquery','easyui-base','Check','String','Base','Msg' ,'Date','App','File','Download','Submit'], function($,locale,appLocale){
	return (function (window) {
		
		/*
	     * 平台私有对象，通过此对象，生成平台全局的访问入口对象
	     */
	    function _$b(arguments) {
	    }
	    /**
         * ### 描述 
         * 为平台私有对象添加获取模块的公共方法，方便后继获取各子类的模块对象
         * 
         * @author lw 2016-11-25
         * @member $b
         * @private
	     * @param {String} moduleName 模块名称，例如Check , Date , String 等
	     * @return {Object/Function} 返回一个模块对象
    	 * ### 示例
	     * 
	     *     @example
	     *     $b("UserApp");
	     */
	    var _getModule = function(moduleName){
        	var _obj = require(moduleName);
            return _obj.instance();
        };

	    /*
	     * 对象原型，设置平台子对象
	     */
	    _$b.prototype = {
	    	
	        constructor: _$b,
	        /**
	         * ### 描述 
	         * 平台上下文环境
	         * 
	         * - 系统根路径
	         * - 平台当前的主题信息
	         * - 首页标签页面的数据信息
	         * 
	         * @author lw 2016-11-25
	         * @member $b
		     * @cfg {Object} context 平台上下文环境  
		     * 到平台的语言key,可以取到zh_CH或是en_US等
		     * 
	    	 * ### 示例
		     * 
		     *     @example
		     *     $b.context.loacle
		     */
	        context : $.extend(true,{},appLocale,locale , server_consts),
	        /**
	         * ### 描述 
	         * 为平台私有对象添加获取模块的公共方法，方便后继获取各子类的模块对象
	         * 
	         * @author lw 2016-11-25
	         * @member $b
	         * @private
		     * @param {String} moduleName 模块名称，例如Check , Date , String 等
		     * @return {Object/Function} 返回一个模块对象
	    	 * ### 示例
		     * 
		     *     @example
		     *     $b("UserApp");
		     */
		    getModule : _getModule,
	        /**
	    	 * ### 描述 
	    	 * 平台公共对象，为平台的基础核对对象类，封装了基本的变更、属性、方法。
	    	 * 
	    	 * - 国际化处理函数
	    	 * - 生成唯一键
	    	 * - json的解析转换
	    	 * - html的解析转换
	    	 * - 文件处理（字节计算，文件下载与删除）
	    	 * - 打开弹出框
	    	 * 
	    	 * @author lw 2016-11-25
	    	 * @member $b
		     * @property {Object} Base  公共对象
	    	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.Base;
			 *     
	    	 */
	        Base :  _getModule('Base'),
            /**
        	 * ### 描述 
        	 * 字符串处理工具，主要是对字符串进行替换、加工等转换处理
        	 * 
        	 * - 字符串加工转换 
        	 * - 字符串规则替换
        	 * - 字符串空格过滤
        	 * - 字符串长度计算工具方法。
        	 * 
        	 * @author lw 2016-11-25
	    	 * @member $b
		     * @property {Object} String  平台字符串加工处理
        	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.String;
			 *   
        	 */
	        String : _getModule('String'),
	        /**
	    	 * ### 描述 
	    	 * 规则验证处理类
	    	 * 
	    	 * - 为空验证
	    	 * - 对象验证
	    	 * - 类型验证
	    	 * - 格式验证
	    	 * - 合规性验证	  
	    	 * - 正则表达规则等
	    	 * 
	    	 * 返回一个boolean类型的验证结果  
	    	 * 
	    	 * @author lw 2016-11-25
	    	 * @member $b
		     * @property {Object} Check  平台验证器
	    	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.Check;
	    	 */
			Check : _getModule('Check'),
	        /**
	    	 * ### 描述
	    	 * 日期工具类；
	    	 * 
	    	 * - 日期转换
	    	 * - 日期格式化处理
	    	 * - 获取季度等
	    	 * @author lw 2016-11-25
	    	 * @member $b
		     * @property {Object} Date  日期对象
	    	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.Date;
	    	 */
            Date : _getModule('Date'),
            /**
        	 * ### 描述 
        	 * 封装了与UI框架相关功能的对象
        	 * 
        	 * - 消息弹出框
        	 * - 进度条
        	 * 
        	 * @author lw 2016-11-25
	    	 * @member $b
		     * @property {Object} Msg  界面处理对象
        	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.Msg;
        	 */
            Msg : _getModule('Msg'),
            /**
        	 * ### 描述 
        	 * 封装了页面提交的方法
        	 * 
        	 * - ajax提交
        	 * - from表单提交
        	 * 
        	 * @author lw 2016-11-25
	    	 * @member $b
	    	 * @property {Object} UI  提交处理对象
        	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.Submit;
        	 */
            Submit : _getModule('Submit'),
            /**
        	 * ### 描述 
        	 * 封装了文件处理类
        	 * 
        	 * - 文件大小单位的转换处理
        	 * - 文件上传
        	 * - 文件下载
        	 * 
        	 * @author lw 2016-11-25
	    	 * @member $b
	    	 * @property {Object} File  文件处理对象
        	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.File;
        	 */
            File : _getModule('File'),
            /**
        	 * ### 描述 
        	 * 封装了下载类
        	 * 
        	 * @author ljs 2017-2-23
	    	 * @member $b
	    	 * @property {Object} Download  下载工具对象
	    	 * @since 1.1.0
        	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.Download;
        	 */
            Download : _getModule('Download'),
            /**
        	 * ### 描述 
        	 * 封装与应用相关的处理方法
        	 * 
        	 * - 富文件
        	 * - 列表格式化
        	 * - 打开一个新窗口
        	 * 
        	 * @author lw 2016-11-25
	    	 * @member $b
		     * @property {Object} App  用户应用处理对象
        	 * 
	    	 * ### 示例
	    	 * 
			 *     @example
			 *      $b.App;
        	 */
            App : _getModule('App')
	    };
	    /**
	     * # 描述
	     * 本类是全局的js对象类，平台js工具类调用的入口对象，直接用$b对象来调用里面了的了对象、属性、方法等。
	     * 
	     * ## 定义了一个上下文件环境属性
	     * - 属性一：context，此环境属性当中有系统的路径、语言环境、主题信息、首页的标签控制参数、是否是debug模式
	     * 
	     * ## 封装了五个子对象
	     * - 子对象一：Base，平台的基本公用对象，里面有公用的方法，或属性等；
	     * - 子对象二：String，字符串处理工具，主要是对字符串进行替换、加工等转换处理
	     * - 子对象三：Check，验证工具，对字符串的验证、正则表达规则，多数返回一个验证结果
	     * - 子对象四：Date，日期工具类，封装了对时间的处理工具类；
	     * - 子对象五：UI，封装了与UI框架相关的对象，例如消息弹出框、进度条等；
	     * - 子对象六：File，封装了处理文件相关的工具
	     * - 子对象七：Submit，封装了页面请求提交的方法
	     * - 子对象七：App，与应用相关的工具方法
	     * - 子对象八：Download，与下载相关的工具方法，从1.1.0版开始提供
	     * @class $b
	     * @since 1.0.0  lw <szluow@163.com> 20161114
	     */
	    window.$b = new _$b();
	    return $b;
	})(window);
});