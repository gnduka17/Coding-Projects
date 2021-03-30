function ajax(ep, retFunc){
			let httpRequest = new XMLHttpRequest();
			httpRequest.open("GET", ep);
			httpRequest.send();
			httpRequest.onreadystatechange = function(){
				if(httpRequest.readyState==4){
					if(httpRequest.status==200){
						retFunc(httpRequest.responseText);
					}
					else{
						console.log("There is an error or situation");
					}
				}
			}
		}
		function displayResults(results){
			document.querySelector("#search-id").value = "";
			let outputs = document.querySelector("#outputs");
			while( outputs.hasChildNodes() ) {
				outputs.removeChild( outputs.lastChild );
			}
			let convertedResults = JSON.parse(results);
			//top results showinf number;
			document.querySelector("#num-results").innerHTML = convertedResults.results.length;
			document.querySelector("#tot-results").innerHTML = convertedResults.total_results;
			if(convertedResults.results.length ==0){
				let displayDiv = document.createElement("div");
				displayDiv.innerHTML = "No Results Found";
				displayDiv.classList.add("text-center");
				displayDiv.classList.add("font-weight-bold");
				displayDiv.classList.add("col-12");
				let cont = document.querySelector("#outputs");
					cont.appendChild(displayDiv);

			}
			else{
				for(let i =0; i< convertedResults.results.length; i++){
					let divContTag = document.createElement("div");
					let brtag = document.createElement("br");
					divContTag.classList.add("col-6");
					divContTag.classList.add("col-md-4");
					divContTag.classList.add("col-lg-3");
					let divmovImgTag = document.createElement("div");
					divmovImgTag.classList.add("movieImage");
					let imgTag = document.createElement("img");
					imgTag.classList.add("img-fluid");
					let divhoverContTag = document.createElement("div");
					divhoverContTag.classList.add("just-hover");
					let divRateTag = document.createElement("div");
					divRateTag.classList.add("col-12");
					divRateTag.classList.add("text-center");
					let divVotersTag = document.createElement("div");
					divVotersTag.classList.add("text-center");
					divVotersTag.classList.add("col-12");
					let divSynopsisTag = document.createElement("div");
					divSynopsisTag.classList.add("col-12");
					divSynopsisTag.classList.add("text-center");
					let divNameTag = document.createElement("div");
					divNameTag.classList.add("col-12");
					divNameTag.classList.add("text-center");
					let divDateTag = document.createElement("div");
					divDateTag.classList.add("col-12");
					divDateTag.classList.add("text-center");
					if(convertedResults.results[i].poster_path!=null){
						imgTag.src = "https://image.tmdb.org/t/p/w500" + convertedResults.results[i].poster_path;
					}
					else{
						imgTag.src = "notAvail.jpg";

					}
					
					divRateTag.innerHTML = "Rating: "+ convertedResults.results[i].vote_average;
					divVotersTag.innerHTML = "Number of Voters: "+ convertedResults.results[i].vote_count;
					if(convertedResults.results[i].overview.length<=200){
						divSynopsisTag.innerHTML = convertedResults.results[i].overview;
					}
					else{
						divSynopsisTag.innerHTML = convertedResults.results[i].overview.substring(0,201) + "...";

					}
					divNameTag.innerHTML = convertedResults.results[i].title;
					divDateTag.innerHTML = convertedResults.results[i].release_date;
					divhoverContTag.appendChild(divRateTag);
					divhoverContTag.appendChild(divVotersTag);
					divhoverContTag.appendChild(divSynopsisTag);
					divmovImgTag.appendChild(imgTag);
					divmovImgTag.appendChild(divhoverContTag);
					divContTag.appendChild(divmovImgTag);
					divContTag.appendChild(divNameTag);
					divContTag.appendChild(divDateTag);
					divContTag.appendChild(brtag);
					let cont = document.querySelector("#outputs");
					cont.appendChild(divContTag);
				}
			}

		}
		let apiKey = "a42895674471d9500b215b5398c3ab32"
		
		
		//display movies being played
		let endpoint = "https://api.themoviedb.org/3/movie/now_playing?api_key="+ apiKey + "&language=en-US&page=1";
		ajax(endpoint, displayResults);

		//When user searching a movie
		document.querySelector("#search-form").onsubmit = function(event){
			event.preventDefault();
			let searchInput = document.querySelector("#search-id").value.trim();
			let endpoint = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey+ "&language=en-US&query=" + searchInput + "&page=1&include_adult=false"
			ajax(endpoint, displayResults);
		}