
Yop.Init.Complete.add(function(){
	 $.history.init(function(arguments){
	    	Yop.Page._forward(arguments);
	 });
});

$(document).ready(function(){
	Yop.Init.Complete.fire();
}
);