import React, {useState, useEffect} from "react";
import "./ShoppingCart.sass";
import Container from "react-bootstrap/container";
import Row from "react-bootstrap/row";
import Col from "react-bootstrap/col";
import ShoppingCartItem from "./ShoppingCartItem/ShoppingCartItem";
import Tips from "./Tips/Tips";
import axios from "axios";
import {Link} from "react-router-dom";
import {updateSession} from "../DishesByCategory/DishItem"

const ShoppingCart = ({tipTotal, setTipTotal}) => {

    const [dishes, setDishes] = useState([]);

    let shoppingCartDishes = JSON.parse(sessionStorage.getItem("ShoppingCartList"));

    //Get Information about dishes in shopping cart (name, price etc)
    useEffect(() => {
        const fetchDishes = async () => {
            const result = await axios.post(
                'http://localhost:9191/menu/dishes/all-in-shopping-cart',
                getDishIds(shoppingCartDishes)
            );
            return result.data;
        }
        fetchDishes().then(r => setDishes(r));
        getPriceTotal(dishes, tipTotal);
    }, []);

    //Retrieve amount of Dish from Local Storage
    for (let dish of dishes) {
        dish.amount = 0;
        for (let shoppingCartDish of shoppingCartDishes) {
            if (dish.dishId === shoppingCartDish.dishId) {
                dish.amount = shoppingCartDish.amount
            }
        }
    }

    const changeAmount = (index, dishId, amount) => {
        updateSession(dishId, amount)
        dishes[index].amount = amount
        setDishes(dishes.filter(dish => dish.amount > 0));
    }


    //Complete Order & Save to DB
    const saveOrder = () => {
        async function onSaveOrder() {
            const order = {
                tableId: JSON.parse(sessionStorage.getItem("table")),
                orderStatus: "ToDo",
                totalPrice: getPriceTotal(dishes, tipTotal),
                tip: tipTotal,
                dateTime: new Date()
            }
            const orderDto = {
                foodOrder: order,
                orderLines: shoppingCartDishes
            }

            await axios.post('http://localhost:9191/orders/create', orderDto,
            ).then();
        }
        onSaveOrder().then(() => clearLocalStorage())
    }

    return (
        <div className="shoppingcart-wrapper">
            <Container>
                {dishes.map((dish, i) => (
                    <ShoppingCartItem dish={dish} changeAmount={changeAmount} index={i}/>
                ))}
                <div>
                    <hr/>
                    <Row>
                        <Col>
                            <h4>SubTotal</h4>
                        </Col>
                        <Col className="price">
                            <h4>{getPriceSubTotal(dishes).toFixed(2)}</h4>
                        </Col>

                    </Row>
                    <Row>
                        <Col>
                            <Tips setTipTotal={setTipTotal}/>
                        </Col>
                        <Col className="price">
                            <h4>{parseFloat(tipTotal).toFixed(2)}</h4>
                        </Col>
                    </Row>
                    <br/>
                    <Row>
                        <Col>
                            <h4>Total</h4>
                        </Col>
                        <Col className="price">
                            <h4>€ {getPriceTotal(dishes, tipTotal).toFixed(2)}</h4>
                        </Col>
                    </Row>
                    <Link to="/checkout">
                        <button className="orderButton" onClick={saveOrder}>
                            Order!
                        </button>
                    </Link>
                </div>
            </Container>
        </div>
    );
};

export default ShoppingCart;

function clearLocalStorage() {
    sessionStorage.removeItem("ShoppingCartList");
    //Refresh Page
    window.location.reload(false)
}

function getDishIds(shoppingCartDishes){
    let dishIDs = [];
    if (shoppingCartDishes != null) {
        for (let dish of shoppingCartDishes) {
            dishIDs.push(dish.dishId);
        }
    }
    return dishIDs;
}

export function getPriceTotal(dishes, tipTotal) {
    return getPriceSubTotal(dishes) + Number(tipTotal);
}

export function getPriceSubTotal(dishes) {
    let total = 0;
    for (let i = 0; i < dishes.length; i++) {
        total = total + parseFloat(dishes[i].price * dishes[i].amount);
    }
    return total;
}
