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
            document.getElementById('name').value = data.realName;
            document.getElementById('email').value = data.email;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch user: ' + error.message);
            window.location.href = './customer_dashboard.html';
        });

    const timeButtons = document.querySelectorAll(".timeslots button");
    const timeInput = document.getElementById("time");
    const form = document.getElementById("appointmentForm");

    timeButtons.forEach(button => {
        button.addEventListener("click", function() {
            timeButtons.forEach(btn => btn.classList.remove("selected"));
            this.classList.add("selected");
            timeInput.value = this.innerText;
        });
    });

    form.addEventListener("submit", function(event) {
        event.preventDefault();

        const selectedDate = new Date(document.getElementById("date").value);
        const currentDate = new Date();

        currentDate.setHours(0, 0, 0, 0);

        if (selectedDate <= currentDate) {
            alert("Je kunt geen afspraak in het verleden maken.");
            return;
        }

        if (!timeInput.value) {
            alert("Selecteer een tijdslot.");
            return;
        }

        const formData = new FormData(form);
        const jsonData = JSON.stringify(Object.fromEntries(formData.entries()));

        fetch('/restservices/appointment', {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            },
            body: jsonData
        })
            .then(response => {
                if (response.ok) {
                    alert('Afspraak succesvol aangemaakt!');
                    window.location.href = './customer_dashboard.html';
                } else if (response.status === 400) {
                    return response.json().then(error => {
                        throw new Error(error.error || 'Voer een geldige datum in.');
                    });
                } else {
                    throw new Error('Afspraak aanmaken mislukt.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert(error.message);
            });
    });
};
