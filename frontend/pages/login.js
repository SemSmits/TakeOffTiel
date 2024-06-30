document.querySelector("#login").addEventListener("click", function () {
    event.preventDefault();

    let formData = new FormData(document.querySelector("#loginForm"));
    let jsonRequestBody = {};
    formData.forEach((value, key) => {
        jsonRequestBody[key] = value;
    });
    console.log(jsonRequestBody);
    fetch("/restservices/account/login", {
        method: "POST",
        body: JSON.stringify(jsonRequestBody),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            console.log("Response: " + response.status);
            if (response.ok) return response.json();
            else alert("Ongeldig email/wachtwoord combinatie.") ;
        })
        .then(myJson => {
            console.log(myJson);
            window.sessionStorage.setItem("myJWT", myJson.JWT);

            console.log(myJson.JWT);

            const payload = JSON.parse(atob(myJson.JWT.split('.')[1]));
            const role = payload.role;

            if (role === 'admin') {
                window.location.href = './admin_dashboard.html';
            } else if(role === 'customer'){
                window.location.href = './customer_dashboard.html';
            } else {
                window.location.href = './login.html';
            }
        })
        .catch(error => {
            console.log(error)
        })
});



