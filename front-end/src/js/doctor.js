export function getAllDoctors() {
  document.addEventListener('DOMContentLoaded', (event) => {
  const tokenValue = localStorage.getItem('token');
  const url = "http://localhost:8080/api/v1/doctor/doctors";
  
  // Verificar si hay un token antes de hacer la solicitud
  if (tokenValue) {
    fetch(url, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer ' + tokenValue,
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if(response.length === 0){
        var doctorList = document.getElementById('result'); 
        doctorList.innerHTML = '<p>No hay doctores disponibles</p>';
        return;
      }
      if (response.status === 401) {
        console.warn('Unauthorized: Please log in.');

      } else if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(doctors => {
      console.log('Success:', doctors);
      updateDoctorList(doctors);
    })
    .catch(error => {
    
    });
   
  }else{
    return
  }

});
}




      function updateDoctorList(doctors) { 
        var doctorList = document.getElementById('result');
        doctorList.innerHTML = '';           
        doctors.forEach(doctor => {
          doctorList.innerHTML += `
          <a href="#" class="enlace">
            <div class="card" style="width: 18rem;">
                <img src="./img/doctor.png" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">${doctor.doctorName} ${doctor.surnames}</h5>
                  <p class="card-text">${doctor.specialty}</p>
                  <p class="card-text">Dir: ${doctor.addressConsultingRoom}</p>
                   <p class="card-text">Tel: ${doctor.phoneConsultingRoom}</p>
                   <br/>         
                   <a href="./showDoctorAvailability.html?doctorId=${doctor.doctorId}" class="btn btn-primary">Solicitar cita medica</a>
                  </a>
                </div>
          </div>
                      
          <br><br>
          `     
        })
        
      }

      export function data() {
        let doctorJSON = localStorage.getItem("user");
        if (doctorJSON) {
          let doctor = JSON.parse(doctorJSON);
          let nameElement = document.getElementById("name");
      
          if (nameElement) {
            nameElement.innerHTML = `Bienvenido: ${doctor.name}`;
          }
      
          
          document.addEventListener('DOMContentLoaded', (event) => {
            const workSchedule = document.getElementById('workSchedule');
            const workScheduleCitas = document.getElementById('workScheduleCitas');
          
            if (workSchedule && workScheduleCitas) {
              const doctorId = doctor.doctorId;
          
              workSchedule.addEventListener('click', function(event) {
                event.preventDefault();
                window.location.href = `./myWorkSchedule.html?doctorId=${doctorId}`;
              });
          
              workScheduleCitas.addEventListener('click', function(event) {
                event.preventDefault();
                window.location.href = `./requestAppointment.html?doctorId=${doctorId}`;
              });
            }
          });
        } else {
          console.error('No se encontró el objeto doctor en localStorage');
        }
      }
      
    