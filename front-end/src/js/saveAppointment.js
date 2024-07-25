import { token } from './helpers.js'
export function saveAppointment() {
    document.addEventListener('DOMContentLoaded', () => {
        const tokenValue = localStorage.getItem('token');
        console.log(tokenValue);
        if(tokenValue){
       
        const exampleModal = document.getElementById('exampleModalAviability');
        if(exampleModal)
        exampleModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const day = button.getAttribute('data-bs-day');
            const startTime = button.getAttribute('data-bs-start');
            const endTime = button.getAttribute('data-bs-end');
            const docId = button.getAttribute('data-bs-id');
            const date = button.getAttribute('data-bs-date');

            const modalBody = exampleModal.querySelector('.modal-body');
            modalBody.innerHTML = `
                <p>Tu cita será para el día <strong>${day} ${date}</strong>, debes escoger una hora entre <strong>${startTime}</strong> y las <strong>${endTime}</strong></p>
                <label for="appointmentTime">Selecciona la hora para tu cita:</label>
                <input type="text" id="appointmentDay" class="form-control" hidden="true" value="${day}">
                <input type="text" id="docId" class="form-control" hidden="true" value="${docId}">
                <input type="text" id="date" class="form-control" hidden="true" value="${date}">
                <input type="time" id="appointmentTime" class="form-control">
            `;
        });

        const confirmButton = document.getElementById('confirmAppointmentButton');
        confirmButton.addEventListener('click', function () {
            const appointmentTime = document.getElementById('appointmentTime').value;
            const dateOfAppointment = document.getElementById('appointmentDay').value;
            const docId = document.getElementById('docId').value;
            const date = document.getElementById('date').value;


            const url = `http://localhost:8080/api/v1/appointment/register`;

            const registerAppointment = {
                appointmentTime: appointmentTime + ':00', // Asegúrate de que tenga formato HH:mm:ss
                dateOfAppointment: dateOfAppointment,
                doctorId:docId,
                date:date

            };
            console.log(registerAppointment);

            console.log(`Cita reservada para el día ${date} ${dateOfAppointment} a las ${appointmentTime}`);
            
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + tokenValue
                },
                body: JSON.stringify(registerAppointment)
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(response.status + ": " + text); });
                }
                return response.json();
            })
            .then(data => {
                console.log('Success:', data);
                alert('¡Cita creada exitosamente!');
                window.location.href ="./dashboard.html"
            })
            // .catch(error => {
            //     console.error('There was a problem with your fetch operation:', error);
            //     alert('Error: ' + error.message);
            // });
        });
    }
    });

}
