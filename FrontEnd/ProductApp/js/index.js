document.addEventListener("DOMContentLoaded", function () {
    getProducts();
    getCategories();
});

const getProducts = () => {
    fetch("http://localhost:8080/products").then(response => {

        return response.json();

    }).then(data => {

        let products = "";

        data.map(product => {
            products += `<div class="col-lg-3 col-sm-6 col-12">`
            products += `<div class="card mb-3">`;
            products += `<div class="card-body">`;
            products += `<h5 class="card-title">${product.name}  <span class="badge bg-secondary ml-3">${product.category.name}</span></h5>`;
            products += `<p class="card-text">Rs. ${product.price}</p>`;
            products += `<a href="#" class="btn btn-primary">View Product</a>`;
            products += `</div>`;
            products += `</div>`;
            products += `</div>`;

        })

        document.getElementById("products").innerHTML = products;

    }).catch((er) => {
        console.log(er);
    })
}

// Thisi is the mehtod to get categories in to product view menu..
const getCategories = () => {

    fetch("http://localhost:8080/categories").then(response => {

        return response.json();

    }).then(data => {

        let categories = `<option selected onclick="getProducts()">All Categories</option>`;

        data.map(category => {

            categories += ` <option onclick="showCategories()" value="${category.id}">${category.name}</option>`;

        });

        document.getElementById("category-dropdown").innerHTML = categories;

    }).catch(error => {

        console.log(error);

    })
}


//This is the method to show products according to category..
const showCategories = () => {

    let categoryId = document.getElementById("category-dropdown").value;


    fetch(`http://localhost:8080/categories/${categoryId}/products`).then(response => {

        return response.json();

    }).then(data => {

        let products = "";

        data.map(product => {
            products += `<div class="col-lg-3 col-sm-6 col-12">`
            products += `<div class="card mb-3">`;
            products += `<div class="card-body">`;
            products += `<h5 class="card-title">${product.name}  <span class="badge bg-secondary ml-3">${product.category.name}</span></h5>`;
            products += `<p class="card-text">Rs. ${product.price}</p>`;
            products += `<a href="#" class="btn btn-primary">View Product</a>`;
            products += `</div>`;
            products += `</div>`;
            products += `</div>`;

        })

        document.getElementById("products").innerHTML = products;

    }).catch((er) => {
        console.log(er);
    })

}

