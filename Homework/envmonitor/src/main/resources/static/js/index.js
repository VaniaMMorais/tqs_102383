
function getAirQuality(){
  const airQualityTable = document.getElementById('airQualityTable');
  airQualityTable.style.display = 'table';
    var city = document.getElementById("city").value;
    const cityName = city;
          fetch(`/airquality/${cityName}`)
          .then(response => response.json())
          .then(data => {
            console.log(data);
            table(data);
      })
          .catch(error => {
            console.error(error);
          });
}

function getFutureAirQuality(){
  const airQualityTable = document.getElementById('futureAirQualityTable');
  airQualityTable.style.display = 'table';
  var city = document.getElementById("city").value;
  const cityName = city;
  console.log("Entrei no futuro");
  fetch(`/futureairquality/${cityName}`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      futureTable(data);
    })
    .catch(error => {
      console.error(error);
    });
  }

function futureTable(data){
  const table = document.querySelector('#futureAirQualityTable tbody');

    table.innerHTML = '';
        
    for(let i = 0; i < data.length; i++){
        const row = `
          <tr>
            <td>${data[i].date}</td>
            <td>${data[i].cityName}</td>
            <td>${data[i].no2}</td>
            <td>${data[i].so2}</td>
            <td>${data[i].co}</td>
            <td>${data[i].o3}</td>
            <td>${data[i].pm10}</td>
            <td>${data[i].aqi}</td>
          </tr>
        `;
        table.innerHTML += row;
    }
}



function table(data){
    const table = document.querySelector('#airQualityTable tbody');

    table.innerHTML = '';
            
        const row = `
          <tr>
            <td>${data.cityName}</td>
            <td>${data.no2}</td>
            <td>${data.so2}</td>
          <td>${data.co}</td>
          <td>${data.o3}</td>
          <td>${data.pm10}</td>
          <td>${data.aqi}</td>
          </tr>
        `;

      table.innerHTML = row;
}

function getStats(){
  fetch("/cacheStatus")
          .then(response => response.json())
          .then(data => {
            console.log(data);
              document.getElementById("requests").innerHTML = data[0];
              document.getElementById("hits").innerHTML = data[1];
              document.getElementById("misses").innerHTML = data[2];
          });
}