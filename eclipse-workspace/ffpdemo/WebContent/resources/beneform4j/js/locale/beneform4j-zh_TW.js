/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用require定义国际化资源模块<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
define({
	form	:	{
		login	:	{
			username	:	'用戶名',
			password	:	'密　碼'
		}
	},
	title	:	{
		add	:	'添加',
		edit:	'編輯',
		remove:	'刪除',
		detail: '明細',
		home  : '首頁',
		lang  : '語言切換',
		search  : '搜索條件',
		naddress  : '當前位置'
		
	},
	button	:	{// 按鈕
		find:	'查  找',
		add:	'新  增',
		insert: '添  加',
		reset:	'重  置',
		submit:	'提  交',
		cancel:	'取  消',
		save:	'保  存',
		edit:	'修  改',
		remove:	'刪  除',
		login:	'登  錄',
		close:	'關  閉',
		look:	'查  看',
		submitSearch:'提交查詢'
	},
	check	:	{// 表單檢驗
		pleaseInput	:	'請輸入“{1}”！',
		pleaseSelect:	'請選擇“{1}”！'
	},
	operate		:	{// 操作提示
		edit			:	'請選擇1條記錄進行修改！',
		selectToRemove	:	'請選擇要刪除的記錄！',
		remove			:	'您確定要刪除所選的{1}條記錄嗎?',
		removeOk   	    :	'操作成功，成功刪除{1}條記錄！',
		nothingRole		:	'操作許可權尚未開通！',
		openTabMax		:	'您打開的頁面數量已經達到最大限制，請關閉一些頁面後再試。',
		exitSytem       :   '確認退出系統？',
		closeHome       :   '您確定要關閉首頁嗎？',
		notClose        :   '{1}不可以被關閉！',
		doOk            :   '操作成功！',
		doFail          :   '操作失敗！',
		load			:	'載入中...'
	},
	error	:	{// 異常提示
		loadtype		:	'不支援的資源類型，無法載入！',
		loadjs			:	'載入JS腳本失敗：{1}',
		loadcss			:	'載入CSS樣式表失敗：{1}',
		loadData		:	'載入數據異常！',
		loadnoregister	:	'要載入的模組 {1} 尚未註冊！',
		requestError	:	'請求資料格式非法！',
		pageNotFound	:	'頁面未找到!',
		serverError		:	'伺服器異常！',
		loadError		:	'載入出現錯誤，請聯繫管理員'
	},
	validate :	{
		select			:	'請選擇',
		engNum			:	'請輸入英文字母或數位',
		chsEngNum		: 	'只允許漢字、英文字母或數位',
		name			:	'用戶名不合法(字母開頭，允許4-16位元組，允許字母數位底線)',
		code			:	'只允許漢字、英文字母、數位及底線',
		minLength		:	'最少輸入 {0}個字元.',
		maxLength		:	'最多輸入 {0}個字元.',
		tel				:	'輸入的內容必須是電話號碼(中國)格式.',
		mobile			:	'輸入的內容必須是行動電話號碼(中國)格式.',
		telOrMobile		:	'輸入的內容必須是電話號碼(中國)或行動電話號碼(中國)格式.',
		fax				:	'輸入的內容必須是傳真號碼(中國)格式.',
		zipCode			:	'輸入的內容必須是郵遞區號(中國)格式.',
		incorrect		:	'輸入內容不正確...',
		same			:	'兩次輸入不一致！',
		contains		:	'輸入的內容必須包含 {0}.',
		startsWith		:	'輸入的內容必須以 {0} 作為起始字元.',
		endsWith		:	'輸入的內容必須以 {0} 作為起始字元.',
		longDate		:	'輸入的內容必須是長日期時間(yyyy-MM-dd hh:mm:ss)格式.',
		shortDate		:	'輸入的內容必須是短日期(yyyy-MM-dd)格式.',
		existChinese	:	'輸入的內容必須是包含中文漢字.',
		chinese			:	'輸入的內容必須是純中文漢字.',
		english			:	'輸入的內容必須是純英文字母.',
		fileName		:	'輸入的內容必須是合法的檔案名(不能包含字元 \\/:*?\'<>|).',
		ip				:	'輸入的內容必須是正確的 IP位址v4 格式.',
		url				:	'輸入的內容必須是正確的 url 格式.',
		qq				:	'輸入的內容必須是正確 QQ 號碼格式.',
		carNo			:	'輸入的內容必須是合法的汽車車牌號碼格式.',
		carEngineNo		:	'輸入的內容必須是合法的汽車發動機序號格式.',
		idCard			:	'輸入的內容必須是合法的身份證號碼(中國)格式.',
		integer			:	'輸入的內容必須是合法的整數格式.',
		integerRange	:	'輸入的內容必須是合法的整數格式且值介於 {0} 與 {1} 之間.',
		numeric			:	'輸入的內容必須是指定類型的數位格式.',
		numericRange	:	'輸入的內容必須是指定類型的數位格式且介於 {0} 與 {1} 之間.'
	},
	demo    :  { //DEMO
		title  :{
			add:'新增產品',
			update:'修改產品',
			remove:'刪除產品',
			detail:'產品資訊'
		},
		product:{//DEMO 產品資訊
			name:'產品名稱',
			type:'產品類型',
			count:'產品數量',
			oldprice:'原價（元）',
			newprice:'現價（元）',
			address:'產品位址',
			description:'產品描述',
			remark:'備註'
		}
	},
	//平臺系統參數
	system  :  {
		main :{
			noteice 	: '公告',
			memo		: '備忘錄'
		},
		news  :{
			title  :{
				msgTitle:'消息標題',
				msgTypeName:'消息類型',
				msgLevelName:'消息級別',
				msgContent:'消息內容',
				sendOper:'發佈人',
				sendDate:'發佈日期',
				sendTime:'發佈時間'
			},
			content  :{
				addTitle:'新增消息',
				editTitle:'修改消息',
				detailTitle:'消息詳細'
			}
		},
		taskrule :{
			title:{
				limitusers	: '限定用戶',
				limitorgs	: '限定機構',
				limitroles	: '限定角色',
				linkdetail	: '进入明細',
                detailmenuname: '明細菜單',
				islinkdetail: '是否進入明細頁面',
				modify		: '修改任務節點',
				insert		: '新增任務節點',
				gotoadduser	: '新增用戶',
				userId		: '用戶ID：',
				userName	: '  用戶姓名：'
			},
			content:{
				userexists	: '以下用戶已存在或已添加\r\n{1}',	
				yes			: '是',
				no			: '否',
				deleteAllUser: '您確定要刪除所有用戶嗎？',
				doadd		: '您確定要添加該規則嗎？',
				doupdate	: '您確定要更新規則責嗎？',
				notseletuser: '當前未選擇用戶，請選擇',
				doOk		: '操作成功，可繼續添加;\r\n無需添加請點擊關閉'
			},
			validate:{
				tasknodenotnull : '任務節點不能為空' 
			}
		}
	},
	logout :　{
		beforeunloadtitle : "确定退出吗？"
	}
});