import"./modulepreload-polyfill-B5Qt9EMX.js";document.querySelector("form").addEventListener("submit",t=>{t.preventDefault();const o=document.querySelector('input[name="username"]').value,n=document.querySelector('input[name="pass"]').value;fetch("/login",{body:`username=${o}&password=${n}`,headers:{"Content-Type":"application/x-www-form-urlencoded"},method:"POST"}).then(e=>e.text()).then(e=>{e==="Login successful"?window.location.href="/account.html":(alert("Onjuiste invoer of account niet bekend, probeer het opnieuw alsjeblieft."),document.querySelector("form").reset())}).catch(e=>{console.error("Error:",e)})});
//# sourceMappingURL=login-DlLuZe5s.js.map