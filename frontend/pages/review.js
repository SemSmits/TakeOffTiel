window.onload = function() {
    const token = sessionStorage.getItem("myJWT");
    if (!token) {
        window.location.href = './login.html';
        return;
    }

    fetch('/restservices/review', {
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
            const appointmentList = document.getElementById('appointmentList');
            appointments.forEach(appointment => {
                const listItem = document.createElement('li');
                listItem.classList.add('appointment');
                listItem.innerHTML = `
                    <div>
                        <strong>${appointment.dateString} - ${appointment.timeString}</strong>
                        <p>${appointment.information}</p>
                    </div>
                    <button class="leave-review-btn" data-appointment-id="${appointment.id}">Review achterlaten</button>
                `;
                appointmentList.appendChild(listItem);
            });

            document.querySelectorAll('.leave-review-btn').forEach(button => {
                button.addEventListener('click', function() {
                    leaveReview(this.getAttribute('data-appointment-id'));
                });
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch appointments: ' + error.message);
        });
};

function leaveReview(appointmentId) {
    const reviewContent = prompt("Leave your review:");
    if (reviewContent) {
        const token = sessionStorage.getItem("myJWT");
        const review = { id: appointmentId, content: reviewContent };
        fetch(`/restservices/review`, {
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(review)
        })
            .then(response => {
                if (response.ok) {
                    alert('Review successfully submitted!');
                    window.location.reload();
                    return response.json()
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to submit review: ' + error.message);
            });
    }
}
