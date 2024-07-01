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
                `;
                appointmentsDiv.appendChild(appointmentDiv);
            })
        })
        .catch(error => {
        console.error('Error:', error);
        alert('Failed to fetch user: ' + error.message);
        window.location.href = './login.html';
    });
    document.getElementById('change-password-btn').addEventListener('click', changePassword);
};

function changePassword() {
    const newPassword = prompt("Voer uw nieuwe wachtwoord in:");
    if (!newPassword) {
        alert("Wachtwoord wijzigen geannuleerd.");
        return;
    }

    const token = sessionStorage.getItem("myJWT");
    if (!token) {
        window.location.href = './login.html';
        return;
    }

    fetch('/restservices/user/password', {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ password: newPassword })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network error. Status: ' + response.status);
            }
            alert('Wachtwoord succesvol gewijzigd.');
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to change password: ' + error.message);
        });
}

