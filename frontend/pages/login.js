document.querySelector("form").addEventListener("submit", function (event) {
  event.preventDefault();

  const username = document.querySelector('input[name="username"]').value;
  const password = document.querySelector('input[name="pass"]').value;

  fetch("/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: `username=${username}&password=${password}`,
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.token) {
        localStorage.setItem("token", data.token);
        window.location.href = "/src/pages/account.html";
      } else {
        alert("Invalid credentials, please try again.");
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});
