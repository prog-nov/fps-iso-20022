/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用require定义国际化资源模块<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-5<br>
 */
define({
	form	:	{
		login	:	{
			username	:	'用户名',
			password	:	'密　码'
		}
	},
	title	:	{
		add	:	'添加',
		edit:	'编辑',
		remove:	'删除',
		detail: '明细',
		home  : '首页',
		lang  : '语言切换',
		search  : '搜索条件',
		naddress  : '当前位置',
		systemTip :'系统提示',
		systemError :'系统错误',
		systemWarning:'操作提醒',
		systemConfirm:'系统确认',
		pleaseWait:   '请稍后',
		tipDetail :   '提示详情'
	},
	label   :   {
		all :   '全部',
		pub :   '公共',
		pleaseSelect : ' 请选择   '
	},
	button	:	{// 按钮
		find:	'查  找',
		add:	'新  增',
		insert:	'添  加',
		reset:	'重  置',
		submit:	'提  交',
		cancel:	'取  消',
		save:	'保  存',
		edit:	'修  改',
		remove:	'删  除',
		login:	'登  录',
		close:	'关  闭',
		look:	'查  看',
		submitSearch:'提交查询'
	},
	check	:	{// 表单检验
		pleaseInput	:	'请输入“{1}”！',
		pleaseSelect:	'请选择“{1}”！'
	},
	operate		:	{// 操作提示
		edit			:	'请选择1条记录进行修改！',
		selectToRemove	:	'请选择要删除的记录！',
		remove			:	'您确定要删除所选的{1}条记录吗?',
		removeOk   	    :	'操作成功，成功删除{1}条记录！',
		nothingRole		:	'操作权限尚未开通！',
		openTabMax		:	'您打开的页面数量已经达到最大限制，请关闭一些页面后再试。',
		exitSytem       :   '确认退出系统？',
		closeHome       :   '您确定要关闭首页吗？',
		notClose        :   '{1}不可以被关闭！',
		doOk            :   '操作成功！',
		doFail          :   '操作失败！',
		load			:	'加载中...',
		systemDo		:	'系统正在处理中...',
		selectToRecord	:   '请选择需要操作的记录',
		selectRecordToMore : '选择的记录条数过多，每次只能选择一条记录操作',
		uploadsuccess   :    "上传成功",
		msginfo   :    "信息提示：{1}。"
	},
	error	:	{// 异常提示
		loadtype		:	'不支持的资源类型，无法加载！',
		loadjs			:	'加载JS脚本失败：{1}',
		loadcss			:	'加载CSS样式表失败：{1}',
		loadData		:	'加载数据异常！',
		loadnoregister	:	'要加载的模块 {1} 尚未注册！',
		requestError	:	'请求数据格式非法！',
		pageNotFound	:	'页面未找到!',
		serverError		:	'服务器异常！',
		loadError		:	'加载出现错误，请联系管理员',
		requestTimeout  :   '请求超时，请重新操作。',
		ajaxSubtmitError:   'Ajax提交出现错误，URL：{1}',
		ajaxDebugError  :   'Ajax调试出现错误，URL：{1}，{2}',
		ajaxSubmitError :   'Ajax提交表单出现错误，URL：{1}',
		inputContentError:  '输入内容不正确。',
		hasObject		:   '已经存在对象 {1}，请检查...',
		notFindKey      :   '未找到键值为{1}的参数。',
		notFindNode     :   '未找到节点{1}。',
		uploadfail      :   "上传失败",
		uploadunsuport  :   "上传插件环境检测不通过"
		
	},
	validate :	{
		select			:	'请选择',
		file			:	'至少选择一个上传文件',
		engNum			:	'请输入英文字母或数字',
		chsEngNum		: 	'只允许汉字、英文字母或数字',
		name			:	'用户名不合法(字母开头，允许4-16字节，允许字母数字下划线)',
		code			:	'只允许汉字、英文字母、数字及下划线',
		minLength		:	'最少输入 {0}个字符.',
		maxLength		:	'最多输入 {0}个字符.',
		tel				:	'输入的内容必须是电话号码(中国)格式.',
		mobile			:	'输入的内容必须是移动电话号码(中国)格式.',
		telOrMobile		:	'输入的内容必须是电话号码(中国)或移动电话号码(中国)格式.',
		fax				:	'输入的内容必须是传真号码(中国)格式.',
		zipCode			:	'输入的内容必须是邮政编码(中国)格式.',
		incorrect		:	'输入内容不正确...',
		same			:	'两次输入不一致！',
		contains		:	'输入的内容必须包含 {0}.',
		startsWith		:	'输入的内容必须以 {0} 作为起始字符.',
		endsWith		:	'输入的内容必须以 {0} 作为起始字符.',
		longDate		:	'输入的内容必须是长日期时间(yyyy-MM-dd hh:mm:ss)格式.',
		shortDate		:	'输入的内容必须是短日期(yyyy-MM-dd)格式.',
		existChinese	:	'输入的内容必须是包含中文汉字.',
		chinese			:	'输入的内容必须是纯中文汉字.',
		english			:	'输入的内容必须是纯英文字母.',
		fileName		:	'输入的内容必须是合法的文件名(不能包含字符 \\/:*?\'<>|).',
		ip				:	'输入的内容必须是正确的 IP地址v4 格式.',
		url				:	'输入的内容必须是正确的 url 格式.',
		qq				:	'输入的内容必须是正确 QQ 号码格式.',
		carNo			:	'输入的内容必须是合法的汽车车牌号码格式.',
		carEngineNo		:	'输入的内容必须是合法的汽车发动机序列号格式.',
		idCard			:	'输入的内容必须是合法的身份证号码(中国)格式.',
		integer			:	'输入的内容必须是合法的整数格式.',
		integerRange	:	'输入的内容必须是合法的整数格式且值介于 {0} 与 {1} 之间.',
		numeric			:	'输入的内容必须是指定类型的数字格式.',
		numericRange	:	'输入的内容必须是指定类型的数字格式且介于 {0} 与 {1} 之间.',
		onefile			:	'选择文件过多，只支持上传一个文件'
	},
	demo    :  { //DEMO
		title  :{
			add:'新增产品',
			update:'修改产品',
			remove:'删除产品',
			detail:'产品信息'
		},
		product:{//DEMO 产品信息
			name:'产品名称',
			type:'产品类型',
			count:'产品数量',
			oldprice:'原价（元）',
			newprice:'现价（元）',
			address:'产品地址',
			description:'产品描述',
			remark:'备注'
		}
	},
	//平台系统参数，防止与
	system  :  {
		main :{
			noteice 	: '公告',
			memo		: '备忘录'
		},
		news  :{
			title  :{
				msgTitle:'消息标题',
				msgTypeName:'消息类型',
				msgLevelName:'消息级别',
				msgContent:'消息内容',
				sendOper:'发布人',
				sendDate:'发布日期',
				sendTime:'发布时间'
			},
			content  :{
				addTitle:'新增消息',
				editTitle:'修改消息',
				detailTitle:'消息详细'
			}
		},
		document:{
			title:{
				docName:'文档名称',
				suffix:'文件后缀',
				checkSumType: '文件校验类型',
				checkSum: '文件校验值',
				docSize:'文档大小',
				docType:'文档类型',
				docState:'文档状态',
				instOper:'上传用户',
				instDate:'上传日期'
			},
			content:{
				addTitle:'新增文档',
				editTitle:'修改文档',
				detailTitle:'文档详细',
				notNeedUnLock:"当前状态已启用，无需解锁",
				notNeedLock:"当前状态已锁定，无需再次锁定",
				dbtile:"双击删除",
				process:"已上传",
				param:"传递的参数当中，文档编号为空，删除失败",
				undownload:"当前状态已锁定，暂不支持下载"
			}
		},
		menu :{
			favorites:{
				success		: '收藏成功',
				failure		: '收藏失败',
				notSupport	: '该菜单不支持收藏',
				isExist	: '该菜单已经收藏'
			}
		},
		taskrule :{
			title:{
				limitusers	: '限定用户',
				limitorgs	: '限定机构',
				limitroles	: '限定角色',
				linkdetail	: '进入明细',
                detailmenuname: '明细菜单',
				islinkdetail: '是否进入明细页面',
				modify		: '修改任务节点',
				insert		: '新增任务节点',
				gotoadduser	: '新增用户',
				userId		: '用户ID：',
				userName	: '  用户姓名：'
			},
			content:{
				userexists	: '以下用户已添加或已存在\r\n{1}',	
				yes			: '是',
				no			: '否',
				deleteAllUser: '您确定要删除选择的所有已选择的用户点吗？',
				doadd		: '您确定要添加该规则吗？',
				doupdate	: '您确定要更新该规则吗？',
				notseletuser: '当前未选择用户，请选择',
				doOk		: '操作成功，可继续添加;\r\n无需添加请点击关闭'
			},
			validate:{
				tasknodenotnull : '任务节点不能为空' 
			}
		},
		multiuploader : {
			success    : " 上传成功,文件ID：{1}",
			dbclickdel : "双击删除",
			del        : "文件{1}从队列移除",
			uploading  : " 正在上传 : {1}%",
			checkerror : "上传插件环境检测不通过",
			
		}
	},
	logout :　{
		beforeunloadtitle : "确定退出吗？"
	}
});