document.querySelector("#login").addEventListener("click", function (){

        let formData = new FormData(document.querySelector("#loginForm"));
        let jsonRequestBody = {};
        formData.forEach((value, key) => {
            jsonRequestBody[key] = value;
        });
        console.log(jsonRequestBody);
        fetch("/restservices/login", {
            method: "POST",
            body: JSON.stringify(jsonRequestBody),
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => {
                console.log("Response: " + response.status);
                if (response.ok) return response.json();
                else throw "Wrong username/password";
            })
            .then(myJson => {
                console.log(myJson);
                window.sessionStorage.setItem("myJWT", myJson.JWT);
                window.location.href = "./account.html";
            })
            .catch(error => console.log(error))
    });



