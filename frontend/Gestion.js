//procede a la pagina de alta
function irAlta(){
    window.location.href = "./Alta.html";
}

//elimina la sesion y procede a la pagina login
function logOut(){
    sessionStorage.removeItem('session');
    sessionStorage.removeItem('mail');
    window.location.href = "./Login.html";
}

//devuelve los chips correspondientes al doctor
function getTable(){
    let email = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3002/farm_backend/ServeXips?email=" + email + "&session="+ session,true);
    
    http.onreadystatechange = function(){
        if(this.readyState== 4 && http.status==200){
            let table = http.response;
            if(table !== null){
                document.getElementById("tab").innerHTML = table;
            }
            else{
                alert("Error en la conexion")
            }
            
        }
    }
    http.send();

}