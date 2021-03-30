	
		function displayResults(jsonFile){
			$("#topTemp").html("");
			let currTemp = jsonFile.data[0].temp * 1.8 + 32;
			let descrip = jsonFile.data[0].weather.description;
			let feelsLike = jsonFile.data[0].app_temp * 1.8 + 32;
			let var1 = "Today's weather in Los Angeles: " + currTemp.toFixed(1) + "° " + descrip + ". Feels like "+ feelsLike.toFixed(1) + "°";
			$("#topTemp").text(var1);
			console.log("hi");
		}
		let apiKey = "41df0a4be48b4ddaaff577f05c722ab0";
		$.ajax({
			method: "GET",
			url: "https://api.weatherbit.io/v2.0/current",
			data: {
				city: "Los Angeles,CA",
				key: apiKey
			}
		})
		.done(function(results) {
			displayResults(results);

		})
		.fail(function() {
			console.log("ERROR");
		});