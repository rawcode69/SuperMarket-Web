document.addEventListener("DOMContentLoaded", function () {

  getCategories();

})

const getCategories = () => {

  fetch("http://localhost:8080/categories").then(response => {

    return response.json();

  }).then(data => {

    let categories = "";

    data.map(category => {

      categories += `<option value="${category.id}">${category.name}</option>`;

    });

    document.getElementById("CategoryId").innerHTML = categories;

  }).catch(error => {

    console.log(error);

  })
}

const createProduct = (event) => {
  event.preventDefault();

  let name = document.getElementById("name").value;
  let price = document.getElementById("price").value;
  let qty = document.getElementById("qty").value;
  let categoryId = document.getElementById("CategoryId").value;

  let data = {
    "name": name,
    "price": price,
    "qty": qty,
    "categoryID": categoryId
  }

  fetch("http://localhost:8080/products", {

    method: 'POST',
    body: JSON.stringify(data),

    headers:{
      "Content-Type":"application/json"
    }

  }).then(response => {
      return response.json();
    }).then(data => {
      console.log(data);
    }).catch(error => {
      console.log(error);
    })
}