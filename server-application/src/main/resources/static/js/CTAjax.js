$("#calcButton").on('click', function(event){
	event.preventDefault();
	
	var elem = $($("form")[1]);
	var inputs = $("input");

	var obj = {};

	Array.prototype.forEach.call(inputs,function(input){
	    obj[$(input).attr("id")] = $(input).val();
    });

     $.ajax({
        url: "/calculationtools/result?CTid=" + $("#CTid").val(),
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj),
        success: function(data){
            document.open();
            document.write(data);
            document.close();
        }
    });
});