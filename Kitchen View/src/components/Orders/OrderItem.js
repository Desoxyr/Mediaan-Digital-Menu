import React, {useState, useEffect} from "react";
import {Card, Button} from "react-bootstrap";
import axios from 'axios';
import "./OrderItem.sass"

const OrderItem = ({index, orderId, tableNum, orderTime, orderStatus, onStatusUpdate}) => {

    const [DishesInOrder, setDishesInOrder] = useState([])
    const [Dishes, setDishes] = useState([])

    useEffect(() => {
        const fetchOrderLines = async () => {
            const result = await axios("http://localhost:9191/orders/orderlines/fromorder/" + orderId);
            return result.data;
        };
        fetchOrderLines().then((r) => setDishesInOrder(r));
    }, []);

    useEffect(() => {
        const fetchDishes = async () => {
            const result = await axios("http://localhost:9191/menu/dishes/all");
            return result.data;
        };
        fetchDishes().then((r) => setDishes(r));
    }, [])

    const getDishName = (orderId) => {
        return Dishes.find(x => x.dishId === orderId)?.name
    }

    const timeAgo = (prevDate) => {

        let previousDate = new Date(prevDate)
        let offset = new Date().getTimezoneOffset(); //returns 120 for GMT+1 + SummerTime
        previousDate.setMinutes(previousDate.getMinutes() - offset)

        const diff = Number(new Date()) - new Date(previousDate);

        const minute = 60 * 1000;
        const hour = minute * 60;
        const day = hour * 24;
        const month = day * 30;
        const year = day * 365;
        switch (true) {
            case diff < 0:
                return "Time Unavailible"
            case diff < minute:
                const seconds = Math.round(diff / 1000);
                return `${seconds} ${seconds > 1 ? 'seconds' : 'second'}`
            case diff < hour:
                const minutes = Math.round(diff / minute);
                return `${minutes} ${minutes > 1 ? 'minutes' : 'minute'}`;
            case diff < day:
                const hours = Math.round(diff / hour);
                return `${hours} ${hours > 1 ? 'hours' : 'hour'}`;
            case diff < month:
                const days = Math.round(diff / day);
                return `${days} ${days > 1 ? 'days' : 'day'}`;
            case diff < year:
                const months = Math.round(diff / month)
                return `${months} ${months > 1 ? 'months' : 'month'}`;
            case diff > year:
                const years = Math.round(diff / year);
                return `${years} ${years > 1 ? 'years' : 'year'}`;
            default:
                return "";
        }
    };

    const updateStatus = () => {
        onStatusUpdate(orderId)
    }

    return (
        <Card className="card-wrapper">
            <Card.Header id="card-header" style={{backgroundColor: getBackgroundColor(orderStatus)}}>
                <div className="status-bar" style={{marginBottom: "10px"}}>
                    <div id="list-id">
                        #{index + 1}
                    </div>
                    <div id="status">
                        {getStatus(orderStatus)}
                    </div>
                </div>
                <div className="status-bar">
                    <div id="tableNum">
                        Table {+tableNum}
                    </div>
                    <div id="time">
                        {timeAgo(orderTime)}
                    </div>
                </div>
            </Card.Header>
            <div id="card-body">
                {DishesInOrder.map((dish) => (
                    <div className="dish-line">
                        <div className="dish-line-content">
                            <div className="dish-line-amount">
                                {dish.amount}
                            </div>
                            <div className="dish-line-name">
                                {getDishName(dish.dishId)}
                            </div>
                        </div>
                    </div>)
                )}
            </div>
            {(() => {
                    if (orderStatus != "Done") {
                        return (
                            <Card.Footer id="card-footer">
                                <button id="status-button" onClick={updateStatus}>
                                    {getButton(orderStatus)}
                                </button>
                            </Card.Footer>
                        )
                    }
                }
            )()}

        </Card>
    );
};

export default OrderItem;


function getBackgroundColor(status) {
    if (status === "ToDo") {
        return "#e1e5eb";
    } else if (status === "InProgress") {
        return "#fbc379";
    } else if (status === "Done") {
        return "#89f397";
    }
}

function getStatus(status) {
    if (status === "ToDo") {
        return "New";
    } else if (status === "InProgress") {
        return "Preparing";
    } else if (status === "Done") {
        return "Done";
    }
}

function getButton(status) {
    if (status === "ToDo") {
        return "Start";
    } else if (status === "InProgress") {
        return "Finish";
    }
}