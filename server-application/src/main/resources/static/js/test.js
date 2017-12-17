window.onload=function(){
	var pos=window.name || 0;
	window.scrollTo(0,pos);
}
window.onunload=function(){
	window.name=self.pageYOffset || (document.documentElement.scrollTop+document.body.scrollTop);
}

$("[data-function=\"questionAdd\"]").click(function(event){
	event.preventDefault();
	var input = $("<input>")
    		.attr("type", "hidden")
    		.attr("name", "questionAdd").val("questionAdd");
	$("#test-form").append($(input));
	$("#test-form").submit();
});

$("[data-function=\"answerAdd\"]").click(function(event){
	event.preventDefault();

	var inputPos = $("<input>")
    		.attr("type", "hidden")
    		.attr("name", "questionPos").val($(this).attr("data-pos"));
	
	var inputAnswerAdd = $("<input>")
		.attr("type", "hidden")
		.attr("name", "answerAdd").val("answerAdd");
	
	$("#test-form").append($(inputPos));
	$("#test-form").append($(inputAnswerAdd));

	$("#test-form").submit();
});

$("[data-function=\"questionDel\"]").click(function(event){
	event.preventDefault();
	var input = $("<input>")
    		.attr("type", "hidden")
    		.attr("name", "questionDel").val("questionDel");
	
	var inputPos = $("<input>")
		.attr("type", "hidden")
		.attr("name", "questionPos").val($(this).attr("data-pos"));
	
	$("#test-form").append($(input));
	$("#test-form").append($(inputPos));
	$("#test-form").submit();
});

$("[data-function=\"answerDel\"]").click(function(event){
	event.preventDefault();

	var inputPosQ = $("<input>")
    		.attr("type", "hidden")
    		.attr("name", "questionPos").val($(this).attr("data-question-pos"));
	
	var inputPosA = $("<input>")
	.attr("type", "hidden")
	.attr("name", "answerPos").val($(this).attr("data-pos"));
	
	var inputAnswerDel = $("<input>")
		.attr("type", "hidden")
		.attr("name", "answerDel").val("answerDel");
	
	$("#test-form").append($(inputPosQ));
	$("#test-form").append($(inputPosA));
	$("#test-form").append($(inputAnswerDel));

	$("#test-form").submit();
});