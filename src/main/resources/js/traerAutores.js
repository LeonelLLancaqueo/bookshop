

let tbodyAutores= document.getElementById("bodyTablaAutores");

window.addEventListener("load" , ()=> {
    traerAutores();
})


async function traerAutores(){

    await fetch("http://localhost:8080/autor")
    .then( autores => {
        console.log(autores)
        return autores.json;
    })
    .then(data => {
        console.log(data);
    })
}