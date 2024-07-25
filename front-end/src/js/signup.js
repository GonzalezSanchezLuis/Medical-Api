export function singup(){
    let url = "http://localhost:8080/api/v1/user/register"
    let form = document.getElementById("form");
  
    if (form) {
      form.addEventListener("submit", e => {
        e.preventDefault();
        let name = form.elements["name"].value;
        let surnames = form.elements["lastName"].value;
        let document = form.elements["document"].value;
        let address = form.elements["address"].value;
        let phone = form.elements["phone"].value;
        let email = form.elements["email"].value;
        let password = form.elements["password"].value;
  
        let user = { name,surnames, document, address, phone, email, password };
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
          localStorage.setItem('token', data.token);
          alert('User created successfully!');
          window.location.href = './dashboard.html';
        })
        .catch(error => {
          console.error('There was a problem with your fetch operation:', error);
          alert('Error: ' + error.message);
        });
      });
    }
}

export function singupDoctor(){
  let url = "http://localhost:8080/api/v1/doctor/register"
  let doctorForm = document.getElementById("doctorForm");

  if (doctorForm) {
    doctorForm.addEventListener("submit", e => {
      e.preventDefault();
      let doctorName = doctorForm.elements["name"].value;
      let surnames = doctorForm.elements["lastName"].value;
      let document = doctorForm.elements["document"].value;
      let addressConsultingRoom = doctorForm.elements["addressConsulting"].value;
      let specialty = doctorForm.elements["speciality"].value;
      let phoneConsultingRoom = doctorForm.elements["phoneConsulting"].value;
      let profesionalCard = doctorForm.elements["profesionalCard"].value;
      let email = doctorForm.elements["email"].value;
      let password = doctorForm.elements["password"].value;

      let doctor = { doctorName, surnames, document, addressConsultingRoom, email, password, profesionalCard, specialty, phoneConsultingRoom };
      console.log(doctor);

      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(doctor)
      })
      .then(response => {
        if (!response.ok) {
          return response.text().then(text => { throw new Error(response.status + ": " + text); });
        }
        return response.json();
      })
      .then(data => {
        console.log('Success:', data);
        localStorage.setItem('token', data.token);
        alert('User created successfully!');
        window.location.href = './dashboard-doctor.html';
      })
      .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
        alert('Error: ' + error.message);
      });
    });
  }
}
