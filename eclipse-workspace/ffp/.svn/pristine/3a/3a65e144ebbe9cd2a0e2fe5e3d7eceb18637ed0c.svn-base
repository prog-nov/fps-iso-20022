/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : RequireJS配置，依赖于server_consts全局变量<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-5<br>
 */
require.config({
	/**
	 * JS文件主路径
	 */
	baseUrl : server_consts.root + '/resources/beneform4j/js',
	/**
	 * JS库相对于主路径的相对路径
	 */
    paths : {
    	/**
    	 * JS路径
    	 */
        'jquery' : '../../third/jquery/jquery-easyui-1.3.3/jquery.min',
        'easyui-core' : '../../third/jquery/jquery-easyui-1.3.3/jquery.easyui.min',
        'easyui-base' : '../../third/jquery/jquery-easyui-1.3.3/locale/easyui-lang-'+server_consts.locale,
        'rsa' : '../../third/rsa/rsa',
        'easyui-portal' : '../../third/jquery/jquery-easyui-portal/jquery.portal',
        'easyui-layout' : '../../third/jquery/jquery-easyui-1.3.3/plugins/jquery.layout',
        'datagrid-groupview' : '../../third/jquery/jquery-easyui-1.3.3/extensions/datagridview/datagrid-groupview',
        'datagrid-bufferview' : '../../third/jquery/jquery-easyui-1.3.3/extensions/datagridview/datagrid-bufferview',
        'datagrid-detailview' : '../../third/jquery/jquery-easyui-1.3.3/extensions/datagridview/datagrid-detailview',
        'datagrid-scrollview' : '../../third/jquery/jquery-easyui-1.3.3/extensions/datagridview/datagrid-scrollview',
        'mycalendar' : './extensions/jquery.fullcalendar',
        'frame-plus' : './extensions/framePlus',
        'ext-jquery' : './extensions/extJquery',
        'extBrowser' : './extensions/extBrowser',
        'datePicker' : '../../third/date/WdatePicker',
        'highcharts' : '../../third/Highcharts-3.0.1/js/highcharts',
        'mainframe'  : './mainframe',
        'WebUploader': '../../third/upload/webuploader/webuploader.min',
        'multiuploader': './extensions/multiuploader',
        'multiuploader-beneform4j': './extensions/multiuploader-beneform4j',
        'multiuploader-common': './extensions/multiuploader-common',
        'koala.min.1.5': '../../third/jquery/slider/koala.min.1.5',
        'task': './module/task',
        'Check': './module/Check',
        'String': './module/String',
        'Date': './module/Date',
        'Base': './module/Base',
        'Submit': './module/Submit',
        'File': './module/File',
        'Download': './module/Download',
        'Msg': './module/Msg',
        'App': './module/App',
        /**
         * add by linjisong 2016-4-30
         */
        'locale'           : './locale/beneform4j-'+server_consts.locale+server_consts.min,
        'common'           : './beneform4j'+server_consts.min,
        'beneform4j-easyui': './beneform4j-easyui'+server_consts.min,
        'beneform4j-page'  : './beneform4j-page'+server_consts.min,
        'beneform4j-treepage'  : './beneform4j-treepage'+server_consts.min,
        
        'kindeditor' : '../../third/kindeditor-4.1.10/kindeditor'+server_consts.min,
        'appLocale'        : beneform4jConfig.appLocale || '',
        'multiuploader-app'        : beneform4jConfig.appUploader || ''
    },
    shim: {
    	'easyui-core' : {
    		deps: ['jquery']
    	},
    	'easyui-base' : {
    		deps: ['easyui-core']
    	},
    	'datagrid-groupview' : {
    		deps: ['easyui-base']
    	},
    	'datagrid-bufferview' : {
    		deps: ['easyui-base']
    	},
    	'datagrid-detailview' : {
    		deps: ['easyui-base']
    	},
    	'datagrid-scrollview' : {
    		deps: ['easyui-base']
    	},
    	'easyui-portal' : {
    		deps: ['easyui-base']
    	},
    	'easyui-layout' : {
    		deps: ['easyui-base']
    	},
    	'mycalendar' : {
    		deps: ['easyui-base']
    	},
    	'ext-jquery' : {
    		deps: ['jquery']
    	},
		'datePicker' : {
			exports: 'WdatePicker'
		},
		'locale':{
			exports: 'locale'
		}
    	
    }
});