import axios from "axios";
import { Outlet, useNavigate } from "react-router-dom";

const ProtectedRoutes = () => {

  const token = localStorage.getItem('token');
  const navigate = useNavigate();

  if(!token){

  }

  axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

  return <Outlet/> // out the all child element from the protected route

}

export default ProtectedRoutes;