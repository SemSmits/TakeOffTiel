window.onload = function () {
    const token = sessionStorage.getItem("myJWT");
    if (!token) {
        window.location.href = './login.html';
        return;
    }

    fetch('/restservices/user', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network error. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            const appointmentsDiv = document.getElementById('appointments');
            data.appointments.forEach(appointment => {
                const appointmentDiv = document.createElement('div');
                appointmentDiv.classList.add('appointment');
                appointmentDiv.innerHTML = `
                    <p><strong>Naam:</strong> ${appointment.receiverName}</p>
                    <p><strong>Email:</strong> ${appointment.receiverEmail}</p>
                    <p><strong>Datum:</strong> ${new Date(appointment.date).toLocaleDateString()}</p>
                    <p><strong>Starttijd:</strong> ${new Date(appointment.startTime).toLocaleTimeString()}</p>
                    <p><strong>Eindtijd:</strong> ${new Date(appointment.endTime).toLocaleTimeString()}</p>
                    <p><strong>Informatie:</strong> ${appointment.information}</p>
                    <p><strong>Status:</strong> <span id="status-${appointment.id}">${appointment.status}</span></p>
                    <button class="accept-btn" data-id="${appointment.id}">Accepteer</button>
                    <button class="deny-btn" data-id="${appointment.id}">Weiger</button>
                `;
                appointmentsDiv.appendChild(appointmentDiv);
            });

            document.querySelectorAll('.accept-btn').forEach(button => {
                button.addEventListener('click', () => {
                    const appointmentId = button.getAttribute('data-id');
                    updateAppointmentStatus(appointmentId, 'accepted');
                });
            });

            document.querySelectorAll('.deny-btn').forEach(button => {
                button.addEventListener('click', () => {
                    const appointmentId = button.getAttribute('data-id');
                    updateAppointmentStatus(appointmentId, 'denied');
                });
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch user: ' + error.message);
            window.location.href = './admin_dashboard.html';
        });
};

function updateAppointmentStatus(appointmentId, status) {
    const token = sessionStorage.getItem("myJWT");

    fetch(`/restservices/appointment/${appointmentId}`, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({status: status})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network error. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById(`status-${appointmentId}`).textContent = status;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to update appointment: ' + error.message);
        });
}
