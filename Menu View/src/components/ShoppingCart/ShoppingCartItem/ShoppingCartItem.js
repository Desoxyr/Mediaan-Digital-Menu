import React from 'react';
import {Row, Col, Button} from "react-bootstrap";
import './ShoppingCartItem.sass'

const ShoppingCartItem = ({ dish, changeAmount, index}) => {

    const plusButtonClick = () => {
        changeAmount(index, dish.dishId, dish.amount + 1);
    }

    const minusButtonClick = () => {
        changeAmount(index, dish.dishId, dish.amount - 1);
    }

    function renderCartItem()
    {
        return (
            <div>
                <hr className="shoppingcartitem-hr"/>
                <Row>
                    <Col>
                        <h4>{dish.name}</h4>
                    </Col>
                    <Col className="price">
                        <h4>{(dish.price * dish.amount).toFixed(2)}</h4>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <h5>{dish.description}</h5>
                    </Col>
                </Row>
                <Row>
                    <div className="shoppingcartitem-button-wrapper">
                        <Button className="shoppingcartitem-button" id="compensateMin" onClick={() => minusButtonClick()}>-</Button>
                        <h5 className="dish-amount">{dish.amount}</h5>
                        <Button className="shoppingcartitem-button" id="compensatePlus" onClick={() => plusButtonClick()}>+</Button>
                    </div>

                </Row>
            </div>
        );
    }

    return (
        renderCartItem()
    );
};

export default ShoppingCartItem;
