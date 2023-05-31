function getPatient(){
    //servPatient
    let email = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3002/farm_backend/ServePatients?email=" + email + "&session="+ session,true);
    

    http.onload = function(){
        if(this.readyState== 4 && http.status==200){
            let listPatients = JSON.parse(http.response);
            let selectPcientes = document.getElementById("pacientes");
            selectPcientes.innerHTML= "";
            var i = 0;
            while(i<listPatients.length){
                for (var key in listPatients[i]){
                    
                    const option = document.createElement("option");
                    option.value = listPatients[i][key];
                    option.text = listPatients[i][key];
                    selectPcientes.appendChild(option);
                }
                i++;
            }
            
        }
    }
    http.send();
}

function getMedicine(){
    //servMedicine
    let email = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3002/farm_backend/ServeMedicines?email=" + email + "&session="+ session,true);

    http.onload = function(){
        if(this.readyState== 4 && http.status==200){
            
            let listPatients = JSON.parse(http.response);
            let selectMedicines = document.getElementById("medicamentos");
            selectMedicines.innerHTML= "";
            
            const keys = Object.keys(listPatients);
            for(let x = 0; x < listPatients.length; x++){
                const option = document.createElement("option");
                option.value = listPatients[x].id;
                option.text = JSON.stringify(listPatients[keys[x]]);
                selectMedicines.appendChild(option);
            }
            
        }
    }
    http.send();

}

function enviar(){
    console.log("enviando")
    //servRelease
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    let xip = document.getElementById("xip").value;
    let pacientes = document.getElementById("pacientes").value;
    var medicamentos = document.getElementById("medicamentos").value;
    let date = document.getElementById("date").value;
   
    var http = new XMLHttpRequest();
    http.open("POST","http://localhost:3002/farm_backend/ServRel",true);
    http.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    http.send("mail=" + mail +"&session=" + session + "&xip=" + xip + "&pacientes=" + pacientes + "&medicamentos=" 
    + medicamentos + "&date=" + date);
    http.onload = function(){

        if(this.readyState== 4 && http.status==200){
            var response = http.responseText;

            if(response !== null ){
                alert("XIP dado de alta");
            }else{
                alert("Error no se ha podido dar de alta el xip")
            }


        }
        
    }

}

function goGestio(){
    window.location.href = "./Gestion.html";
}

window.onload = function(){
    getPatient();
    getMedicine();
}