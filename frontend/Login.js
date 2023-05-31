//funcion que envia las credenciales y recibe la sesion
function enviar(){

    // asignar los valores introducidos a variables locales
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    var http = new XMLHttpRequest();
    http.open("GET","http://localhost:3002/farm_backend/login?email=" + email + "&password="+ password,true);

    http.onreadystatechange = function(){
        // Verifica si el estado de la respuesta es 200 ( que signica éxito) para continuar .
        if(this.readyState== 4 && http.status==200){
            //Obtiene el texto de la respuesta de la solicitud HTTP
            var session =http.responseText;
            
            if(session !== null){
              //Almacena el valor de la sesión en el almacenamiento de sesión del navegador y lo mismo pasa con el email
              sessionStorage.setItem("session",session)
              sessionStorage.setItem("mail",email);
              // Se pone página de gestión,ya que el login ha sido un exito
              window.location.href = "./Gestion.html";
            } else{
              alert("Sesion interrumpida.Vuelva a intentarlo.")
            }
        }
    }
    http.send();
}