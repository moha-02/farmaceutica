function enviar(){
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3002/farm_backend/login?email=" + email + "&password="+ password,true);

    http.onreadystatechange = function(){
        
        if(this.readyState== 4 && http.status==200){
            
            var session =http.responseText;
            
            if(session !== null){
              
              sessionStorage.setItem("session",session)
              sessionStorage.setItem("mail",email);
              
              window.location.href = "./Gestion.html";
            } else{
              alert("Sesion interrumpida.Vuelva a intentarlo.")
            }
        }
    }
    http.send();
}