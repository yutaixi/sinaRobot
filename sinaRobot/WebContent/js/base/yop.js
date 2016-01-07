$.fn.yopLoad=function(url,data,callback,noscroll,errorCallback)
{
	this.empty().html("");
	var realUrl=['#!'];
	if(!Yop.DataHelper.isEmpty(url))
	{
		realUrl.push(Yop.Page.register(url));
		if(typeof(data) == 'object')
		{
			if(realUrl.join('').indexOf('?')<0)
			{
				realUrl.push('?');
			}
			realUrl.push(Yop.Core._ajaxSerialize(data));
		}
	}
	debugger;
	url=realUrl.join("");
	this.load(url.substring(2),null,function(responseTxt,textStatus,xhr){
		if(textStatus=='error')
		{
			errorCallback&& errorCallback();
		}else
		{
			window.location.hash=url;
			Yop.Page.afterLoad(this);
			if(typeof callback ==='function')
			{
				callback();
			}
		}
	}
	);
};

$.fn.yopAnoLoad=function(url,data,callback,noscroll,errorCallback)
{
	this.empty().html("");
	var realUrl=['#!'];
	if(!Yop.DataHelper.isEmpty(url))
	{
		realUrl.push(Yop.Page.register(url));
		if(typeof(data) == 'object')
		{
			if(realUrl.join('').indexOf('?')<0)
			{
				realUrl.push('?');
			}
			realUrl.push(Yop.Core._ajaxSerialize(data));
		}
	}
	url=realUrl.join("");
	debugger;
	this.load(url.substring(2),null,function(responseTxt,textStatus,xhr){
		if(textStatus=='error')
		{
			errorCallback&& errorCallback();
		}else
		{
			Yop.Page.afterLoad(this);
			if(typeof callback ==='function')
			{
				callback();
			}
		}
	}
	);
};



var Yop=
{
		Core:{
			beforeFoward:$.Callbacks(),
			afterFoward:$.Callbacks()
		},
		Init:{
			AfterUi:$.Callbacks('once'),
			AfterSystem:$.Callbacks('once'),
			Complete:$.Callbacks('once')
		},
        reqRoot:"http://192.168.1.100:8080/wowdb/",
	    staticUrl:"wow/static"
	
};

Yop.Core.Namespace=
{
	register:function(namespace)
	{
		var root=window;
		var objs=namespace.split('.');
	    var len=objs.length;
	    for(var i=0;i<len;i++)
	    {
	    	root=root[objs[i]]=root[objs[i]] || {};
	    }
	    return root;
	},
	regModule:function(namespace,obj)
	{
		var root=window;
		var objs=namespace.split('.');
		var len=objs.length;
		for(var i=0;i<len;i++)
		{
		    root=root[objs[i]]=root[objs[i]] ||obj || {};
		}
		Yop.Module.modules.put(namespace);
		return root;
	},
	getVariable:function(name)
	{
		var value=false;
		try
		{
			value=eval(name) || false;
		}catch(e)
		{
			
		}
		return value;
	},
	get:function(name)
	{
		if(name){
			name=name.split('.');
			if(name.length){
				var ns=window;;
				for(var i=0;ns && i<name[i];i++){
					ns=ns[name[i]];
				}
				return ns;
			}
		}
	}
};

Yop.Core._ajaxSerialize=function(data)
{
	debugger;
	var strParam=new Array();
	if(typeof(data) !== 'object')
	{
		return data;
	}
	function genQueryParam(data,path)
	{
		if($.isArray(data))
		{
			for(var i=0;i<data.length;i++)
			{
				if(typeof(data[i]) === 'object' )
				{
					genQueryParam(data[i],path);
				}else if(typeof(data[i]) == 'string')
				{
					strParam.push(path+"="+data[i].encodeURI());
				}else
				{
					strParam.push(path+"="+data[i]);
				}
				
			}
		}else
		{
			for(var attr in data)
			{
				var attrValue=data[attr];
				var attrPath=path?path+'.'+attr:attr;
				if(attrValue && attrValue.getTimezoneOffset){
					strParam.push(attrPath+".time="+(attrValue.getTime()-8*60*60*1000-attrValue.getTimezoneOffset()*60*1000));
				}else if(typeof(attrValue) === 'object')
				{
					genQueryParam(attrValue,path);
				}else if(typeof(attrValue) == 'string')
				{
					strParam.push(attrPath+"="+data[attr].encodeURI());
				}else
				{
					strParam.push(attrPath+"="+data[attr]);
				}
			}
		}
	};
	genQueryParam(data,"");
	strParam=strParam.join("&");
	return strParam;
};





