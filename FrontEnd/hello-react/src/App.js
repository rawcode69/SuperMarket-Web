
import './App.css';
import Car from './Car';
import { useState } from 'react'

const App = () => {

  const [userName, setUserName] = useState(null)
  const [count, setCount] = useState(0)

  const handleClick = () => {
    setUserName('Ravidu');
  }

  const increaseCount = () => {
    setCount(count + 1);
  }

  const decreaseCount = () =>{
    setCount(count -1);
  }

  return (
    <div className='App'>
      <Samplecomponent title='Sample prop title' value='25' />
      <h1>User Name is {userName}</h1>

      <button onClick={handleClick}>Set User Name</button>

      <h1>{count}</h1>
      <button onClick={increaseCount}>Increase</button>
      <button onClick={decreaseCount}>Decreaese</button>
      <Car model="Toyota Corolla" description="Well maintained vehicle" />
      <Car model="Totyot Land Cruiser" />

    </div>
  )
}

const Samplecomponent = (props) => {

  return (
    <div>
      <h1>{props.title} Value:{props.value}</h1>
    </div>
  )
}


export default App;
