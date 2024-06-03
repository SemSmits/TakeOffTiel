document.querySelector("form").addEventListener("submit", function (event) {
  event.preventDefault();

  const username = document.querySelector('input[name="username"]').value;
  const password = document.querySelector('input[name="password"]').value;
  const email = document.querySelector('input[name="email"]').value;
  const role = "user";

  fetch("/auth/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: `username=${username}&password=${password}&email=${email}&role=${role}`,
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.token) {
        localStorage.setItem("token", data.token);
        alert("Registration successful. You can now log in.");
        window.location.href = "/src/pages/login.html";
      } else {
        alert("Registration failed: " + data.error);
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
});
