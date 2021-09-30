import DishDetails from "./DishDetails";
import IngredientDetails from "./IngredientDetails";
import React, { useState } from "react";
import "./CreateFood.sass";
import Row from "react-bootstrap/row";
import Col from "react-bootstrap/col";
import Container from "react-bootstrap/container";
import axios from "axios";
import {Button} from "react-bootstrap";

const CreateFood = () => {
  const [info, setInfo] = useState({
    category: 1,
  });
  const [ingredients, setIngredients] = useState([]);

  const onFormSubmit = () => {
    const dishes = {
      categoryId: info["category"],
      name: info["name"],
      price: info["price"],
      description: info["description"],
      imageUrl: info["image"],
      ingredients: ingredients,
    };

    axios.post("http://localhost:9191/menu/dishes/create", dishes)
        .then(window.location.reload(true));
  };

  // This method is passed down to child components.
  // Child components pass this method data so it can be kept in CreateFood.js state
  // This way the state can be used when submitting data
  const onFormChange = ({ name, value }) => {
    let copy = info;
    copy[name] = value;
    setInfo(copy);
  };

  return (
    <div className="page-wrapper">
      <Container>
        <Row>
          <Col md={6}>
            <DishDetails onFormChange={onFormChange} />
          </Col>
          <Col md={6}>
            <IngredientDetails
              selectedIngredients={ingredients}
              onIngredientChange={setIngredients}
            />
          </Col>
        </Row>
        <Row>
          <Col id="buttonRow">
            <div className="divButtons">
              <Button
                onClick={onFormSubmit}
                id="add-button"
                type="button"
                className="btn btn-primary"
              >
                Add
              </Button>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default CreateFood;
