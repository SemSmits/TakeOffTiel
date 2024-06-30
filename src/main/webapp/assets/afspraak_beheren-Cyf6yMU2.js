import"./modulepreload-polyfill-B5Qt9EMX.js";/* empty css              *//* empty css                         */window.onload=function(){const o=sessionStorage.getItem("myJWT");if(!o){window.location.href="./login.html";return}fetch("/restservices/user",{method:"GET",headers:{Authorization:"Bearer "+o,"Content-Type":"application/json"}}).then(e=>{if(!e.ok)throw new Error("Network error. Status: "+e.status);return e.json()}).then(e=>{const r=document.getElementById("appointments");e.appointments.forEach(t=>{const n=document.createElement("div");n.classList.add("appointment"),n.innerHTML=`
                    <p><strong>Naam:</strong> ${t.receiverName}</p>
                    <p><strong>Email:</strong> ${t.receiverEmail}</p>
                    <p><strong>Datum:</strong> ${new Date(t.date).toLocaleDateString()}</p>
                    <p><strong>Starttijd:</strong> ${new Date(t.startTime).toLocaleTimeString()}</p>
                    <p><strong>Eindtijd:</strong> ${new Date(t.endTime).toLocaleTimeString()}</p>
                    <p><strong>Informatie:</strong> ${t.information}</p>
                    <p><strong>Status:</strong> <span id="status-${t.id}">${t.status}</span></p>
                    <button class="accept-btn" data-id="${t.id}">Accepteer</button>
                    <button class="deny-btn" data-id="${t.id}">Weiger</button>
                `,r.appendChild(n)}),document.querySelectorAll(".accept-btn").forEach(t=>{t.addEventListener("click",()=>{const n=t.getAttribute("data-id");a(n,"accepted")})}),document.querySelectorAll(".deny-btn").forEach(t=>{t.addEventListener("click",()=>{const n=t.getAttribute("data-id");a(n,"denied")})})}).catch(e=>{console.error("Error:",e),alert("Failed to fetch user: "+e.message),window.location.href="./admin_dashboard.html"})};function a(o,e){const r=sessionStorage.getItem("myJWT");fetch(`/restservices/appointment/${o}`,{method:"PUT",headers:{Authorization:"Bearer "+r,"Content-Type":"application/json"},body:JSON.stringify({status:e})}).then(t=>{if(!t.ok)throw new Error("Network error. Status: "+t.status);return t.json()}).then(t=>{document.getElementById(`status-${o}`).textContent=e}).catch(t=>{console.error("Error:",t),alert("Failed to update appointment: "+t.message)})}
//# sourceMappingURL=afspraak_beheren-Cyf6yMU2.js.map
