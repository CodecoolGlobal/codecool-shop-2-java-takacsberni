

    async function updateItemNumber (event) {
        let productName = event.target.dataset.prod_name
        let productPrice = event.target.dataset.prod_price
        let url = "/order"
        let data = {"prod_name": productName, "prod_price": productPrice}
        let response = await getData(url, data);
        console.log(response);
    }

        async function getData(url, data) {
        fetch(url, {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        }).then(res => {
            return res;
        });
}
    let itemNumber = document.getElementById("itemNumber");
    let addToCartLink = document.getElementsByClassName("addToCart");

    for(let link of addToCartLink){
    link.addEventListener('click', updateItemNumber);
}



