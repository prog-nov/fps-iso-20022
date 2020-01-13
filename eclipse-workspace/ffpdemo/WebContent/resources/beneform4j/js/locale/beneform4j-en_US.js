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
			username	:	'user name',
			password	:	'password'
		}
	},
	title	:	{
		add	:	'add',
		edit:	'edit',
		remove:	'remove',
		detail: 'detail',
		home  : 'home',
		lang  : 'change language',
		search  : 'search criteria',
		naddress  : 'now location',
		systemTip :'system hint',
		systemError :'system error',
		systemWarning:'operate remind',
		systemConfirm:'system confirm',
		pleaseWait:   'please wait',
		tipDetail :   'remind details'
	},
	label   :   {
		all :   'all',
		pub :   'public',
		pleaseSelect : 'please select'
	},
	button	:	{// 按钮
		find:	'lookup',
		add:	'add',
		reset:	'reset',
		submit:	'submit',
		cancel:	'cancel',
		save:	'save',
		edit:	'edit',
		remove:	'remove',
		login:	'login',
		close:	'close',
		look:	'look',
		submitSearch:'submit search'
	},
	check	:	{// 表单检验
		pleaseInput	:	'please input “{1}”！',
		pleaseSelect:	'please select “{1}”！'
	},
	operate		:	{// 操作提示
		edit			:	'please select record to update！',
		selectToRemove	:	'please select record to remove！',
		remove			:	'are you sure remove {1} record? ',
		removeOk   	    :	'operate success，delete {1} record！',
		nothingRole		:	'do role not open！',
		openTabMax		:	'open tab to max count。',
		exitSytem       :   'exit system？',
		closeHome       :   'are you sure close home？',
		notClose        :   '{1}not close！',
		doOk            :   'operate success！',
		doFail          :   'operate Failed！',
		load			:	'loading...',
		systemDo		:	'system doing...',
		selectToRecord	:   'please select record to operate！',
		selectRecordToMore : 'select record to max, can select one record！',
	},
	error	:	{// 异常提示
		loadtype		:	'不支持的资源类型，无法加载！',
		loadjs			:	'加载JS脚本失败：{1}',
		loadcss			:	'加载CSS样式表失败：{1}',
		loadData		:	'load data error！',
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
		notFindNode     :   '未找到节点{1}。'
	},
	validate :	{
		select			:	'please select the item ...',
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
		numericRange	:	'输入的内容必须是指定类型的数字格式且介于 {0} 与 {1} 之间.'
	},	
	system  :  {
		main :{
			noteice : 'Noteice',
			memo	: 'Memo'
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
		taskrule :{
			title:{
				limitusers	: 'Limit Users',
				limitorgs	: 'Limit Orgs',
				limitroles	: 'Limit Roles',
				linkdetail	: 'Link Detail',
                detailmenuname: 'Link Detail menu name',
				islinkdetail: 'Is Link Detail',
				modify		: 'Modify Task Node',
				insert		: 'Add Task Node',
				gotoadduser	: 'Add Users',
				userId		: 'User ID：',
				userName	: '  User Name：'
			},
			content:{
				userexists	: 'Users already add or exists\r\n{1}',	
				yes			: 'Yes',
				no			: 'No',
				deleteAllUser: 'Are you sure to delete all users?',
				doadd		: 'Are you sure to add this task rule?',
				doupdate	: 'Are you sure to update this task rule?',
				notseletuser: 'Hasn‘t chose any users,please choose what you want',
				doOk		: 'Operated success,can continue to add;\r\nClick to close tithout adding'
			},
			validate:{
				tasknodenotnull : 'Task Node cann’t be empty' 
			}
		},
		multiuploader : {
			success    : " Upload Success,The File id is ：{1}",
			dbclickdel : "Double Click For Delete",
			del        : "File {1} Deleted From The Queue",
			uploading  : " Uploading : {1}%",
			checkerror : "Environmental Check Does Not pass",
			
		}
	},
	demo    :  { //DEMO
		title  :{
			add:"addproduct",
			update:"update product",
			remove:"delete product",
			detail:"product info"
		},
		product:{//DEMO 产品信息
			name:"product name",
			type:"product category",
			count:"product count",
			oldprice:"old price",
			newprice:"now price",
			address:"product address",
			description:"product description",
			remark:"remark"
		}
	},
	logout :　{
		beforeunloadtitle : "sure to exit?"
	}
});