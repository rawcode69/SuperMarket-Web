const Car = (props) => {

  return (
    <div>
      <h2>{props.model}</h2>

      {props.description &&
        <p>{props.description}</p>
      }

    </div>
  )

}

export default Car