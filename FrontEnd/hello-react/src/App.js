
import './App.scss';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
// import 'bootstrap/dist/css/bootstrap.min.css'
import Home from './pages/Home';
import Product from './pages/Product';
import SingleProducts from './pages/SingleProducts';
import Category from './pages/Category';
import Checkouts from './pages/Checkouts';

const App = () => {

  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Home />} />
        <Route path='/products' element={<Product />} />
        <Route path='/products/:id' element={<SingleProducts/>} />
        <Route path ='/categories/:id' element={<Category/>}/>
        <Route path = '/checkouts' element={<Checkouts/>}/>
      </Routes>
    </BrowserRouter>

  )

}

export default App;
