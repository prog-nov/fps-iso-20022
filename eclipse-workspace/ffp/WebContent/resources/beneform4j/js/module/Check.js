define(function(require) {
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
     * @class $b.Check
     * @requires $b
     * @singleton
     */
    var Check = (function() {
        // 定义一个私有单例对象
        var singletonInstance ,$;// undefined
        var initInstance = function() {
            $  = require('jquery');
            return {
                /** 
                 * ### 描述
                 * 
                 * 判断对象是否为jQuery对象
                 *   
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isJqueryObject($test);
                 * 
                 * @param {Object} obj  要检测的对象
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isJqueryObject : function(obj) {
                    return obj != null
                            && obj != undefined
                            && ((obj.jquery ? true : false) || obj.constructor === $.constructor);
                },
                /** 
                 * ### 描述
                 * 
                 * 判断对象是否为一个空的jQuery对象
                 *   
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isEmptyJqueryObject($test);
                 * 
                 * @param {Object} obj  要检测的对象
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isEmptyJqueryObject : function(obj) {
                    return singletonInstance.isJqueryObject(obj) && !obj.length;
                },
                /** 
                 * ### 描述
                 * 
                 * 判断是否为空串
                 *   
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isNullOrEmptyStr(str);
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isNullOrEmptyStr : function(str) {
                    return str === undefined || str === null || str === '';
                },
                /** 
                 * ### 描述
                 * 
                 * 判断是否为空串或全部为空格
                 *   
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isNullOrWhiteSpace('   '); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isNullOrWhiteSpace : function(str) {
                    return singletonInstance.isNullOrEmptyStr(str)
                            || $.trim(String(str)) === '';
                },
                /** 
                 * ### 描述
                 * 
                 * 检查传入的字符串是否为整数
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isInteger('123'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回是否为整数
                 * 
                 */
                isInteger : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[+]?[1-9]+\d*$/i.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 检查传入的字符串是否为数值
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isNumeric('-123', '-i'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @param {String} flag 要判定的具体类型
                 * 取值有：
                 *   - '+' : 正数
                 *   - '-' : 负数
                 *   - 'i' : 整数
                 *   - '+i': 正整数
                 *   - '-i': 负整数
                 *   - 'f' : 浮点数
                 *   - '+f': 正浮点数
                 *   - '-f': 负浮点数
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isNumeric : function(str, flag) {
                    if (arguments.length == 0) {
                        return false;
                    }
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    // 验证是否是数字
                    if (isNaN(str)) {
                        return false;
                    }
                    switch (flag) {
                    case '':
                        return true;
                    case '+': // 正数
                        return /(^\+?|^\d?)\d*\.?\d+$/.test(str);
                    case '-': // 负数
                        return /^-\d*\.?\d+$/.test(str);
                    case 'i': // 整数
                        return /(^-?|^\+?|[0-9])[0-9]+$/.test(str);
                    case '+i': // 正整数
                        return /(^\d+$)|(^\+?\d+$)/.test(str);
                    case '-i': // 负整数
                        return /^[-]\d+$/.test(str);
                    case 'f': // 浮点数
                        return /(^-?|^\+?|^\d?)\d*\.\d+$/.test(str);
                    case '+f': // 正浮点数
                        return /(^\+?|^\d?)\d*\.\d+$/.test(str);
                    case '-f': // 负浮点数
                        return /^[-]\d*\.\d$/.test(str);
                    default: // 缺省
                        return true;
                    }
                },
                /** 
                 * ### 描述
                 * 
                 * 判断传入的字符串是否为货币格式
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isCurrency('123.03'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isCurrency : function(str) {
                    if (singletonInstance.isNullOrEmptyStr(str)) {
                        return false;
                    }
                    return /^\d{0,}(\.\d+)?$/i.test(String(str));
                },
                /** 
                 * ### 描述
                 * 
                 * 判断传入的字符串是否为长日期格式
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isLongDate('2016-01-01 10:00:00'); //返回true
                 * 
                 * 长日期格式：yyyy-MM-dd hh:mm:ss 或 yyyy/MM/dd
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isLongDate : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    var r = str
                            .replace(/(^\s*)|(\s*$)/g, '')
                            .match(
                                    /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
                    if (r == null) {
                        return false;
                    }
                    var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
                    return (d.getFullYear() == r[1]
                            && (d.getMonth() + 1) == r[3]
                            && d.getDate() == r[4] && d.getHours() == r[5]
                            && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
                },
                /** 
                 * ### 描述
                 * 
                 * 判断传入的字符串是否为合法的短日期格式
                 *  
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isShortDate('2016-01-01'); //返回true
                 * 
                 * 短日期格式：yyyy-MM-dd 或 yyyy/MM/dd
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isShortDate : function(str) {
                    str = isNullOrEmptyStr(str) ? '' : String(str);
                    var r = str.replace(/(^\s*)|(\s*$)/g, '').match(
                            /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
                    if (r == null) {
                        return false;
                    }
                    var d = new Date(r[1], r[3] - 1, r[4]);
                    return (d.getFullYear() == r[1]
                            && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
                },
                /** 
                 * ### 描述
                 * 
                 * 判断传入的字符串是否为合法的日期格式（即是否为长日期格式或短日期格式）
                 *  
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isDate('2016-01-01'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isDate : function(str) {
                    return singletonInstance.isLongDate(str) || singletonInstance.isShortDate(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的固定电话号码格式（中国）
                 *  
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isTel('755-22331346'); //返回true
                 * 
                 * 固定电话号码通用格式为 区号+主号码+分机号，其中区号和分机号为可选项
                 * @param {String} str  电话号码
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isTel : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
                            .test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的传真号码（中国）
                 *  
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isFax('755-22331346'); //返回true
                 * 
                 * 同固定电话号码判定
                 * @param {String} str  传真号码
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isFax : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
                            .test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的11位手机号码格式（中国）
                 *  
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isMobile('13712345678'); //返回true
                 * 
                 * @param {String} str  手机号码
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isMobile : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^(13|14|15|17|18)\d{9}$/i.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的电话号码或手机号码（中国）
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isTelOrMobile('13712345678'); //返回true
                 *     $b.Check.isTelOrMobile('755-22331346'); //返回true
                 * 
                 * @param {String} str  电话号码或手机号码
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isTelOrMobile : function(str) {
                    return singletonInstance.isTel(str)
                            || singletonInstance.isMobile(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的电子邮箱格式
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isEmail('test@formssi.com'); //返回true
                 * 
                 * @param {String} str  电子邮箱
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isEmail : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
                            .test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的邮政编码格式（中国）
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isZipCode('518000'); //返回true
                 * 
                 * @param {String} str  邮政编码
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isZipCode : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[\d]{6}$/.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 字符串中是否存在汉字
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.existChinese('abc测试def'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                existChinese : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    // [\u4E00-\u9FA5]为汉字﹐[\uFE30-\uFFA0]为全角符号
                    return !/^[\x00-\xff]*$/.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为全部中文
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isChinese('中文测试'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isChinese : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[\u0391-\uFFE5]+$/i.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为全部英文
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isEnglish('hello'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isEnglish : function(str) {
                    str = isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[A-Za-z]+$/i.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的文件名
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isFileName('test.txt'); //返回true
                 * 
                 * 合法文件名：不能包含字符\/*?:<>
                 * @param {String} str  要检测的文件名
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isFileName : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return !/[\\\/\*\?\|:<>]/g.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的IPv4地址
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isIPv4('127.0.0.1'); //返回true
                 * 
                 * @param {String} str  要检测的IP地址
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isIPv4 : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
                    if (reSpaceCheck.test(str)) {
                        str.match(reSpaceCheck);
                        if (RegExp.$1 <= 255 && RegExp.$1 >= 0
                                && RegExp.$2 <= 255 && RegExp.$2 >= 0
                                && RegExp.$3 <= 255 && RegExp.$3 >= 0
                                && RegExp.$4 <= 255 && RegExp.$4 >= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的URL地址
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isUrl('http://www.formssi.com'); //返回true
                 * 
                 * @param {String} str  要检测的URL地址
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isUrl : function(str) {
                    str = singletonInstance.isNullOrEmpty(str) ? "" : String(str);
                    var strRegex = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
                    var re = new RegExp(strRegex);
                    return re.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的QQ号码
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isQQ('840211818'); //返回true
                 * 
                 * @param {String} str  要检测的字符串
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isQQ : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[1-9]\d{4,11}$/i.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的车牌号码
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isCarNo('粤B888888'); //返回true
                 * 
                 * @param {String} str  要检测的车牌号
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isCarNo : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的汽车发动机号码（VIN，17位车架号）
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isCarEngineNo('LSGJR52U85S110284'); //返回true
                 * 
                 * @param {String} str  要检测的发动机号
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isCarEngineNo : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[a-zA-Z0-9]{17}$/.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的用户名
                 * 
                 * ### 示例
                 * 
                 *     @example
                 *     $b.Check.isUserName('test1'); //返回true
                 * 
                 * 以字母打头，长度为4~16位，包含字母大小写、数字和下划线
                 * @param {String} str  要检测的用户名
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isUserName : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    return /^[a-zA-Z][a-zA-Z0-9_]{3,15}$/i.test(str);
                },
                /** 
                 * ### 描述
                 * 
                 * 是否为合法的身份证号
                 * 身份证号的组成：2位省份（直辖市）代码+4位地区码+8位出生年月日+3位顺序号+1位校验码
                 * @param {String} str  要检测身份证号
                 * @returns {Boolean}  返回检测结果
                 * 
                 */
                isIDCard : function(str) {
                    str = singletonInstance.isNullOrEmptyStr(str) ? '' : String(str);
                    var iSum = 0, sId = str, aCity = { // 代码中只用到了以下数据的key，因此此处中文可以不用国际化
                        11 : '北京',
                        12 : '天津',
                        13 : '河北',
                        14 : '山西',
                        15 : '内蒙古',
                        21 : '辽宁',
                        22 : '吉林',
                        23 : '黑龙江',
                        31 : '上海',
                        32 : '江苏',
                        33 : '浙江',
                        34 : '安徽',
                        35 : '福建',
                        36 : '江西',
                        37 : '山东',
                        41 : '河南',
                        42 : '湖北',
                        43 : '湖南',
                        44 : '广东',
                        45 : '广西',
                        46 : '海南',
                        50 : '重庆',
                        51 : '四川',
                        52 : '贵州',
                        53 : '云南',
                        54 : '西藏',
                        61 : '陕西',
                        62 : '甘肃',
                        63 : '青海',
                        64 : '宁夏',
                        65 : '新疆',
                        71 : '台湾',
                        81 : '香港',
                        82 : '澳门',
                        91 : '国外'
                    };
                    if (!/^\d{17}(\d|x)$/i.test(sId)) {
                        return false;
                    }
                    sId = sId.replace(/x$/i, 'a');
                    // 非法地区
                    if (aCity[parseInt(sId.substr(0, 2), 10)] == null) {
                        return false;
                    }
                    var sBirthday = sId.substr(6, 4) + '-'
                            + Number(sId.substr(10, 2)) + '-'
                            + Number(sId.substr(12, 2)), d = new Date(sBirthday
                            .replace(/-/g, '/'));
                    // 非法生日
                    if (sBirthday != (d.getFullYear() + '-'
                            + (d.getMonth() + 1) + '-' + d.getDate())) {
                        return false;
                    }
                    for (var i = 17; i >= 0; i--) {
                        iSum += (Math.pow(2, i) % 11)
                                * parseInt(sId.charAt(17 - i), 11);
                    }
                    if (iSum % 11 != 1) {
                        return false;
                    }
                    return true;
                }

            }
        };
        return {
            getInstance : function() {
                // 如果单例对象不存在，则建立此对象，如果存在，直接返回
                if (!singletonInstance) {
                    singletonInstance = initInstance();
                }
                return singletonInstance;
            }
        };
    })();
    return {
        instance : function(){
             return Check.getInstance() ;
        }
    };
});