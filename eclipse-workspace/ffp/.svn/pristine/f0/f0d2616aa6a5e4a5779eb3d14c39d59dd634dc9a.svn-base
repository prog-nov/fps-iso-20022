/* Common Function */

var displayMessage = function(selector, message, status){
	if(!!selector && !!message){
		if(status == 2){
			//Error
			$(selector).addClass("alert-danger");
			$(selector).removeClass("alert-warning");
			$(selector).removeClass("alert-success");
		} else if(status == 1){
			//Warn
			$(selector).removeClass("alert-danger");
			$(selector).addClass("alert-warning");
			$(selector).removeClass("alert-success");
		} else {
			//Success
			$(selector).removeClass("alert-danger");
			$(selector).removeClass("alert-warning");
			$(selector).addClass("alert-success");
		}
		
		$(selector).html(message);
		
		$(selector).show();
	}
}

var callAjax = function(config){
	
	if(!config){
		return;
	}
	
	if(!config.messageAreaSelector){
		config.messageAreaSelector = "#messageArea";
	}
	
	if(!config.dataType){
		config.dataType = "json";
	}
	
	if(config.nocache === undefined){
		config.nocache = true;
	}
	
	if(config.nocache){
		if(config.url.indexOf("?") != -1){
			config.url = config.url + "&nocache=" + new Date().getTime();
		}else{
			config.url = config.url + "?nocache=" + new Date().getTime();
		}
		
	}
	
	var isJson = config.dataType == "json";

	$(config.messageAreaSelector).hide();
	
	
	var successCallback = config.success;
	var errorCallback = config.error;
	
	config.success = function(data, textStatus, response){
		if(!!data && isJson){
     		//Display Message
			displayMessage(config.messageAreaSelector, data.message, data.messageStatus);
     	}
		successCallback(data, textStatus, response);
	};
	
	config.error = function(response, textStatus, errorThrown){
		displayMessage(config.messageAreaSelector, "Server Error [" + response.status + "]", 2);
		errorCallback(response, textStatus, errorThrown);
	};
	
	$.ajax(config);
	
};


var getFormData = function($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
    	if(n['name'] == 'messageId'){
    		//Do not export message id to form data so that it remain unchange when importing data
    		return;
    	}
    	
    	var field = $("[name='" + n['name'] + "']");
    	if($(field).is("input[type=checkbox]") && !!indexed_array[n['name']]){
    		//Check box array handling
    		indexed_array[n['name']] = [indexed_array[n['name']]];
    		indexed_array[n['name']].push(n['value']);
    	}else{
    		indexed_array[n['name']] = n['value'];
    	}
        
    });
    console.log("Get Form", indexed_array);
    return indexed_array;
};


var setFormData = function($form, data){
	console.log("Set Form", data);
	for(var key in data){
		var value = data[key];
		
		var el = $($($form)[0][key]).not("[disabled]");
		
		if($(el).length == 0 || $(el).is("[readonly]")){
			continue;
		}
		
		
		if($(el).parents(".listInput-element-container").length > 0){
			if(key.match(/\..*\[0\]/g)){
				var listInputContainer = $(el).parents(".listInput-container")[0];
				var seq = $(listInputContainer).data().seq;
				if(!!window.listInputStore[seq]){
					var hasNext = true;
					var size = 0;
					while(hasNext){
						var testKey = key.replace(/(.*\[)[0-9]+(\].*)/g, "$1" + size + "$2");
						if(!data[testKey]){
							console.info("setFormData no [" + testKey + "]");
							hasNext = false;
						}else{
							console.info("setFormData found [" + testKey + "]");
							size++;
						}
						
					}
					window.listInputStore[seq].setSize(size);
					
				}
			}
		}
		
		if($(el).is(":input[type=radio]")){
			var radioEl = $(el).filter("[value='" + value + "']").prop("checked", true);
			
			console.info("setFormData trigger click event to radio", radioEl);
			radioEl.trigger("click");
			
		}else if($(el).is(":input[type=checkbox]")){
			if(Array.isArray(value)){
				for(var i=0; i<value.length; i++){
					var checkBoxEl = $(el).filter("[value='" + value[i] + "']");
					if(!$(checkBoxEl).is(":checked")){
						console.info("setFormData trigger click event to checkbox", checkBoxEl);
						$(checkBoxEl).trigger("click");
					}
					
				}
			}else{
				var checkBoxEl = $(el).filter("[value='" + value + "']");
				if(!$(checkBoxEl).is(":checked")){
					console.info("setFormData trigger click event to checkbox", checkBoxEl);
					$(checkBoxEl).trigger("click");
				}
			}
			
		}else{
			$(el).val(value);
			$(el).change();
		}
		
	}
	
};

