import"./modulepreload-polyfill-B5Qt9EMX.js";/* empty css              */document.querySelector("#register").addEventListener("click",function(){let o=new FormData(document.querySelector("#registerForm")),r={};o.forEach((e,t)=>{r[t]=e}),console.log(r),fetch("/restservices/account/register",{method:"POST",body:JSON.stringify(r),headers:{"Content-Type":"application/json"}}).then(e=>e.ok?e.json():e.json().then(t=>{throw new Error(t.error)})).then(e=>{e.message?(alert("Registration successful. You can now log in."),window.location.href="./login.html"):alert("Registration failed: "+e.error)}).catch(e=>{console.error("Error:",e),alert("Registration failed: "+e.message)})});
//# sourceMappingURL=register-DTHwpUh1.js.map