import React, {useState, useEffect} from "react";
import Row from "react-bootstrap/row";
import Col from "react-bootstrap/col";
import axios from "axios";
import {Container} from "react-bootstrap";
import "./DishManagement.sass"

const DishManagement = () => {
    const [dishes, setDishes] = useState([]);


    useEffect(() => {
        fetchDishes();
    }, [])


    const fetchDishes = () => {
        axios.get('http://localhost:9191/menu/dishes/all')
            .then(res => {
                setDishes(res.data)
                console.log(res)
            })
            .catch(err => {
                console.log(err)
            })
    }


    const deleteDish = async (id) => {
        await axios.delete("http://localhost:9191/menu/dishes/delete/" + id,
            {
                headers: {"Content-Type": "application/json"}
            }
        )
            .then(res => {
                console.log(res)
            })
            .catch(error => {
                console.log(error)
            });
        fetchDishes()
    }


    return (
        <Container className="dish-management-wrapper">
            <Row>
                <Col>
                    <table className="table">
                        <thead className="thead-dark">
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Manage</th>
                        </tr>
                        </thead>
                        <tbody>


                        {dishes.map((dish, index) => (
                            <tr>
                                <td>
                                    <label>{dish.name}</label><br/>
                                </td>

                                <td>
                                    <div className="cBtnContainer">
                                        <button type="button" className="btn btn-danger" id={index}
                                                onClick={() => deleteDish(dish.dishId)}>Delete
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        ))}

                        </tbody>
                    </table>

                </Col>
            </Row>
        </Container>
    );
}

export default DishManagement;
