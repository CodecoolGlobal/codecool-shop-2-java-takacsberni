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

