let start = {
    getProductsBySupplier: async function (supplierId) {
        let url = "/supplier" + "?supplier_id=" + supplierId;


    },

    getProductsByCategory: async function (categoryId) {
        let url = "/category" + "?category_id=" + categoryId;
        location.replace(url);

    },


    initFilters: function () {
        let categoriesDropdown = document.getElementById("categoriesDropdownDiv");
        let suppliersDropdown = document.getElementById("suppliersDropdownDiv");
        for(let category of categoriesDropdown.children){
            category.addEventListener('click', () => start.getProductsByCategory(category.dataset.id));
        }
        for(let supplier of suppliersDropdown.children){
            supplier.addEventListener('click', () => start.getProductsBySupplier(supplier.dataset.id));
        }
    },
}

start.initFilters();

