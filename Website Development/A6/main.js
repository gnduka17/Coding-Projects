let apiKey = "41df0a4be48b4ddaaff577f05c722ab0";
console.log("hi");

if(localStorage.getItem("city")===null) {
   console.log("NOTHING IN HERE");
}
else{
	$("#cities").val(localStorage.getItem("city"));
}

function displayResults(jsonFile){
	let currTemp = jsonFile.data[0].temp * 1.8 + 32;
	let descrip = jsonFile.data[0].weather.description;
	let feelsLike = jsonFile.data[0].app_temp * 1.8 + 32;
	let var1 = currTemp.toFixed(1) + "° " + descrip + ". Feels like "+ feelsLike.toFixed(1) + "°";
	$("#tempDesc").text(var1);
}

$("#cities").on("change", function(event){
	localStorage.setItem("city", $("#cities").val());
	$.ajax({
		method: "GET",
		url: "https://api.weatherbit.io/v2.0/current",
		data: {
			city: $("#cities").val(),
			key: apiKey
		}
	})
	.done(function(results) {
		displayResults(results);
	})
	.fail(function() {
		console.log("ERROR");
	});
});

$("fas fa-plus").on("mouseenter", function(){
	$('fas fa-plus').css('cursor','pointer');
});
// $("li").on("mouseenter", function(){
// 	$('li').css('cursor','pointer');
// });

//let selected = 0;
$("#list").on("mouseenter", 'li', function(){
	$(this).css('cursor','pointer');
});

$("#list").on("click", 'li', function(){
	$(this).toggleClass("striked");
});

$("#list").on("click", '.fa-li', function(event){
	event.stopPropagation();
	$(this).parent().fadeOut();
});

$("#form").on("submit", function(event){
	event.preventDefault();
	$("#list").append('<li><span class="fa-li"><i class="far fa-square"></i></span>' + $("#toDoInput").val() + '</li>');
	$("#toDoInput").val("");
});

$(".fa-plus").on("click", function() {
	$("#toDoInput").slideToggle("slow");
	$("#toDoInput").val("");
});



