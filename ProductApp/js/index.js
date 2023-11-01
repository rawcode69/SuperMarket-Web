document.addEventListener("DOMContentLoaded", function () {
    getProducts();
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