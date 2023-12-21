import axios from "axios";
import { useEffect, useState } from "react";
import { Link, json, useNavigate } from "react-router-dom";


const Home = () => {

  const [products, setProducts] = useState(null);
  const [categories, setCategories] = useState(null);

  const [name, setName] = useState(null);
  const [price, setPrice] = useState(null);
  const [qty, setQty] = useState(0);
  const [categoryId, setCategoryId] = useState(null);



  useEffect(() => {
    getProducts();
    getCategories();
  }, [])

  const navigate = useNavigate();

  const getProducts = async () => {

    try {
      const response = await axios.get('http://localhost:8080/products');
      setProducts(response.data);
    } catch (error) {

      if (error.response.status === 401) {
        console.log(error);
        navigate('/login');
      }

    }
  }

  const getCategories = async () => {

    try {
      const response = await axios.get('http://localhost:8080/categories');
      setCategories(response.data);
    } catch (error) {
      if (error.response.status === 401) {
        console.log(error);
        navigate('/login');
      }
    }

  }

  const nameHandle = (event) => {
    setName(event.target.value);
  }

  const priceHandle = (event) => {
    setPrice(event.target.value);
  }

  const qtyHandle = (event) => {
    setQty(event.target.value);
  }

  const catHandle = (event) => {
    setCategoryId(event.target.value);
  }

  const handleSubmit = (event) => {

    event.preventDefault();

    const data = {
      name: name,
      price: price,
      qty: qty,
      categoryID: categoryId
    }

    fetch('http://localhost:8080/products', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
      .then(response => {
        return response.json();
      }).then(data => {
        setProducts([...products, data])
        console.log(data);
      }).catch(errors => {
        console.log(errors);
      })

  }

  const handleLogout = () =>{
    localStorage.removeItem('token');
    navigate('/login');
  }

  return (
    <>
      <nav class="navbar navbar-expand-lg bg-body-tertiary ">

        <div class="container-fluid d-flex justify-content-between">

          <div>
            <a class="navbar-brand" href="#">Super Market</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
          </div>

          <div>
            <a class="navbar-brand" href="#">Sign In</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
          </div>

          <div>
            <a class="navbar-brand" href="#">Login</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
          </div>

          <div>
            <a class="navbar-brand" href="#" onClick={handleLogout}>Log out </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation" >
              <span class="navbar-toggler-icon"></span>
            </button>
          </div>

          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">

              {categories && categories.map(cat => (
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="#"><Link to={`/categories/${cat.id}`}>{cat.name}</Link></a>
                </li>
                // 
              ))}


            </ul>
          </div>
        </div>
      </nav>

      <ul>
        <li>
          <Link to={"/products"}>Product</Link>
        </li>
      </ul>

      <button onClick={getProducts}>Load Products</button>

      <ul>
        {products && products.map((product) => (

          <li> <Link to={`/products/${product.id}`}>{product.name}</Link></li>
          // <li>{product.id} - {product.name}</li>

        ))}

      </ul>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Product Name</label>
          <input type="text" required onChange={nameHandle}></input>
        </div>
        <div>
          <label>Product Price</label>
          <input type="text" required onChange={priceHandle}></input>
        </div>
        <div>
          <label>Product Quantity</label>
          <input type="text" required onChange={qtyHandle}></input>
        </div>
        <div>
          <label>category</label>
          <select required onChange={catHandle}>
            <option>Please Select</option>
            {categories && categories.map(cat => (
              <option value={cat.id}>{cat.name}</option>
            ))}
          </select>
        </div>
        <button className="btn btn-primary" type="submit">Save Product</button>
      </form>

    </>
  )
}

export default Home;