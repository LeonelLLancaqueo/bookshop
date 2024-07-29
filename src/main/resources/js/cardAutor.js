
const sectionCardsAutor= document.getElementById("list-card-autor");

window.addEventListener('load', traerAutoresForCard);



async function traerAutoresForCard(){

    await fetch("http://localhost:8080/autor")
    .then( autores => {
        return autores.json();
    })
    .then(data => {
        crearCardAutor(data);
    })
}
function ToLibrosAutor(id){
    localStorage.setItem("idAutorSeleccionado",id);
    location.assign("/src/main/resources/static/listLibros.html");
}

function crearCardAutor(listAutor){

    listAutor.forEach(autor => {
        
        let card= document.createElement("div");
        card.className= "card-autor"; 
        
        let img= document.createElement("img");
        img.src= autor.urlImage;
        let nombre= document.createElement("h2");
        nombre.textContent= autor.nombre;
        let descripcion= document.createElement("p");
        descripcion.textContent= autor.descripcion;
        
        card.appendChild(img);
        card.appendChild(nombre);
        card.appendChild(descripcion);
        const onClickAutor= () => {ToLibrosAutor(autor.idAutor)}
        card.addEventListener("click", onClickAutor, false);

        sectionCardsAutor.appendChild(card);

    });
}

 