var download = function(filename, data, type) {
    var file = new Blob([data], {type: type});
    if (window.navigator.msSaveOrOpenBlob) // IE10+
        window.navigator.msSaveOrOpenBlob(file, filename);
    else { // Others
        var a = document.createElement("a"),
                url = URL.createObjectURL(file);
        a.href = url;
        a.download = filename;
        document.body.appendChild(a);
        a.click();
        setTimeout(function() {
            document.body.removeChild(a);
            window.URL.revokeObjectURL(url);  
        }, 0); 
    }
};


/* Common Function for Object Classes */

var escapeRegExp = function(str) {
  return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
};

var replaceAll = function(str, search, replacement){
	return str.replace(RegExp(escapeRegExp(search), 'g'), replacement);
};

var getValueByPath = function(path, data){
	var value = undefined;
	try{
		value = eval("data." + path);
	}catch(e){}
	if(value == undefined){
		value = "";
	}
	
	return value;
};

var getValuesByKeysPath = function(keys, data){
	
	var result = {};
	for(var i=0; i<keys.length; i++){
		var key = keys[i];
		var value = getValueByPath(key, data);
		result[key] = value;
	}
	return result;
	
};
var findKeysFromScript = function(script){
	//Remove script in quote
	var quoteRegex = /('|").*?(\1)/g;
	script = script.replace(quoteRegex, "");
	
	var regex = /([_a-zA-Z0-9\[\]]+(?:\.)?)+/g;
	var result = [];
	while ((m = regex.exec(script)) !== null) {
		if (m.index === regex.lastIndex) {
			regex.lastIndex++;
		}
		
		var key = m[0];
		
		// Check if start with character
		if(key.match(/^[_a-zA-Z]/)){
			result.push(key);
		}
		
	}
	
	return result;

};

var evalScript = function(script, data){

	console.info("Evaluating " + script, data);
	
	//Replace string inside quote to place holder
	var quoteRegex = /('|").*?(\1)/g;
	
	var quoteTemp = [];
	var tempScript = script;
	while((m = quoteRegex.exec(script)) !== null){
		if (m.index === quoteRegex.lastIndex) {
			quoteRegex.lastIndex++;
		}

		var index = quoteTemp.length;
		quoteTemp.push(m[0]);
		tempScript = replaceAll(tempScript, m[0], "{#" + index + "}");
		
	}
	
	//Eval script
	var keys = findKeysFromScript(script);
	var keyValueMap = getValuesByKeysPath(keys, data);
	
	console.info("KeyValueMap from script", keyValueMap);

	for(var key in keyValueMap){
		var value = keyValueMap[key];
		if(typeof value === "string"){
			value = value.replace(/"/g, '\\"');
			value = '"' + value + '"';
		}
		tempScript = replaceAll(tempScript, key, value);
	}

	//Replace back string in quote
	for(var i=0; i<quoteTemp.length; i++){
		tempScript = replaceAll(tempScript, "{#" + i + "}", quoteTemp[i]);
	}

	console.info("Final Script " + tempScript, data);
	
	return eval(tempScript);

};

var delay = (function(){
  var timer = 0;
  return function(callback, ms){
    clearTimeout (timer);
    timer = setTimeout(callback, ms);
  };
})();


/**
 *	Object Class - Bind Event
 *	This class control event binding to a DOM element
 */

var BindEvent = function(el, type, handler){
	this.el = el;
	this.type = type;
	this.handler = handler;
	
	var self = this;
	
	this.bind = function(){
		console.log("BindEvent bind [" + self.type + "]", self.el);
		$(self.el).on(self.type, self.handler);
	}
	
	this.unbind = function(){
		console.log("BindEvent unbind [" + self.type + "]", self.el);
		$(self.el).off(self.type, self.handler);
	}
	
	this.bind();
	
}

/**
 *	Object Class - DataBinder
 *	This class allow javascript object bind with DOM element
 *	It is add event listener to element that will change the value
 *	and update all DOM elment that bind to a certain value
 *
 *  Usage Example:
 *  
 *  1. Bind Text
 *  	HTML:		<div id="container"><font data-bind-text="data.name" /></div>
 *  	JS:			var obj = {data: {name: "Bod" }};
 *  				new DataBinder(obj).init($("#container"));
 *  	OUTPUT:		<div id="container"><font data-bind-text="data.name" />Bod</font></div>
 *  
 *  Whenever data.name is changed and binder is notified, it will also update the html text inside <font> tag
 *  
 *  2. Bind Value
 *  	HTML:		<div id="container"><input type="text" name="name" data-bind-value="data.name" /></div>
 *  	JS:			var obj = {data: {name: "Bod" }};
 *  				new DataBinder(obj).init($("#container"));
 *  	OUTPUT:		<div id="container"><input type="text" name="name" data-bind-value="data.name" value="Bod" /></div>
 *  
 *  Whenever the input field value is changed, it will also update data.name in obj and notify the binder to update all binded element.
 *  
 *  3. Bind Attribute
 *  	HTML:		<div id="container"><div data-bind-name="data.name"></div></div>
 *  	JS:			var obj = {data: {name: "Bod" }};
 *  				new DataBinder(obj).init($("#container"));
 *  	OUTPUT:		<div id="container"><p data-bind-name="data.name" name="Bod"></p></div>
 *  
 *  Whenever data.name is changed and binder is notified, it will also update the name attribute of the <p> tag
 *  
 *  4. Notify Binder
 *  	HTML:		<div id="container"><font data-bind-text="data.name" /></div>
 *  	JS:			var obj = {data: {name: "Bod" }};
 *  				var binder = new DataBinder(obj).init($("#container"));
 *  				obj.data.name = "Peter";
 *  				binder.notify("data.name");
 *  	OUTPUT:		<div id="container"><font data-bind-text="data.name" />Peter</font></div>
 *  
 *  After the value data.name is updated programatically, notify binder the update all binded element
 *  	
 */

var DataBinder = function(data){
	this.data = data;
	this.bindMap = {};
	this.bindedEvent = [];
	
	var self = this;
	
	this.init = function(selector){
		console.info("DataBinder Init", selector)
		
		//Scan all element
		$.each($(selector).find("*"), function(){
			var dataList = $(this).data();
			for(var k in dataList){
				if(k.indexOf("bind") != 0){
					continue;
				}
				
				var script = dataList[k];
				var keys = findKeysFromScript(script);
				
				console.info("Found [" + k + "]", keys, this);
				
				for(var i=0; i<keys.length; i++){
					self.subscribeBinding(keys[i], $(this));
				}
			}
			
		});
		
		self.refresh();

	};
	
	this.refresh = function(){
		console.info("DataBinder refresh", this);
		for(var k in self.bindMap){
			self.notify(k);
		}
	}
	
	
	this.notify = function(key){
		console.info("DataBinder notify key = [" + key + "]", this);
		var bindList = this.bindMap[key];
		if(!bindList){
			console.debug("Binding not found for key " + key);
			return;
		}
		
		
		
		for(var i=0; i<bindList.length; i++){
			var el = bindList[i];
			console.info("Notifying [" + key + "] Element Name = [" + $(el).attr("name") + "]", el);
			var dataList = $(el).data();
			for(var k in dataList){
				if(k.indexOf("bind") != 0){
					continue;
				}
				
				var attrName = k.replace("bind", "")
				attrName = attrName[0].toLowerCase() + attrName.substr(1);
				
				var value = evalScript(dataList[k], self.data);
				
				if(attrName == "text"){
					$(el).html(value);
				}else if(attrName == "value"){
				
					$(el).val(value);
					
				}else if(attrName == "radio"){
					if($(el).val() == value){
						$(el).prop("checked", true);
					}else{
						$(el).prop("checked", false);
					}
				}else{
					if(attrName.indexOf("data") == 0){
						attrName = attrName.replace(/[A-Z]/g, function(v){
							return "-" + v.toLowerCase();
						})
					}
					$(el).attr(attrName, value);
				}

				
			}

		}
		
	};
	
	this.subscribeBinding = function(key, el){
		var bindList = this.bindMap[key];
		if(!bindList){
			bindList = [];
			this.bindMap[key] = bindList;
		}
		
		if(bindList.indexOf(el) == -1){
			console.info("DataBinder subscribeBinding [" + key + "]", el);
			bindList.push(el);
		}
		
		//Add event listenr to element that may change the binded value
		if(!!$(el).attr("data-bind-value") && $(el).is(":input")){
			var script = $(el).attr("data-bind-value");
			var keys = findKeysFromScript(script);
			if(keys.length > 1){
				//The data-bind-value has more than one key, do not update the binded value when change.
				return;
			}
			
			//Add event listener to update binded value and notify
			var bindEventTypes = {};
			var delayTime = 0;
			
			if($(el).is("input")){
			
				if($(el).is("[type='radio']") || $(el).is("[type='checkbox']")){
					//radio button and checkbox will not change the bind value with data-bind-value
					return;
				}else{
					bindEventTypes["keyup"] = {delay: 200};
					bindEventTypes["change"] = {};
					delayTime = 200;
				}
				
			} else if ($(el).is("select")){
				bindEventTypes["change"] = {};
			} 
			if(Object.keys(bindEventTypes).length > 0){
				for(var eventType in bindEventTypes){
					var handler = function(){
						console.info("DataBinder bindEvent Triggered bindEventType = [" + eventType + "]", el);
						var script = $(el).attr("data-bind-value");
						var keys = findKeysFromScript(script);
						var value = $(el).val();

						try{
							var startPos = el.selectionStart;
							var endPos = el.selectionEnd;
						}catch(e){}
						
						
						self.updateData(keys[0], value);
						
						if(!!startPos){
							evntObj.setSelectionRange(startPos, endPos);
						}
					};
					
					if(!!bindEventTypes[eventType].delay){
						var eventHandler = handler;
						handler = function(){
							delay(eventHandler, bindEventTypes[eventType].delay);
						}
						
					};
					
					self.bindedEvent.push(new BindEvent(el, eventType, handler));
					
				}
				
			}
		}
		
		if(!!$(el).attr("data-bind-radio") && $(el).is("input[type=radio]")){
			var script = $(el).attr("data-bind-radio");
			var keys = findKeysFromScript(script);
			if(keys.length > 1){
				//The data-bind-radio has more than one key, do not update the binded value when change.
				return;
			}
			
			var bindEventType = "click";
			if(!!bindEventType){
				var handler = function(){
					var script = $(this).attr("data-bind-radio");
					var keys = findKeysFromScript(script);
					var value = $(el).val();

					self.updateData(keys[0], value);
				}
				
				self.bindedEvent.push(new BindEvent(el, bindEventType, handler));
			}
		}
		
		if(!!$(el).attr("data-bind-checked") && $(el).is("input[type='checkbox']")){
			var script = $(el).attr("data-bind-checked");
			var keys = findKeysFromScript(script);
			if(keys.length > 1){
				//The data-bind-checked has more than one key, do not update the binded value when change.
				return;
			}
			
			var bindEventType = "click";
			if(!!bindEventType){
				var handler = function(){
					var script = $(this).attr("data-bind-checked");
					var keys = findKeysFromScript(script);
					var value = $(el).is(":checked");

					self.updateData(keys[0], value);
				}
				
				self.bindedEvent.push(new BindEvent(el, bindEventType, handler));
			}

		}

	};
	
	
	this.updateData = function(key, value){
		console.info("DataBinder updateData key = [" + key + "], value = [" + value + "]");
		eval("self.data." + key + "=value");
		self.notify(key);
	};
	
	this.unbindAll = function(){
		console.info("DataBinder unbindAll", this);
		for(var i=0; i<self.bindedEvent.length; i++){
			self.bindedEvent[i].unbind();
		}			
	};


}

/**
 *  Object Class - ListInput
 *  This class control list input
 *  Usage Example
 *  
 *  HTML:
 *  <div id="container" class="listInput-container">
 *  	<div class="listInput-config">
 *			<div class="listInput-data-config">
 *				<p data-name="max" data-value="3" />
 *				<p data-name="min" data-value="1" />
 *			</div>
 *			<li class="listInput-initData">
 *				<ol>
 *					<p data-name="name" data-value="Bod" />
 *					<p data-name="age" data-value="18" />
 *				</ol>
 *				<ol>
 *					<p data-name="name" data-value="Peter" />
 *					<p data-name="age" data-value="60" />
 *				</ol>
 *			</li>	
 *			<div class="listInput-template">
 *				# <font data-bind-text="data.index + 1"></font>
 *				<input type="text" data-bind-value="data.name" />
 *				<input type="text" data-bind-value="data.age" />
 *				<button type="button" class="listInput-removeBtn">Remove</button>
 *			</div>
 *		<div>
 *		<div class="listInput-after">
 *			<button type="button" class="listInput-addBtn">Add</button>
 *		</div>
 * </div>
 * 
 * JS:
 * new ListInput($("#container"), seq);
 * 
 * OUTPUT:
 * 
 * <div id="container" class="listInput-container">
 * 		<div class="listInput-element-container">
 *				# <font data-bind-text="data.index + 1">1</font>
 *				<input type="text" data-bind-value="data.name" value="Bod" />
 *				<input type="text" data-bind-value="data.age" value="18"/>
 *				<button type="button" class="listInput-removeBtn">Remove</button>
 *		</div>
 * 		<div class="listInput-element-container">
 *				# <font data-bind-text="data.index +1">2</font>
 *				<input type="text" data-bind-value="data.name" value="Peter" />
 *				<input type="text" data-bind-value="data.age" value="60"/>
 *				<button type="button" class="listInput-removeBtn">Remove</button>
 *		</div>
 *		<div class="listInput-after">
 *			<button type="button" class="listInput-addBtn">Add</button>
 *		</div>
 *</div>
 * 
 */

var substitute = function(template, map){
	var regex = /{{(?!{)(.*?)}}/g;
	var result = template;
		
	while ((m = regex.exec(template)) !== null) {
		if (m.index === regex.lastIndex) {
			regex.lastIndex++;
		}
		var placeHolder = m[0];
		var script = m[1];

		var value = evalScript(script, map);
		result = replaceAll(result, placeHolder, value);
	}

	return result;
}


var ListInput = function(containerEl, seq){
	this.seq = seq;
	this.containerEl = containerEl;
	this.configEl = $(containerEl).find(".listInput-config");
	this.config = {};
	this.dataList = [];
	this.binders = [];
	
	var self = this;

	this.add = function(data){
		console.info("ListInput add", data);
		if(!!self.config.max && self.dataList.length >= self.config.max){
			self.updateBtnEnable();
			return;
		}
		
		var index = self.dataList.length;
		data['index'] = index;
		self.dataList.push(data);

		var template = $(self.configEl).children(".listInput-template").html();
		var map = {
			data: data,
			config: self.config
		};

		template = substitute(template, map);
		
		var templateEl = $(template);
		$(templateEl).attr('data-index', index);
		
		var binder = new DataBinder(map);
		binder.init($(templateEl));
		self.binders.push(binder);
		
		var afterEl = $(self.containerEl).children('.listInput-after');
		if(!!afterEl && afterEl.length > 0){
			$(afterEl).before(templateEl);
		}else{
			$(self.containerEl).append(templateEl);
		}
		
		self.updateBtnEnable();

	};
	
	this.remove = function(index){
		console.info("ListInput remove index = " + index);
		if(!!self.config.max && self.dataList.length <= self.config.min){
			self.updateBtnEnable();
			return;
		}
		
		
		//Update View
		var elementContainerEl = $(self.containerEl).children('.listInput-element-container[data-index=' + index + ']');
		$(elementContainerEl).remove();
		
		$.each($(self.containerEl).children('.listInput-element-container'), function(i, el){
			$(el).attr('data-index', i);
		});
		
		//Update Data binding
		self.dataList.splice(index, 1);
		self.binders.splice(index, 1);
		
		for(var i=0; i<self.dataList.length; i++){
			var data = self.dataList[i];
			data.index = i;
		}
		
		for(var i=0; i<self.binders.length; i++){
			var binder = self.binders[i];
			binder.updateData('data.index', i);
		}
		
		self.updateBtnEnable();
		
	
	};
	
	this.pop = function(){
		console.info("ListInput pop", this);
		var lastIndex = self.dataList.length - 1;
		var obj = self.dataList[lastIndex];
		self.remove(lastIndex);
		
		return obj;
	};
	
	
	this.setSize = function(size){
		console.info("ListInput setSize to " + size, this);
		if(self.dataList.length < size){
			var addSize = size - self.dataList.length;
			for(var i=0; i < addSize; i++){
				self.add({});
			}
		} else if (self.dataList.length > size){
			var removeSize = self.dataList.length - size;
			
			for(var i=0; i < addSize; i++){
				self.pop();
			}
		}
	};
	
	this.updateBtnEnable = function(){
		if(!!self.config.max){
			if(self.dataList.length >= self.config.max){
				$(self.containerEl).find('.listInput-addBtn').attr('disabled', true);
			} else {
				$(self.containerEl).find('.listInput-addBtn').attr('disabled', false);
			}
		}
		
		if(!!self.config.min){
			if(self.dataList.length <= self.config.min){
				$(self.containerEl).find('.listInput-element-container .listInput-removeBtn').attr('disabled', true);
			}else{
				$(self.containerEl).find('.listInput-element-container .listInput-removeBtn').attr('disabled', false);
			}
		}
	
	};
	
	// Get Data List
	this.init = function(){
		console.info("ListInput init seq = [" + seq + "]" , this);
		$(self.configEl).remove();
		$(self.containerEl).attr('data-seq', self.seq);
		
		//Load data config
		var dataConfigEls = $(self.configEl).children(".listInput-data-config").children();
		$.each(dataConfigEls, function(i, cEl){
			var cfg = $(cEl).data();
			self.config[cfg.name] = cfg.value;
		});
		
		//Load init data
		var initDataEls = $(self.configEl).children(".listInput-initData").children();
		$.each(initDataEls, function(i, dEl){
			var dataEntryEls = $(dEl).children();
			var data = {};
			
			$.each(dataEntryEls, function(i, deEl){
				var dataObj = $(deEl).data();
				data[dataObj.name] = dataObj.value;
			});
			
			self.add(data);
		});
		
		//If config.min is set, ensure there is enough data in data list
		if(!!self.config.min){
			if(initDataEls.length < self.config.min){
				for(var i=initDataEls.length; i<self.config.min; i++){
					self.add({});
				}
			}
		}
		
		//Define Btton action
		$(self.containerEl).on('click', '.listInput-addBtn', function(){
			self.add({});
		});
		
		$(self.containerEl).on('click', '.listInput-removeBtn', function(e1, e2){
			var btnEl = event.target;
			var elementContainerEl = $(btnEl).parents('.listInput-element-container');
			
			var index = $(elementContainerEl).attr('data-index');
			
			self.remove(index);

		});
		
		
		
	};

	
	this.init();

}


/*
 * Global Init Function
 */

$(function(){
	
	//Fix Form Charset Problem
	$("form").attr("accept-charset", "UTF-8");
	
	//Add Export & Import button for form element
	var urlParts = window.location.href.split("/");
	if(urlParts[urlParts.length-1].indexOf("launch") != 0 && $("form").length > 0){
		var toolBox = $("<div>").prop("class", "col-md-9").css("text-align", "right");
		
		var exportBtn = $("<button>")
					.prop("type", "button")
					.prop("class", "btn btn-default")
					.html("Export Form")
					.click(function(){
						var data = JSON.stringify(getFormData($("form")), null, "\t");
						var paths = window.location.pathname.split("/");
						var d = new Date();
						var filename = paths[paths.length-1] 
										+ "_" + d.getFullYear() 
										+ (d.getMonth()+1) 
										+ d.getDate() 
										+ d.getHours() 
										+ d.getMinutes() 
										+ d.getSeconds() + ".json";
						download(filename, data, "application/json");
					});
		
		$(toolBox).append(exportBtn);
		
		var importFile = $("<input>")
							.prop("type", "file")
							.click(function(e){
								console.log(e);
								$(this).val("");
							}).change(function(e){
								console.log("Reading File ", this.files)
								if(this.files.length > 0){
									var reader = new FileReader();
									reader.onload = function(e) {
										var data = JSON.parse(reader.result);
										setFormData($("form"), data);
									}
									
									reader.readAsText(this.files[0]);
								}
								
							});
		
		var importBtn = $("<button>")
							.prop("type", "button")
							.prop("class", "btn btn-default")
							.html("Import Form")
							.click(function(e){
								e.preventDefault();
								console.log("Import");
								$(importFile).click();
							});
		
		$(toolBox).append(importFile);
		$(importFile).hide();
		$(toolBox).append(importBtn);
		
		$("form :input[type='submit']").parent().after($(toolBox));
	}
	

	
	// Add Star to required field
	$.unique($("*[required]").parents("div[class*=col]")).find("label.control-label").prepend('<span style="color:red">*</span> ');
	
	/*
	 * List Input
	 */
		
	//Handling List Input for all defined container
	window.listInputStore = {};
	$.each($(".listInput-container"), function(i, el){
		var seq = null;
		do{
			seq = Math.random().toString(36).substring(7);
		}while(!!window.listInputStore[seq]);
		
		window.listInputStore[seq] = new ListInput(el, seq);
	});
		
		
	//prevent submit list input template data to server
	if(!!$(".listInput-template")){
		$("form").submit(function(){
			$(".listInput-template :input").attr("disabled", true);
		});
		
	}
	
	/*
	 * Radio Show Div
	 */
	
	// Radio Show Div - refresh div visibility
	var refreshRaidoShowDiv = function(indexStr, fieldName){
		if(!listName) listName = "";
		
		var options = $("[name='" + listName + indexStr + "." + fieldName + "']").map(function(){
			return $(this).val();
		});
		
		var selectedValue = $("[name='" + listName + indexStr + "." + fieldName + "']:checked").val(); 
		
		//Hide All first
		$.each(options, function(i, v){
			$("[id='" + fieldName + v + indexStr + "']").hide();
			$("[id='" + fieldName + v + indexStr + "'] :input").prop("disabled", true);
		});
		
		$("[id='" + fieldName + selectedValue + indexStr + "']").show();
		$("[id='" + fieldName + selectedValue + indexStr + "'] :input").prop("disabled", false);

	};
	
	// Radio Show Div - define radio button clicked event
	$('.raidoShowDiv input:radio').on("click", function() {
		var name = $(this).attr("name")
		var nameParts = name.split(".");
		var indexStr = nameParts[0].replace(listName, "").replace(/[!"#$%&'()*+,.\/:;<=>?@\[\\\]^`{|}~]/g, '\\$&');
		var fieldName = name.substr(nameParts[0].length + 1);
		
	   	refreshRaidoShowDiv(indexStr, fieldName);
	});		


	// Radio Show Div - Set Radio Show Div visibility according to field value
	var allRadioShowDivField = $.unique($("[id^=tx\\[0] .raidoShowDiv input:radio").map(function(){
		var name = $(this).attr('name');
		var nameParts = name.split(".");
		return name.substring(nameParts[0].length + 1);
	}));
	
	$.each($('[id^=tx\\[]'), function(){
		var indexStr = this.id.replace(/^tx\[([0-9]+)\]$/,'\\[$1\\]');
		
		$.each(allRadioShowDivField, function(i, v){
			refreshRaidoShowDiv(indexStr, v);
		});
		
	});
	
	/*
	 * WebScoket Message Received Noti
	 */
	var wsNotiArea = $("<div>")
	.css("position", "fixed")
	.css("bottom", "10px")
	.css("right", "10px")
	.css("z-index", "9999")
	.css("width", "300px");
	
	$("body").append(wsNotiArea);
	
	var displayNotif = function(baseResp){
		var notiBox = $("<div>").addClass("alert").css("display", "none");
		
		if(baseResp.messageStatus == 2){
			$(notiBox).addClass("alert-danger");
		}else if(baseResp.messageStatus == 1){
			$(notiBox).addClass("alert-warning");
		}else{
			$(notiBox).addClass("alert-success");
		}
		
		$(notiBox).html(baseResp.message);
		
		$(wsNotiArea).append(notiBox);
		$(notiBox).slideDown();
		setTimeout(function(){
			$(notiBox).slideUp();
			setTimeout(function(){
				$(notiBox).remove();
			}, 1000);
		}, 5000);
		
		
	}
	
	/*
	 * WebScoket Message Received System Alert 
	 */
	var wsSysAlertArea = $("<div>")
	.css("width", "100%");
	
	$($("body").children(".container")[0]).prepend(wsSysAlertArea);
	
	var displaySysAlert = function(baseResp){
		
		clearSysAlert();
		
		if(!!baseResp.message){
			
			var notiBox = $("<div>").addClass("alert").css("display", "none");
			
			if(baseResp.messageStatus == 2){
				$(notiBox).addClass("alert-danger");
			}else if(baseResp.messageStatus == 1){
				$(notiBox).addClass("alert-warning");
			}else{
				$(notiBox).addClass("alert-success");
			}
			
			$(notiBox).html(baseResp.message);
			
			$(wsSysAlertArea).append(notiBox);
			$(notiBox).slideDown();
			
		}
		
	}
	
	var clearSysAlert = function(){
		var notiBox = $(wsSysAlertArea).children(".alert");
		
		$(notiBox).slideUp();
		setTimeout(function(){
			$(notiBox).remove();
		}, 1000);
	}
	
	
	var ClientWebSocket = function(wsUrl){
		this.wsUrl = wsUrl;
		this.ws = null;
		this.cmdCallback = {
				/* cmnName : [callback] */
		};
		
		var self = this;
		
		this.connect = function(){
			self.ws = new WebSocket(self.wsUrl);
			self.ws.onmessage = function(event){
				console.log("[ClientWebSocket] Message", event);
				var message = event.data;
				
				if(!!message){
					var bean = JSON.parse(message);
					var cmd = bean.cmd;
					var data = bean.data;
					
					self.publish(cmd, data);
					
				}
				
			};
			
			self.ws.onopen = function(event){
				console.log("[ClientWebSocket] Connected", event);
			};
			self.ws.onclose = function(event){
				console.log("[ClientWebSocket] Socket Closed. Retry in 2 seconds.", event);
				setTimeout(function(){
					self.ws = self.connect();
				}, 2000);
			};
			self.ws.onerror = function(event){
				console.log("ERROR", event);
			};
			
		};
		
		this.subscribe = function(cmd, callback){
			console.log("[ClientWebSocket] Subscribe callback for command [" + cmd + "]");
			if(!self.cmdCallback[cmd]){
				self.cmdCallback[cmd] = [];
			}
			
			self.cmdCallback[cmd].push(callback);
		};
		
		this.unSubscribeAll = function(cmd){
			console.log("[ClientWebSocket] Unsubscribe all callback for command [" + cmd + "]");
			self.cmdCallback[cmd] = [];
		};
		
		this.unSubscribe = function(cmd, callback){
			if(!self.cmdCallback[cmd]){
				self.cmdCallback[cmd] = [];
			}
			
			console.log("[ClientWebSocket] Unsubscribe callback for command [" + cmd + "]");
			
			var callbackIdx = self.cmdCallback[cmd].indexOf(callback);
			if(callbackIdx != -1){
				self.cmdCallback[cmd].splice(callbackIdx, 1);
			}
		};
		
		this.publish = function(cmd, data){
			if(!!self.cmdCallback[cmd]){
				console.log("Invoke callback for command [" + cmd + "]" , event);
				for(var i=0; i<self.cmdCallback[cmd].length; i++){
					self.cmdCallback[cmd][i](data);
				}
			}
		};
		
		
		this.connect();
		
	}
	
	window.clientWebSocket = new ClientWebSocket("ws://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/ws/clientNoti");
	window.clientWebSocket.subscribe("NOTI", displayNotif);
	window.clientWebSocket.subscribe("SYSALT", displaySysAlert);
	
});


