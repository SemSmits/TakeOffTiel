document.addEventListener("DOMContentLoaded", () => {
    function login(event) {
        event.preventDefault();

        let formData = new FormData(document.querySelector("#loginForm"));
        let jsonRequestBody = {};
        formData.forEach((value, key) => {
            jsonRequestBody[key] = value;
        });
        console.log(jsonRequestBody);
        fetch("/api/login", {
            method: "POST",
            body: JSON.stringify(jsonRequestBody),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => {
                console.log("Response: " + response.status);
                if (response.ok) return response.json(); // if 200 there will be a body
                else throw "Wrong username/password"; // if !200 there will be no body
            })
            .then(myJson => {
                console.log(myJson);
                window.sessionStorage.setItem("myJWT", myJson.JWT);
                window.location.href = "./account.html";
            })
            .catch(error => console.log(error)) // to handle the possibly thrown error
    }

    document.querySelector("#loginForm").addEventListener("submit", login);
});
