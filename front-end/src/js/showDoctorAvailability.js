
// export function showDoctorAvailability() {
//     document.addEventListener('DOMContentLoaded', function() {
//         const params = new URLSearchParams(window.location.search);
//         let id = params.get('doctorId');
//         let result = document.getElementById("resultAviability");
//          result.innerHTML ="";
        
        
//         let tokenValue = localStorage.getItem('token');
//         let url = `http://localhost:8080/api/v1/work-schedules/work-schedule/${id}`;

//         fetch(url, {
//             method: 'GET',
//             headers: {
//                 'Authorization': 'Bearer ' + tokenValue
//             }
//         })
//         .then(response => response.json())
//         .then(schedules => {
//             if(schedules < 0){
//                 result.innerHTML = "No hay disponibilidad para este médico en este momento";
//                 return;
//             }
//                 schedules.forEach(schedule => {
//                     let dayOfWeek = schedule.dayOfWeek;
//                     let startTime = schedule.startTime;
//                     let endTime = schedule.endTime;
//                     let date = schedule.date;

//                     result.innerHTML += `
//                     <div class="col-md-4">
//                         <div class="card card-availability">
//                             <div class="card-body">
//                                 <h5 class="card-title">${dayOfWeek} ${date}</h5>
//                                 <p class="card-text">Disponible desde ${startTime} hasta ${endTime}</p>
//                               <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalAviability"
//                                 data-bs-day="${dayOfWeek}" data-bs-start="${startTime}" data-bs-end="${endTime}" data-bs-date="${date}" data-bs-id="${id}">Solicitar cita</button> 
//                             </div>
//                         </div>
//                         </div>
//                     <br>`;
//                 })
        
//             .catch(error => {
//                 console.error('Error al obtener disponibilidad:', error);
//             });
                
            
//         })
//         // .catch(error => {
//         //     console.error('Error al obtener disponibilidad:', error);
//         // });
//     });
//     }
export function showDoctorAvailability() {
    document.addEventListener('DOMContentLoaded', function() {
        const params = new URLSearchParams(window.location.search);
        let id = params.get('doctorId');
        let result = document.getElementById("resultAviability");
        result.innerHTML = "";

        let tokenValue = localStorage.getItem('token');
        let url = `http://localhost:8080/api/v1/work-schedules/work-schedule/${id}`;

        fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + tokenValue
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error ${response.status}: ${response.statusText}`);
            }
            return response.json();
        })
        .then(schedules => {
            if (schedules.length === 0) {
                result.innerHTML = "No hay disponibilidad para este médico en este momento";
                return;
            }
            schedules.forEach(schedule => {
                let dayOfWeek = schedule.dayOfWeek;
                let startTime = schedule.startTime;
                let endTime = schedule.endTime;
                let date = schedule.date;

                result.innerHTML += `
                    <div class="col-md-4">
                        <div class="card card-availability">
                            <div class="card-body">
                                <h5 class="card-title">${dayOfWeek} ${date}</h5>
                                <p class="card-text">Disponible desde ${startTime} hasta ${endTime}</p>
                                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalAviability"
                                    data-bs-day="${dayOfWeek}" data-bs-start="${startTime}" data-bs-end="${endTime}" data-bs-date="${date}" data-bs-id="${id}">
                                    Solicitar cita
                                </button>
                            </div>
                        </div>
                    </div>
                    <br>`;
            });
        })
        .catch(error => {
            console.error('Error al obtener disponibilidad:', error);
            result.innerHTML = "Ocurrió un error al obtener la disponibilidad del médico.";
        });
    });
}

    

