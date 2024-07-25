
export function workschedule(){
      let calendarEl = document.getElementById('calendar');
      if(calendarEl){
        let calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'timeGridWeek',
          events: [],
          selectable: true,
          select: function(info) {
            let dayOfWeek = info.start.toLocaleString('es-CO', { weekday: 'long' });
            let startTime = info.start.toISOString().substr(11, 8);
            let endTime = info.end.toISOString().substr(11, 8);
            let date = info.start.toISOString().substr(0, 10);
      
            let workSchedule = { dayOfWeek, startTime, endTime, date };
            let token = localStorage.getItem('token');
      
            fetch("http://localhost:8080/api/v1/work-schedules/register", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
              },
              body: JSON.stringify(workSchedule)
            })
            .then(response => {
              if (!response.ok) {
                return response.text().then(text => { throw new Error(response.status + ": " + text); });
              }
              return response.json();
            })
            .then(data => {
              console.log('Success:', data);
              alert('Work schedule saved successfully');
              calendar.addEvent({
                title: 'Horario laboral',
                start: info.start,
                end: info.end,
                display: 'auto' // Asegura que el evento se muestre correctamente
              });
              
            })
            .catch(error => {
              console.error('Error:', error);
              alert('Error: ' + error.message);
            });
          }
        });
        calendar.render();

      }
  }

  export function getWorkSchedule() {
    document.addEventListener('DOMContentLoaded', (event) => {
        let tokenValue = localStorage.getItem('token');
        let table = document.getElementById("table");
        if (table) {
            table.innerHTML = "";

            const params = new URLSearchParams(window.location.search);
            let id = params.get('doctorId');
            let url = `http://localhost:8080/api/v1/work-schedules/work-schedule/${id}`;

            // Realizar la solicitud fetch
            fetch(url, {
                method: 'GET',
                headers: {
                    "Authorization": "Bearer " + tokenValue
                }
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(response.status + ": " + text); });
                }
                return response.json();
            })
            .then(response => {
                if(response.length === 0){
                  table.innerHTML = "<tr><td colspan='5'>No hay horarios registrados para este medico</td></tr>";
                  return;
                }
                if (Array.isArray(response)) {
                    response.forEach(workSchedule => {
                        table.innerHTML += `
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Día de la semana</th>
                                        <th scope="col">Fecha</th>
                                        <th scope="col">Hora inicio</th>
                                        <th scope="col">Hora fin</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">${workSchedule.id}</th>
                                        <td>${workSchedule.dayOfWeek}</td>
                                        <td>${workSchedule.date}</td>
                                        <td>${workSchedule.startTime}</td>
                                        <td>${workSchedule.endTime}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <button type="button" class="btn btn-danger" data-bs-docId="${workSchedule.id}" data-bs-toggle="modal" data-bs-target="#exampleModal">Borrar calendario laboral</button>
                        `;
                    });
                } else {
                    console.error('La respuesta no es un array');
                }
            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
        } else {
            console.error("El elemento con el ID 'table' no se encuentra en el DOM.");
        }
    });
}

export function deleteWorkSchedule() {
  document.addEventListener('DOMContentLoaded', (event) => {
    let tokenValue = localStorage.getItem('token');
    const exampleModal = document.getElementById('exampleModal');
    const confirmDeleteButton = document.getElementById('confirmDeleteButton');

    if (exampleModal && confirmDeleteButton) {
      exampleModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const docId = button.getAttribute('data-bs-docId');
        confirmDeleteButton.setAttribute('data-docId', docId);
      });

      confirmDeleteButton.addEventListener('click', function () {
        const docId = this.getAttribute('data-docId');
        const url = `http://localhost:8080/api/v1/work-schedules/delete/${docId}`;
        fetch(url,{
          method: 'DELETE',
          headers: {
            "Authorization": "Bearer " + tokenValue
        }

        })
          .then(response => {
            if (!response.ok) {
              return response.text().then(text => { throw new Error(response.status + ": " + text); });
            }
            alert('Calendario laboral borrado con éxito');
            document.location.href = "./doctor-panel.html";
          })
          .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
            alert('Error: ' + error.message);
          });
      });
    }
  });
}