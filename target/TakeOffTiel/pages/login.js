document.querySelector('form').addEventListener('submit',
    (event) => {
        event.preventDefault();

        const username = document.querySelector('input[name="username"]').value,
            // eslint-disable-next-line sort-vars
            password = document.querySelector('input[name="pass"]').value;

        fetch('/login', {
            body: `username=${username}&password=${password}`,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'POST'
        })
            .then(response => response.text())
            .then(data => {
                if (data === 'Login successful') {
                    window.location.href = '/account.html';
                } else {
                    // eslint-disable-next-line no-alert
                    alert('Onjuiste invoer of account niet bekend, probeer het opnieuw alsjeblieft.');
                    document.querySelector('form').reset();
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
