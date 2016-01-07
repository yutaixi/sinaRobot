String.prototype.encodeURI=function()
{
	return encodeURIComponent(this).replaceAll('%C2%A0','%20');
};
String.prototype.replaceAll=function(oldstr,newstr)
{
	return this.replace(new RegExp(oldstr,'gm'), newstr);
};
String.prototype.startWith=function(str){     
	  var reg=new RegExp("^"+str);     
	  return reg.test(this);        
};  
String.prototype.trim=function()
{
	return this.replace(/(^[\s ]*)|([\s ]*$)/g,"");
};

String.prototype.endWith=function(str){     
	  var reg=new RegExp(str+"$");     
	  return reg.test(this);        
	};
Array.prototype.remove=function(value){
	for(var i=0,len=this.length;i<len;i++)
	{
		if(value==this[i])
		{
			this.splice(i, 1);
			i--;
		}
	}
};

Yop.DataHelper={
		hasValue:function(value)
		{
			return typeof  value != 'undefined' && value!=null;
		},
		isEmpty:function(str)
		{
			if(Yop.DataHelper.hasValue(str))
			{
				str+='';
				return str && str.trim().length>0? false:true;
			}
			return true;
		}
};