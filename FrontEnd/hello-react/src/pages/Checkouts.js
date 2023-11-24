import { useEffect, useState } from "react";
import axios from "axios"

const Checkouts = () => {

  useEffect(() => {
    getProducts();
  })

  const [products, setProducts] = useState(null);
  const [addProducts, setAddProducts] = useState([]);

  const getProducts = async () => {
    const response = await axios.get('http://localhost:8080/products')
    setProducts(response.data)
  }

  //This is the function I need implement. This is not working as I wisheda
  const addToOrder = (item) => {
    const newProducts = [...addProducts, item];
    setAddProducts(newProducts);
  }


  const data = addProducts.map(product => {
    return product.id;
  })

  const placeOrder = () => {
    axios.post('http://localhost:8080/orders', { products: data }, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(response => {

      console.log(response.data);
    })
      .catch(error => {

        console.error(error);
      });

  }

  let total = 0;

  return (
    <>
      <h1>Checkouts</h1>
      <div className="row">
        <div className="col-6">
          <h2>Products</h2>

          {products && products.map(product => (
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">{product.name}</h5>
                <p class="card-text">{product.price}</p>
                <a href="#" class="btn btn-outline-primary" onClick={() => addToOrder(product)} >Add to order</a>
              </div>
            </div>
          ))}

        </div>
        <div className="col-6">
          <h2>Order</h2>

          <table className="table table-stripped">
            <thead>
              <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
              {addProducts && addProducts.map(product => (
                <tr>
                  <td>{product.id}</td>
                  <td>{product.name}</td>
                  <td>{product.price}</td>
                </tr>
              ))}
              <tr>
                {addProducts && addProducts.map(product => {
                  total += product.price;
                })}
                <th colSpan={2}>Total</th>
                <th>{total}</th>
              </tr>
            </tbody>
          </table>

          <button className="btn btn-primary" onClick={()=>placeOrder()}>Place Order</button>

        </div>
      </div>
    </>
  )
}

export default Checkouts;