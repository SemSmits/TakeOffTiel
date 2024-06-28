window.onload = function() {
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
            document.getElementById('username').textContent = data.username;
            document.getElementById('email').textContent = data.email;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch user: ' + error.message);
            window.location.href = './admin_dashboard.html';
        });


    document.addEventListener("DOMContentLoaded", function () {
        const token = sessionStorage.getItem("myJWT");
        if (!token) {
            window.location.href = './login.html';
            return;
        }

        fetch('/restservices/appointment', {
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
            .then(appointments => {
                const appointmentsDiv = document.getElementById('appointments');
                appointments.forEach(appointment => {
                    const appointmentDiv = document.createElement('div');
                    appointmentDiv.classList.add('appointment');
                    appointmentDiv.innerHTML = `
                    <p><strong>Ontvanger:</strong> ${appointment.receiverName}</p>
                    <p><strong>Email ontvanger:</strong> ${appointment.receiverEmail}</p>
                    <p><strong>Datum:</strong> ${new Date(appointment.date).toLocaleDateString()}</p>
                    <p><strong>Starttijd:</strong> ${new Date(appointment.startTime).toLocaleTimeString()}</p>
                    <p><strong>Eindtijd:</strong> ${new Date(appointment.endTime).toLocaleTimeString()}</p>
                    <p><strong>Informatie:</strong> ${appointment.information}</p>
                    <p><strong>Status:</strong> ${appointment.status}</p>
                    <p><strong>Klant:</strong> ${appointment.customer.name} (${appointment.customer.email})</p>
                `;
                    appointmentsDiv.appendChild(appointmentDiv);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to fetch appointments: ' + error.message);
            });
    });
};