Yop.Page=
{
		_clearCache:true,
        _forwardCount:0,
        getContentPanel:function()
        {
        	return $('#'+Yop.Config.Panel.main);
        },
		_forward:function(url)
		{
			if(url && url!=null && url.startWith('!') || url.startWith('#!'))
			{
				url=url.substring(url.indexOf('!')+1);
			}
			var panel=Yop.Page.getContentPanel();
			if(Yop.DataHelper.isEmpty(url))
			{
				Yop.Module.clear();
				Yop.Page.clearCache();
//				panel.yopLoad('main.html');
				panel.yopAnoLoad('main.html');
				return;
			}
			
			var target=Yop.Page.getRequest()['_target'];
			if(target && target!=null)
			{
				return;
			}
			url=url.replaceAll(' ','20%');
			Yop.Module.clear();
			Yop.Page.clearCache();
			panel.yopLoad(url,function(){
				Yop.Page.afterForward && Yop.Page.afterForward();
			});
			
		},
		clearCache:function()
		{
			this._clearCache && Yop.Cache.clear();
			this._clearCache=true;
		},
		forwardParam:{
			url:"",
			data:"",
			clearCache:"",
			isSelf:false,
			isDelay:false,
			lastTime:false,
			interal:""
		},
		forward:function(url,data,clearCache)
		{
			var forwardParam=Yop.Page.forwardParam;
			forwardParam.isDelay=true;
			forwardParam.isSelf=(url==window.location.hash)? true:false;
			forwardParam.url=url;
			forwardParam.data=data;
			forwardParam.clearCache=clearCache;
			if(forwardParam.interal)
			{
				return true;
			}
			var date=new Date();
			if(date-forwardParam.lastTime>Yop.Config.Forward.lastTimeSpace){
				forwardParam.isDelay=false;
				Yop.Page.forwardIntervalMethod();
				return;
			};
			forwardParam.interal=setInterval(callback, Yop.Config.Forward.delayTime/2);
			//loading code;
			
		},
		forwardIntervalMethod:function()
		{
			var forwardParam=Yop.Page.forwardParam;
			if(forwardParam.isDelay){
				forwardParam.isDelay=false;
			};
			var forwardURL=forwardParam.url;
			forwardParam.lastTime=new Date().getTime();
			if(forwardParam.isSelf){
//				if(ajaxCompleted)
				Yop.Page._forward(forwardURL);
			}else
			{
				Yop.Page.forwardMethod(forwardURL,forwardParam.data,forwardParam.clearCache);
			}
			clearInterval(forwardParam.interal);
			forwardParam.interal=false;
		},
		forwardMethod:function(url,data,clearCache)
		{
			if(Yop.Page.beforeForward
			    && Yop.Page.beforeForward(url,data,clearCache)==false)
			{
			   	return;
			}
			
			var realUrl=['#!'];
			if (!Yop.DataHelper.isEmpty(url)) {
			realUrl.push(Yop.Page.register(url));
			if (typeof(data) == 'object') {
				if (realUrl.join('').indexOf('?') < 0) {
					realUrl.push('?');
				}
				realUrl.push(Yop.Core._ajaxSerialize(data));
			    }
		    }
		    url=realUrl.join('')
		    if(clearCache==false)
		    {
		    	this._clearCache=false;
		    }
		    if(window.location.hash==url)
		    {
		    	Yop.Page._forward(url);
		    }else
		    {
		    	window.location.hash=url;
		    }
		},
        cdRequest:function(id,url)
        {
        	oScript = document.getElementById(id);
            var head = document.getElementsByTagName("head").item(0);
            if (oScript) {
               head.removeChild(oScript);
            }
            oScript = document.createElement("script");
            oScript.setAttribute("src", url);
            oScript.setAttribute("id",id);
            oScript.setAttribute("type","text/javascript");
            oScript.setAttribute("language","javascript");
            head.appendChild(oScript);
            return oScript;
        },
        _list:{},
        register:function(pKey,path)
        {
        	if(path)
        	{
        		Yop.Page._list[pKey]=path;
        	}else
        	{
        		return Yop.Page._list[pKey]|| pKey;
        	}
        },
        afterLoad:function(context)
        {
        	Yop.ready(context);
        	Yop.Module.executeReady();
        },
        getRequest:function()
	    {
	    	var args=new Object();
	    	var fulUrl=window.location.href;
	    	var posPrefix=fulUrl.search('#');
	    	if(posPrefix<0)
	    	{
	    		return {};
	    	}
	    	var tempUrl=fulUrl.substr(fulUrl.indexOf('?') + 1);
	    	if(tempUrl.length>1)
	    	{
	    		var pairs=tempUrl.split("&");
	    		for(var i=0;i<pairs.length;i++)
	    		{
	    			var pos=pairs[i].indexOf("=");
	    			if(pos==-1)
	    			{
	    				continue;
	    			}
	    			var argName=pairs[i].substring(0,pos);
	    			var value=pairs[i].substring(pos+1);
	    			//value=decodeURIComponent(value);
	    			value=decodeURI(value);
	    			args[argName]=value;
	    		}
	    		
	    	}
	    	return args;
	    }
        
};

