import"./modulepreload-polyfill-B5Qt9EMX.js";document.querySelector("form").addEventListener("submit",function(t){t.preventDefault();const n=document.querySelector('input[name="username"]').value,o=document.querySelector('input[name="pass"]').value;fetch("/api/auth/login",{method:"POST",headers:{"Content-Type":"application/x-www-form-urlencoded"},body:`username=${n}&password=${o}`}).then(e=>e.json()).then(e=>{e.token?(localStorage.setItem("token",e.token),window.location.href="/src/pages/account.html"):alert("Invalid credentials, please try again.")}).catch(e=>{console.error("Error:",e)})});
//# sourceMappingURL=login-h5O46Yd3.js.map
