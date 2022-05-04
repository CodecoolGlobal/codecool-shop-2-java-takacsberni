let categoriesDropdown = document.getElementById("categoriesDropdownDiv");
let suppliersDropdown = document.getElementById("suppliersDropdownDiv");

for(let category of categoriesDropdown.children){
    category.addEventListener('click', () => alert('Category clicked!'));
}

for(let supplier of suppliersDropdown.children){
    supplier.addEventListener('click', () => init.getProductsBySupplier(supplier.dataset.id));
}

let init = {
    getProductsBySupplier: async function (supplierId) {
        let url = "/supplier/" + "?supplier_id=" + supplierId;
        let data = await init.getData(url);
        init.showData(data);
    },

    getData: async function (url) {
        let response = await fetch(url);
        return response.json();
    }
}

