//function pageReady() {
//	$('#guild_tab').click(function(e) {
//		//			  e.preventDefault();
//		//			  $(this).tab('show');
////		loadPage("myContent", "guild/guildRank.html");
//
//	});
//	$('#character_tab').click(function(e) {
//		//			  e.preventDefault();
//		$(this).tab('show');
////		loadPage("myContent", "character/characterRank.html");
//	});
//
//	$('#trade_tab').click(function(e) {
//		//			  e.preventDefault();
//		$(this).tab('show');
////		loadPage("myContent", "trade/trade.html");
//	});
//	$('#dailian_tab').click(function(e) {
//		//			  e.preventDefault();
//		$(this).tab('show');
//	});
//
//};

function loadPage(tabContId, url, args) {
	$('#' + tabContId).yopLoad(url, args);

};

