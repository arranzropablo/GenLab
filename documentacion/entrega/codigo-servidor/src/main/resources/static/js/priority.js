$("#savePriority").on('click', function(event){
	event.preventDefault();
	
	let sortedIndex = [];
	
	let elemsToSort = $("#sortable > li");
	elemsToSort.each(li =>{
		sortedIndex.push(Number($(elemsToSort[li]).attr("id")));
	})

     $.ajax({
        url: "/setpriority",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(sortedIndex),
        success: function(data){
            alert("Saved priority");
        }
    });
});