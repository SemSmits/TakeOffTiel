import"./modulepreload-polyfill-B5Qt9EMX.js";/* empty css              */document.addEventListener("DOMContentLoaded",function(){document.querySelector("#loginForm").addEventListener("submit",function(n){n.preventDefault();const t=document.querySelector('input[name="username"]').value,o=document.querySelector('input[name="pass"]').value;fetch("/api/auth/login",{method:"POST",headers:{"Content-Type":"application/x-www-form-urlencoded"},body:`username=${t}&password=${o}`}).then(e=>e.json()).then(e=>{e.token?(localStorage.setItem("token",e.token),window.location.href="./frontend/account.html"):alert("Invalid credentials, please try again.")}).catch(e=>{console.error("Error:",e)})})});
//# sourceMappingURL=login-Hs1J9Crq.js.map