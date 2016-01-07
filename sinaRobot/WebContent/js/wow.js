Yop.Module.define("wow",function(page,$S){
	
//	page.ready=function()
//	{
//		$('#guild_tab').click(function (e) {
////			  e.preventDefault();
////			  $(this).tab('show');
//			page.loadPage("myContent","guild/guildRank.html");
//			 
//		});
//		$('#character_tab').click(function (e) {
////			  e.preventDefault();
//			  $(this).tab('show');
//			  page.loadPage("myContent","character/charachterRank.html");
//		});
//
//		
//		
//		$('#trade_tab').click(function (e) {
////			  e.preventDefault();
//			  $(this).tab('show');
//			  page.loadPage("myContent","trade/trade.html");
//		});
//		$('#dailian_tab').click(function (e) {
////			  e.preventDefault();
//			  $(this).tab('show');
//		});
//		
////		page.loadPage("myContent","guild/guildRank.html");
//	};
	

	page.loadPage=function(tabContId,url,args)
	{
		$('#'+tabContId).yopLoad(url,args);

	};
	
	
});






