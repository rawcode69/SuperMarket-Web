import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'

const Category = () => {

  const [category, setCategory] = useState(null);
  const [products, setProducts] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    getCategoryById();
    getProductByCategory();
  })

  const getCategoryById = () => {
    fetch(`http://localhost:8080/categories/${id}`)
      .then(response => {
        return response.json();
      }).then(data => {
        setCategory(data);
      }).catch(errors => {
        console.log(errors);
      })
  }

  const getProductByCategory = () => {
    fetch(`http://localhost:8080/categories/${id}/products`)
      .then(response => {
        return response.json();
      }).then(data => {
        setProducts(data);
      }).catch(errors => {
        console.log(errors);
      })
  }

  return (
    <>
      {category &&
        <div>
          <h1>{category.name}</h1>

        </div>
      }

      <ul>
        {products && products.map((product) => (

          <li> <Link to={`/products/${product.id}`}>{product.name}</Link></li>
          // <li>{product.id} - {product.name}</li>

        ))}

      </ul>

    </>
  )
}

export default Category