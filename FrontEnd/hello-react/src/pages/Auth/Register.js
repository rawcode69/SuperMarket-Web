import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = () => {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");

  const navigate = useNavigate();

  const handleUsername = (event) => {
    setUsername(event.target.value);
  }

  const handlePassword = (event) => {
    setPassword(event.target.value);
  }

  const handleEmail = (event) => {
    setEmail(event.target.value);
  }
  
  const handleAddress = (event) => {
    setAddress(event.target.value);
  }

  const handleRegister = async (event) => {

    event.preventDefault();

    const data = {
      "username": username,
      "address":address,
      "email": email,
      "password": password
    }

    const response = await axios.post("http://localhost:8080/auth/register", data);

    if (response.status === 200) {
      navigate("/login")
      // console.log("registered");
    } else {
      console.log("error");
    }

  }

  return (
    <div className="login-box">
      <div className="form-group">
        <h1 className="mb-3 mt-3">User Regiser</h1>
        <form onSubmit={handleRegister}>

          <div className="form-box mb-3">
            <input className="form-control" type="text" placeholder="Username" onChange={handleUsername} required/>
          </div>

          <div className="form-box mb-3">
            <input className="form-control" type="password" placeholder="Password" onChange={handlePassword} required />
          </div>

          <div className="form-box mb-3">
            <input className="form-control" type="text" placeholder="Address" onChange={handleAddress} required />
          </div>

          <div className="form-box mb-3">
            <input className="form-control" type="text" placeholder="E mail" onChange={handleEmail} required />
          </div>

          <button type="submit" className="btn btn-primary">Register</button>
        </form>
      </div>
    </div>
  )
}

export default Register;