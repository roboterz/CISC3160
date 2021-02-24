


var json_url = 'https://data.cityofnewyork.us/resource/rc75-m7u3.json';


loadData(json_url);


async function loadData(url) { 
    
    let response = await fetch(url); 
    var data = await response.json(); 

    let tab =`<tr> <th>Date</th> <th>Case</th> <th>Death</th> </tr>`; 
    for (let r of data) { 
        tab += `<tr> <td>` + `${r.date_of_interest}`.substring(0,10) + `</td>
			<td>${r.case_count}</td>
			<td>${r.death_count}</td> </tr>`; 
    } 

    document.getElementById("COVID19").innerHTML = tab; 
} 



