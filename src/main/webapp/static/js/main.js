let categoriesDropdown = document.getElementById("categoriesDropdownDiv");
let suppliersDropdown = document.getElementById("suppliersDropdownDiv");

for(let category of categoriesDropdown.children){
    category.addEventListener('click', () => alert('Category clicked!'));
}

for(let supplier of suppliersDropdown.children){
    supplier.addEventListener('click', () => alert('Supplier clicked!'));
}