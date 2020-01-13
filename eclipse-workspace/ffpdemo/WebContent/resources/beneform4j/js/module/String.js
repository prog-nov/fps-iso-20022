define(function(require) {
	/**
	 * ### 描述 
	 * 字符串处理工具，主要是对字符串进行替换、加工等转换处理
	 * 
	 * - 字符串加工转换 
	 * - 字符串规则替换
	 * - 字符串空格过滤
	 * - 字符串长度计算工具方法。
     * @class $b.String
     * @requires $b
     * @singleton
     */
    var Str = (function() {
        
        // 定义一个私有单例对象
        var _singletonInstance,_check , $;// undefined
        var initInstance = function() {
            
            $  = require('jquery');
            _check =  require("Check").instance();
            //左右去空格
            var trim = $.trim ;
            return {
            	/**
                 * ### 描述
                 * 
                 * 字符串两边去空格
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.String.trim(' hello'); //返回'hello'
                 * 
                 * @param {String} str  源字符串
                 * @returns {String}  处理后的字符串副本
                 * 
                 */
                trim : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return trim(str);
                },
            	
                /** 
                 * ### 描述
                 * 
                 * 字符串左边去空格
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.String.ltrim(' hello'); //返回'hello'
                 * 
                 * @param {String} str  源字符串
                 * @returns {String}  处理后的字符串副本
                 * 
                 */
                ltrim : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.replace(/(^\s*)/g, '');
                },
                /** 
                 * ### 描述
                 * 
                 * 字符串右边去空格
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.String.rtrim('hello '); //返回'hello'
                 * 
                 * @param {String} str  源字符串
                 * @returns {String}  处理后的字符串副本
                 * 
                 */
                rtrim : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.replace(/(\s*$)/g, '');
                },
                /** 
                 * ### 描述
                 * 
                 * 字符串全局替换
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var str = $b.String.replaceAll('abcdcde','cd','12'); //str的值为'ab1212e'
                 * 
                 * @param {String} str  源字符串
                 * @param {String} substr 要替换的子串
                 * @param {String} replacement 替换的目标串
                 * @returns {String}  完成替换操作后的字符串副本
                 * 
                 */
                replaceAll : function (str, substr, replacement) {
                    if (!substr || substr == replacement) {
                        return str;
                    }
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    var length = str.length, i = 0;
                    while (str.indexOf(substr) > -1 && i++ < length) {
                        str = str.replace(substr, replacement);
                    }
                    return str;
                },
                /** 
                 * ### 描述
                 * 
                 * 判断当前字符串是否包含指定内容
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.contains('abcdcde','cde'); //返回true
                 * 
                 * @param {String} str  源字符串
                 * @param {String} val 要查找的串
                 * @returns {Boolean}  是否包含，布尔值
                 * 
                 */
                contains : function (str, val) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.indexOf(val) > -1;
                },
                /** 
                 * ### 描述
                 * 
                 * 判断某字符串是否以指定字符串开头
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.startsWith('abcdcde','abc'); //返回true
                 * 
                 * @param {String} str  源字符串
                 * @param {String} val 匹配子串
                 * @returns {Boolean}  结果
                 * 
                 */
                startsWith : function (str, val) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.substr(0, val.length) == val;
                },
                /** 
                 * ### 描述
                 * 
                 * 判断某字符串是否以指定字符串结尾
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.endsWith('abcdcde','cde'); //返回true
                 * 
                 * @param {String} str  源字符串
                 * @param {String} val 匹配子串
                 * @returns {Boolean}  结果
                 * 
                 */
                endsWith : function (str, val) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.substr(str.length - val.length) == val;
                },
                /** 
                 * ### 描述
                 * 
                 * 截取字符串左边指定长度内容
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.left('abcdcde',3); //返回'abc'
                 * 
                 * @param {String} str  源字符串
                 * @param {Number} len 截取字符长度
                 * @returns {String}  截取后的字符串副本
                 * 
                 */
                left : function (str, len) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    if (!_check.isNumeric(len)) {
                        len = parseInt(len, 10);
                    }
                    if (len < 0 || len > str.length) {
                        len = str.length;
                    }
                    return str.substr(0, len);
                },
                /** 
                 * ### 描述
                 * 
                 * 截取字符串右边指定长度内容
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.right('abcdcde',3); //返回'cde'
                 * 
                 * @param {String} str  源字符串
                 * @param {Number} len 截取字符长度
                 * @returns {String}  截取后的字符串副本
                 * 
                 */
                right : function (str, len) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    if (!_check.isNumeric(len)) {
                        len = parseInt(len, 10);
                    }
                    if (len < 0 || len > str.length) {
                        len = str.length;
                    }
                    return str.substring(str.length - len, str.length);
                },
                /** 
                 * ### 描述
                 * 
                 * 截取字符串子串
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.mid('abcdcde',1,3); //返回'bcd'
                 * 
                 * @param {String} str  源字符串
                 * @param {Number} start 起始下标，从0开始
                 * @param {Number} len 截取长度
                 * @returns {String}  截取后的字符串副本
                 * 
                 */
                mid : function (str, start, len) {
                    if (!start) { start = 0; }
                    if (!len) { len = 0; }
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.substr(start, len);
                },
                /** 
                 * ### 描述
                 * 
                 * 获取字符串的打印长度（一个汉字算2个字符）
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.lengthOfPrint('abc您好'); //返回7
                 * 
                 * @param {String} str  源字符串
                 * @returns {Number}  返回长度
                 * 
                 */
                lengthOfPrint : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    return str.replace(/[^\x00-\xff]/g, '**').length;
                },
                /** 
                 * ### 描述
                 * 
                 * 获取字符串的字节长度
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.getByteLen('abc您好'); //UTF-8编码时返回7
                 * 
                 * @param {String} str  源字符串
                 * @returns {Number}  返回长度
                 * 
                 */
                getByteLen : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    var bytelen = 0, i = 0, length = str.length, cc = document.charset;
                    if (!cc) { cc = 'utf-8'; }
                    cc = cc.toLowerCase();
                    var s = cc == 'iso-8859-1' ? 5 : 2;
                    for (; i < length; i++) {
                        bytelen += str.charCodeAt(i) > 255 ? s : 1;
                    }
                    return bytelen;
                },
                /** 
                 * ### 描述
                 * 
                 * 截取字符串左边指定字节长度内容
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.leftBytes('abc您好',5); //UTF-8编码时返回'abc您'
                 * 
                 * @param {String} str  源字符串
                 * @param {Number} len 截取字节长度
                 * @returns {String}  返回截取后的字符串副本
                 * 
                 */
                leftBytes : function (str, len) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    if (!_check.isNumeric(len)) {
                        len = parseInt(len, 10);
                    }
                    var length = _singletonInstance.getByteLen(str), i = 0, bytelen = 0, cc = document.charset;
                    if (!cc) { cc = 'utf-8'; }
                    cc = cc.toLowerCase();
                    var s = cc == 'iso-8859-1' ? 5 : 2;
                    if (len < 0 || len > length) { len = length; }
                    for (; i < str.length; i++) {
                        bytelen += str.charCodeAt(i) > 255 ? s : 1;
                        if (bytelen == len) { break; }
                        if (bytelen > len) { i--; break; }
                    }
                    return _singletonInstance.left(str, i + 1);
                },
                /** 
                 * ### 描述
                 * 
                 * 截取字符串右边指定字节长度内容
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.rightBytes('abc您好',4); //UTF-8编码时返回'您好'
                 * 
                 * @param {String} str  源字符串
                 * @param {Number} len 截取字节长度
                 * @returns {String}  返回截取后的字符串副本
                 * 
                 */
                rightBytes : function (str, len) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    if (!_check.isNumeric(len)) {
                        len = parseInt(len, 10);
                    }
                    var length = _singletonInstance.getByteLen(str), i = 0, bytelen = 0, cc = document.charset;
                    if (!cc) {
                        cc = 'utf-8';
                    }
                    cc = cc.toLowerCase();
                    var s = cc == 'iso-8859-1' ? 5 : 2;
                    if (len < 0 || len > length) { len = length; }
                    for (; i < str.length; i++) {
                        bytelen += str.charCodeAt(str.length - 1 - i) > 255 ? s : 1;
                        if (bytelen == len) {
                            break;
                        }
                        if (bytelen > len) {
                            i--;
                            break;
                        }
                    }
                    return _singletonInstance.right(str, i + 1);
                },
                /** 
                 * ### 描述
                 * 
                 * 截取字符串子串（字节方式）
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.midBytes('abc您好',4,2); //UTF-8编码时返回'您'
                 * 
                 * @param {String} str  源字符串
                 * @param {Number} start 起始的字节下标，从0开始
                 * @param {Number} len 截取字节长度
                 * @returns {String}  返回截取后的字符串副本
                 * 
                 */
                midBytes : function (str, start, len) {
                    if (!start) { start = 0; }
                    if (!len) { len = 0; }
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    if (!_check.isNumeric(start)) {
                        start = parseInt(start, 10);
                    }
                    if (!_check.isNumeric(len)) {
                        len = parseInt(len, 10);
                    }
                    var length = _singletonInstance.getByteLen(str);
                    if (start < 0) {
                        start = 0;
                    } else if (start > length){
                        start = length -1;
                    }
                    if (len < 0 || len > length) {
                        len = length;
                    }
                    return _singletonInstance.leftBytes(_singletonInstance.rightBytes(str, length-start),len);
                },
                /** 
                 * ### 描述
                 * 
                 * 字符串反转
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.reverse('abc'); //返回'cba'
                 * 
                 * @param {String} str  源字符串
                 * @returns {String}  返回反转后的字符串副本
                 * 
                 */
                reverse : function (str) {
                    var charArray = [];
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    for (var i = str.length - 1; i > -1; i--) {
                        charArray.push(str[i]);
                    }
                    return charArray.join('');
                },
                /** 
                 * ### 描述
                 * 
                 * 转为全角字符串
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.toCase('abc'); //返回'ａｂｃ'
                 * 
                 * @param {String} str  源字符串
                 * @returns {String}  返回处理后的字符串副本
                 * 
                 */
                toCase : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    var tmp = '';
                    for (var i = 0; i < str.length; i++) {
                        if (str.charCodeAt(i) > 0 && str.charCodeAt(i) < 255) {
                            tmp += String.fromCharCode(str.charCodeAt(i) + 65248);
                        }
                        else {
                            tmp += String.fromCharCode(str.charCodeAt(i));
                        }
                    }
                    return tmp;
                },
                /** 
                 * ### 描述
                 * 
                 * 字符串转换为日期对象
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var curDate = $b.String.toDate('2016/01/01'); 
                 * 
                 * @param {String} str  日期字符串
                 *   格式： 'yyyy-MM-dd' 或 'yyyy/MM/dd' 也可以是长日期格式: 'yyyy-MM-dd hh:mm:ss'
                 * @returns {Date}  返回日期对象
                 * 
                 */
                toDate : function (str) {
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str);
                    try { 
                        return new Date(str.replace(/-/g, "\/")); 
                    } catch (e) { 
                        return null; 
                    }
                },
                /** 
                 * ### 描述
                 * 
                 * 字符串换为布尔值
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     var res = $b.String.toBoolean('是'); //返回true
                 * 
                 * @param {String} str  源字符串
                 *   以下字符串会被转换为true: 
                 *   - 'true'
                 *   - 'yes'
                 *   - 'y'
                 *   - '是'
                 *   - '开'
                 *   - 'on'
                 *   - '1'
                 * @returns {Boolean}  返回对应的布尔值
                 * 
                 */
                toBoolean : function (str) {
                    if (typeof str == 'boolean') { return str; }
                    str = _check.isNullOrEmptyStr(str) ? '' : String(str).toLowerCase();
                    str = trim(str);
                    return str === 'true' || str === 'yes' || str === 'y'
                        || str == 'on' || str == '1' || str === '是' || str === '开';
                },
                /**
    	    	 * ### 描述
    	    	 * 动态转换金额字段的值为三位三位分隔处理，注意：会对金额进行四舍五入处理
    	    	 * 
    	    	 * ### 示例
    	    	 * 
    	    	 *     @example
    	    	 *     $b.String.toSplitMoney(value, 2);//对金额格式化，保留两位小数
    	    	 * 
    		     * @param {String} money 传入的金额
    		     * @param {String} precision 需要进行格式化精度
    	    	 * @returns {String} 转换后的字符串
    	    	 * @member $b.String
    	    	 * @method toSplitMoney
    	    	 * @author luow 2016-5-20
    	    	 * 
    	    	 */
                toSplitMoney: function (money, precision) {
                	precision = precision > 0 && precision <= 20 ? precision : 2;
                    money = parseFloat((money + "").replace(/[^\d\.-]/g, "")).toFixed(precision) + "";
                    var l = money.split(".")[0].split("").reverse(), r = money.split(".")[1];
                    t = "";
                    for (i = 0; i < l.length; i++) {
                        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
                    }
                    return t.split("").reverse().join("") + "." + r;
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
        instance : function(){
             return Str.getInstance() ;
        }
    };
});