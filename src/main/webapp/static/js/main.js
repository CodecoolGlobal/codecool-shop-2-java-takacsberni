let itemNumber = document.getElementById("itemNumber");
let addToCartLink = document.getElementsByClassName("addToCart");

for(let link of addToCartLink){
    addToCartLink.addEventListener('click', init.addItemToCart)
}

let init = {
    addItemToCart: function () {
        if(itemNumber.innerHTML === `<p></p>`){
            itemNumber.innerHTML = `<p>1</p>`;
        }
        else{
            itemNumber.innerHTML = `<p>${parseInt(itemNumber.innerText )+1}</p>`;
        }

    },

}



