import { singup, singupDoctor } from "./signup.js";
singup();
singupDoctor();

import { data,getAllDoctors} from "./doctor.js";
data();
getAllDoctors();


import { login } from "./login.js";
login();

import { logout  } from "./logout.js";
logout();

import { workschedule } from "./workSchedule.js";
workschedule();

import { showDoctorAvailability} from "./showDoctorAvailability.js";
showDoctorAvailability();


import { getWorkSchedule } from "./workSchedule.js";
getWorkSchedule();

import { deleteWorkSchedule } from "./workSchedule.js";
deleteWorkSchedule();

import { requestAppointment } from "./requestAppointment.js";
requestAppointment();

import { getAllAppointments } from "./user.js";
getAllAppointments();



import { saveAppointment } from "./saveAppointment.js";
saveAppointment();

