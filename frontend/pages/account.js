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