Yop.ready=function(fn,name)
{
	if($.isFunction(fn)){
		Yop.ready._list.push(fn);
	}else
	{
		$.each(Yop.ready._list,function(index,readyFunc){
			readyFunc(fn);
		});
	};
};
Yop.ready._list=new Array();

Yop.HashMap=function()
{
	this.keys=new Array();
	this.data=new Object();
	this.put=function(key,value)
	{
		if(this.data[key]==null)
		{
			this.keys.push(key);
		}
		this.data[key]=value;
	};
	this.get=function(key)
	{
		return this.data[key];
	};
	this.remove=function(key)
	{
		this.keys.remove(key);
	    this.data[key]=null;
	};
	this.each=function(fn)
	{
		if(typeof fn != 'function')
		{
			return;
		};
		
		var len=this.keys.length;
		for(var i=0;i<len;i++)
		{
			k=this.keys[i];
			fn(k,this.data[k],i);
		};
		
	};
	this.isEmpty=function()
	{
		return this.keys.length==0;
	};
	
	this.size=function()
	{
		return this.keys.length;
	};
};


Yop.Module=function(name)
{
	this.name=name;
	this.hasInit=false;
};
Yop.Module.modules=new Yop.HashMap();
Yop.Module.define=function(moduleName,defineFunction)
{

	var module=Yop.Core.Namespace.register(moduleName);
	var selector=function(elmt,panel)
	{
		return (module.context || $(document)).find(elmt,panel);
	};
	if(typeof defineFunction == 'function')
	{
		defineFunction(module,selector);
	};
	Yop.Module.modules.put(moduleName,new Yop.Module(moduleName));
};
Yop.Module.executeReady=function()
{

	Yop.Module.modules.each(function(moduleName,module){

		var realModuleObj=Yop.Core.Namespace.register(moduleName);
		if(!module.hasInit && typeof(realModuleObj.ready)==='function' )
		{
			realModuleObj.ready();
			module.hasInit=true;
		}
	});
};
Yop.Module.remove=function(moduleName){
	if(moduleName){
		try{
			var realModuleObj=Yop.Core.Namespace.register(moduleName);
			if(realModuleObj && typeof(realModuleObj.exit) === 'function')
			{
				realModuleObj.exit();
			}
			Yop.Module.modules.remove(moduleName);
			eval(moduleName+='null');
		}catch(e)
		{
			
		};
	};
};
Yop.Module.clear=function()
{
	var keys=$.extend([],Yop.Module.modules.keys);
	$.each(keys,function(){
		Yop.Module.remove(this);
	});
};



Yop.Cache={
		_cacheMap:{},
		get:function(key){
			return this._cacheMap[key];
		},
		put:function(key,data)
		{
			this._cacheMap[key]=data;
		},
		clear:function(){
			this._cacheMap={};
		},
		remove:function(key){
			try{
				delete this._cacheMap[key];
			}catch(e){
				
			}
                  
		}
};