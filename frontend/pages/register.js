document.querySelector("#register").addEventListener("click", function () {
    let formData = new FormData(document.querySelector("#registerForm"));
    let jsonRequestBody = {};
    formData.forEach((value, key) => {
        jsonRequestBody[key] = value;
    });
    console.log(jsonRequestBody);
    fetch("/restservices/account/register", {
        method: "POST",
        body: JSON.stringify(jsonRequestBody),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then((response) => {
            if (!response.ok) {
                return response.json().then((data) => {
                    throw new Error(data.error);
                });
            }
            return response.json();
        })
        .then((data) => {
            if (data.message) {
                alert("Registration successful. You can now log in.");
                window.location.href = "./login.html";
            } else {
                alert("Registration failed: " + data.error);
            }
        })
        .catch((error) => {
            console.error("Error:", error);
            alert("Registration failed: " + error.message);
        });
});
