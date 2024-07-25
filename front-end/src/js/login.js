 import { getAllDoctors,data } from "./doctor.js";

 export function login() {
  let url = "http://localhost:8080/api/v1/auth/login";
  let login = document.getElementById("login");

  if (login) {
      login.addEventListener("submit", e => {
          e.preventDefault();

          let email = login.elements["email"].value;
          let password = login.elements["password"].value;

          let user = { email, password };
          console.log(user);

          fetch(url, {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify(user)
          })
          .then(response => {
              if (!response.ok) {
                  return response.text().then(text => { throw new Error(response.status + ": " + text); });
              }
              return response.json();
          })
          .then(data => {
              console.log('Success:', data);
              console.log('Sending request with token:', data.token); 
              localStorage.setItem('token', data.token);
              localStorage.setItem("user",JSON.stringify(data.user))
               
               
              // Aquí rediriges según el rol
              if (data.role === 'ROLE_DOCTOR') {
                  window.location.href = './doctor-panel.html';
                  
                //   getWorkSchedule();
              } else {
                  window.location.href = './dashboard.html';
                  getAllDoctors();
                  
              }
          })
          .catch(error => {
              alert('Error: ' + error.message);
          });
      });
  }
}

