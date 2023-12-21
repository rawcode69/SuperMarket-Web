import axios from "axios";
import { useState } from "react"
import { useNavigate } from "react-router-dom";

const Login = () => {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleUsername = (event) => {
    setUsername(event.target.value);
  }

  const handlePassword = (event) => {
    setPassword(event.target.value);
  }

  const handleLogin = async (event) =>{

    event.preventDefault();

    const data = {
      "username":username,
      "password":password
    }

    const response = await axios.post("http://localhost:8080/auth/login",data);

    if(response.status === 200){
      navigate('/')
      localStorage.setItem("token",response.data)
      axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}` // set as the default token for the axios. Now this has been set to the header of the request.
      console.log(response.data)
    }else{
      console.log("Login error")
    }
  }

  return (
    <div className="login-box">

      <div className="form-group">
      <h1 className="mt-3 mb-3">User Login</h1>
        <form onSubmit={handleLogin}>

          <div className="form-group mb-3">
            <input className="form-control" type="text" placeholder="Username" onChange={handleUsername} />
          </div>

          <div className="form-group mb-3">
            <input className="form-control" type="password" placeholder="Password" onChange={handlePassword} />
          </div>

          <button type="submit" className="btn btn-primary">Login</button>
        </form>
      </div>
    </div>
  )
}
export default Login;