define(function(require) {
	/**
	 * ### 描述
	 * 日期工具类；
	 * 
	 * - 日期转换
	 * - 日期格式化处理
	 * - 获取季度等
	 * @class $b.Date
	 * @requires $b
	 * @singleton
	 */
	var Date = (function() {
		// 定义一个私有单例对象
		var _singletonInstance,$ ,_string , _check;// undefined
		var initInstance = function() {
			
			$  = require('jquery');
            _string = require("String").instance();
            _check =  require("Check").instance();
            
			return {
				/** 
				 * ### 描述
	             * 将字符串转换为日期对象  
	             * 
	             * ### 示例
	             * 
	             *     @example
	             * 	   $b.Date.toDate('2016-11-25');
	             *
	             * @member $b.Date
		    	 * @method toDate
	             * @param {String} obj  时间字符串
	             * 
	             * ### 支持的格式
	             * 
	             * - 'yyyy-MM-dd'
	             * - 'yyyy/MM/dd' 
	             * - 'yyyy-MM-dd hh:mm:ss'
	             * 
	             * @returns {Date}  返回一个date的对象类型
	             */
				toDate : function (obj) {
			        return $.type(obj) === 'date' ? obj : ($.type(obj) === 'string' ? _string.toDate(obj) : new Date(obj));
			    },
			    /** 
			     * ### 描述 
	             * 日期格式化  
	             * 
	             * ### 示例
	             * 
	             *     @example
	             * 		$b.Date.format('2016-11-25' , 'yyyyMMdd');
	             * 
	             * @author OuLinhai  2016-05-18
	             * @member $b.Date
		    	 * @method format
	             * @param {Date} date 一个日期格式的字符串
	             * 
	             * ### 支持的格式
	             * 
	             * - 'yyyy-MM-dd'
	             * - 'yyyy/MM/dd' 
	             * - 'yyyy-MM-dd hh:mm:ss'
	             * @param {String} format [name='yyyy-MM-dd'] 日期格式的字符串,如果为空或是没有传递此参数，则会以'yyyy-MM-dd'的格式进行格式化处理
	             * @returns {String}  返回对象为一个字符串
	             */
				format : function (date, format) {
					date = _singletonInstance.toDate(date);
			        format = _check.isNullOrEmptyStr(format) ? 'yyyy-MM-dd' : format;
			        var dict = {
			            'yyyy': date.getFullYear(),
			            'M': date.getMonth() + 1,
			            'd': date.getDate(),
			            'H': date.getHours(),
			            'm': date.getMinutes(),
			            's': date.getSeconds(),
			            'MM': ('' + (date.getMonth() + 101)).substr(1),
			            'dd': ('' + (date.getDate() + 100)).substr(1),
			            'HH': ('' + (date.getHours() + 100)).substr(1),
			            'mm': ('' + (date.getMinutes() + 100)).substr(1),
			            'ss': ('' + (date.getSeconds() + 100)).substr(1)
			        };
			        return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function () {
			            return dict[arguments[0]];
			        });
			    },
			    /** 
			     * ### 描述
			     * 判断日期或年份是否为润年  
			     * 
			     * ### 示例
			     * 
			     *     @example
			     * 		$b.Date.isLeapYear('2016-11-25');
			     * 
			     * @member $b.Date
		    	 * @method isLeapYear
	             * @param {Date} date 年份（数值型）或日期（日期对象或是日期字符串）
	             * 
	             * ### 支持的格式
	             * 
	             * - 'yyyy-MM-dd'
	             * - 'yyyy/MM/dd' 
	             * - 'yyyy-MM-dd hh:mm:ss'
	             * 
	             * @returns {Boolean} 返回true、false的boolean值
	             * 
	             */
			    isLeapYear : function (date) {
			        var y = 0;
			        if ($.type(date) === 'number') {
			            y = date;
			        } else {
			            var dt = _singletonInstance.toDate(date);
			            if($.type(dt) === 'date'){
			            	y = dt.getFullYear();
			            }else{
			            	throw '参数有误';
			            }
			        } 
			        var b = false;
			        if (y >= 0) {
			            b = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
			        } else {
			            b = (y % 4 == 1 && y % 100 != 0) || (y % 400 == 1);
			        }
			        return b;
			    },
			    /** 
			     * ### 描述
			     * 获取指定日期的当前季度（0-3）  
			     * 
			     * ### 示例
			     * 
			     *     @example
			     * 	   $b.Date.getQuarter('2016-11-25');
			     * 
			     * @member $b.Date
		    	 * @method getQuarter
	             * @param {Date} date  日期（日期对象或是日期字符串）
	             * 
	             * ### 支持的格式
	             * 
	             * - 'yyyy-MM-dd'
	             * - 'yyyy/MM/dd' 
	             * - 'yyyy-MM-dd hh:mm:ss'
	             * 
	             * @returns {Number} 返回数据值类型
	             */
			    getQuarter : function (date) {
			        date = _singletonInstance.toDate(date);
			        var m = date.getMonth();
			        var q = 0;
			        if (m >= 0 && m < 3) {
			            q = 0;
			        } else if (m >= 3 && m < 6) {
			            q = 1;
			        } else if (m >= 6 && m < 9) {
			            q = 2;
			        } else if (m >= 9 && m < 12) {
			            q = 3;
			        }
			        return q;
			    }
			}
		};
		return {
			getInstance : function() {
				// 如果单例对象不存在，则建立此对象，如果存在，直接返回
				if (!_singletonInstance) {
					_singletonInstance = initInstance();
				}
				return _singletonInstance;
			}
		};
	})();
	return {
		instance : function() {
			return Date.getInstance();
		}
	};
});