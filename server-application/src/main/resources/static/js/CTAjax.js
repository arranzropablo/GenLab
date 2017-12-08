$(".CTButton").on('click', function(event){
	event.preventDefault();
	
	var href = $(this).attr("href");
	
	$.get(href, function(response){
		$(".mainContentCalcTool").html(response);
	})
});