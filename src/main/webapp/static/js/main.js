let itemNumberContainer = document.getElementById("itemNumber");
let addToCartLink = document.getElementsByClassName("addToCart");

for(let link of addToCartLink){
    link.addEventListener('click', addItemToCart);
}

function addItemNumber(itemNumber) {
    itemNumberContainer.innerHTML = '';
    itemNumberContainer.innerHTML += `<p>${itemNumber}</p>`;

}

async function addItemToCart(){
    fetch('/order')
        .then(response => response.text())
        .then((response) => {
            addItemNumber(response)
        })
        .catch(err => console.log(err))
}




