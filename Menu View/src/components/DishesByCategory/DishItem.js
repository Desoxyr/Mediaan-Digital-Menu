import React, {useEffect, useState} from 'react'
import {Card} from 'react-bootstrap'
import "./DishesByCategory.sass"

const DishItem = ({dishID, Name, ImageLink, Description, Amount}) => {

    const [amount, setAmount] = useState(Amount);
    const plusButtonClick = () => {
        setAmount(amount + 1);
    }

    const minusButtonClick = () => {
        setAmount(amount - 1);
    }

    useEffect(() => {
        //call function when something change in state
        updateSession(dishID, amount);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[amount]) //dependency added

    return (
        <Card>
            <Card.Header>{Name}</Card.Header>
            <Card.Img className='card-image' src={ImageLink}/>
            <Card.Body className="card-body">
                <Card.Text>{Description}</Card.Text>
                {amount !== 0 && <button className="dishitem-button" onClick={minusButtonClick}>-</button>}
                <Card.Text className="dish-amount">{amount}</Card.Text>
                <button className="dishitem-button" onClick={plusButtonClick}>+</button>
            </Card.Body>
        </Card>
    );
}

//Update Local Storage
export function updateSession(dishID, amount) {
    let shoppingCartList = JSON.parse(sessionStorage.getItem("ShoppingCartList"));

    if (shoppingCartList != null) {
        const dish = shoppingCartList.find(x => x.dishId === dishID)
        if (dish != null) {
            dish.amount = amount
        } else {
            shoppingCartList.push({dishId: dishID, amount: amount});
        }
    } else {
        shoppingCartList = [{dishId: dishID, amount: amount}];
    }

    shoppingCartList = shoppingCartList.filter(i => i.amount > 0);
    sessionStorage.setItem("ShoppingCartList", JSON.stringify(shoppingCartList));
}

export default DishItem