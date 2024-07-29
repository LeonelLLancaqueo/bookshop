

async function register(){
    const user= document.getElementById("user").value;
    const password= document.getElementById("password").value;
    const requestUser= {"username": user, "password": password};

    await fetch("http://localhost:8080/auth/register", {
        method:"POST",
        body: JSON.stringify(requestUser),
        headers:{
            "Content-Type": "application/json",
        }
    }).then(response => {
        handleResponse(response);
    })

}
async function handleResponse(response){

    if(response.status === 200){
        console.log(response);
        console.log("success register");
        user= await response.json();
        
        localStorage.setItem("id", user.id);
        localStorage.setItem("user", user.username);
        location.assign("/src/main/resources/static/index.html");
        
    }else{
        if(response.status === 404){
            console.log(response);
            console.log("unsuccess login");
        }else{
            console.log("server error");
        }
        
    }
    
}