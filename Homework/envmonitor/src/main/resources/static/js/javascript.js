function getCoord(){
    var city = document.getElementById("city").value;
    const cityName = city;
    const apiKey= '6be3ea4da4135b4533844adbb662252b';
    const limit = 1;

    //primeiro descobrimos a latitude e longitude da cidade
    fetch(`http://api.openweathermap.org/geo/1.0/direct?q=${cityName}&limit=${limit}&appid=${apiKey}`)
    .then(response => response.json())
    .then(data => {
      const lat = data[0].lat;
      const lon = data[0].lon;
      const now = new Date();
      const date = now.toLocaleString('en-US', {
        month: 'numeric',
        day: 'numeric',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
        hour12: false
      });
      console.log(lat, lon, date);
      getAirQuality(lat, lon, cityName, date);
    })
    .catch(error => console.error(error));
}

async function getAirQuality(lat, lon, cityName, date){
    fetch(`/checkcity/${cityName}`)
    .then(response => response.json())
    .then(exists => {
      if (exists) {
          fetch(`/airquality/${cityName}`)
          .then(response => response.json())
          .then(data => {
            console.log("tinha em cache");
            console.log(data);
            table(data);
      })
          .catch(error => {
            console.error(error);
          });
      } else {
          const apiKey = '6be3ea4da4135b4533844adbb662252b';

          fetch(`http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=${apiKey}`)
          .then(response => response.json())
          .then(airQualityData => {
            const city = cityName;
            const co = airQualityData.list[0].components.co;
            const no2 = airQualityData.list[0].components.no2;
            const o3 = airQualityData.list[0].components.o3;
            const so2 = airQualityData.list[0].components.so2;
            const pm10 = airQualityData.list[0].components.pm10;
            const aqi = airQualityData.list[0].main.aqi;
            const airQualityDate = new Date(airQualityData.list[0].dt * 1000);
            const localDateTime = new Date(airQualityDate.getTime() - airQualityDate.getTimezoneOffset() * 60000).toISOString().substr(0, 19).replace('T', ' ');

            const envmonitor = {
              cityName: city,
              dateTime: localDateTime,
              no2: no2,
              so2: so2,
              co: co,
              o3: o3,
              pm10: pm10,
              aqi: aqi
            };

            console.log("ENVMONITOR:",envmonitor);

            fetch(`/airquality/${cityName}`, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(envmonitor)
            });
  
            console.log(`Armazenando valor de ${city}-${date} em cache`);
            table(envmonitor);
            return envmonitor;

          })
          .catch(error => console.error(error));
        
      }
  })
  .catch(error => {
      console.error(error);
  });
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