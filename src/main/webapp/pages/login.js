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
                const resultDiv = document.createElement('div');
                resultDiv.innerText = data;
                document.querySelector('.window').appendChild(resultDiv);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
