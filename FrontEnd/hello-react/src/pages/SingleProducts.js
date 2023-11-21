import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"

const SingleProducts = () => {

  const [product, setProducts] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    getProductbyId();
  },)

  const getProductbyId = () => {
    fetch(`http://localhost:8080/products/${id}`)
      .then(response => {
        return response.json();
      }).then(data => {
        setProducts(data)
      }).catch(error => {
        console.log(error);
      })
  }

  return(
    <>
      {product&&

      <div>
        <h1>{product.name}</h1>
        <h2>Price: {product.price}</h2>
        <h2>Stock: {product.qty}</h2>
      </div>
        
      }
    </>
  )

}

export default SingleProducts;