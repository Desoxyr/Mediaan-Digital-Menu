import React from "react";
import { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import axios from "axios";

const DishDetails = ({ onFormChange }) => {
  const [category, setCategory] = useState([]);

  //useEffects
  useEffect(() => {
    axios
      .get("http://localhost:9191/menu/categories/all")
      .then((res) => {
        setCategory(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  //onNameChange passes down the value of the onChange event to onFormChange
  //The value is kept in parent state so it can be submitted there
  const onInputChange = (e) => {
    let name = e.target.name;
    let value = e.target.value;
    onFormChange({ name, value });
  };

  return (
    <>
      <div className="productDetails">
        <Form>
          <Form.Group controlId="formBasicEmail">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Name"
              name="name"
              onChange={onInputChange}
            />
          </Form.Group>

          <Form.Group controlId="formBasicPassword">
            <Form.Label>Price</Form.Label>
            <Form.Control
              type="text"
              placeholder="Price"
              name="price"
              onChange={onInputChange}
            />
          </Form.Group>

          <Form.Group controlId="exampleForm.ControlTextarea1">
            <Form.Label>Description</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              placeholder="Description"
              name="description"
              onChange={onInputChange}
            />
          </Form.Group>

          <select
            className="form-control"
            id="category"
            name="category"
            onChange={onInputChange}
          >
            <option selected disabled>
              Category
            </option>
            {category.map((category) => (
              <option
                key={category.categoryId}
                value={category.categoryId}
                onChange={onInputChange}
              >
                {category.name}
              </option>
            ))}
          </select>

          <Form.Group controlId="formBasicPassword">
            <Form.Label>Image Link</Form.Label>
            <Form.Control
              type="text"
              placeholder="https://site.nl/image"
              name="image"
              onChange={onInputChange}
            />
          </Form.Group>
        </Form>
      </div>
    </>
  );
};

export default DishDetails;
