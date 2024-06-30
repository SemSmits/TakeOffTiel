import"./modulepreload-polyfill-B5Qt9EMX.js";/* empty css              *//* empty css                  */window.onload=function(){const o=sessionStorage.getItem("myJWT");if(!o){window.location.href="./login.html";return}fetch("/restservices/user",{method:"GET",headers:{Authorization:"Bearer "+o,"Content-Type":"application/json"}}).then(t=>{if(!t.ok)throw new Error("Network error. Status: "+t.status);return t.json()}).then(t=>{document.getElementById("username").textContent=t.username,document.getElementById("email").textContent=t.email;const r=document.getElementById("appointments");t.appointments.forEach(e=>{const n=document.createElement("div");n.classList.add("appointment"),n.innerHTML=`
                    <p><strong>Naam:</strong> ${e.receiverName}</p>
                    <p><strong>Email:</strong> ${e.receiverEmail}</p>
                    <p><strong>Datum:</strong> ${new Date(e.date).toLocaleDateString()}</p>
                    <p><strong>Starttijd:</strong> ${new Date(e.startTime).toLocaleTimeString()}</p>
                    <p><strong>Eindtijd:</strong> ${new Date(e.endTime).toLocaleTimeString()}</p>
                    <p><strong>Informatie:</strong> ${e.information}</p>
                    <p><strong>Status:</strong> <span id="status-${e.id}">${e.status}</span></p>
                    <button class="accept-btn" data-id="${e.id}">Accepteer</button>
                    <button class="deny-btn" data-id="${e.id}">Weiger</button>
                `,r.appendChild(n)})}).catch(t=>{console.error("Error:",t),alert("Failed to fetch user: "+t.message),window.location.href="./login.html"})};
//# sourceMappingURL=customer_account-5k-Sk0KI.js.map
