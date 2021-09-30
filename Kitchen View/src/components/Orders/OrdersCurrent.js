import React, {useState, useEffect} from "react";
import {CardDeck, Container, Row} from "react-bootstrap";
import OrderItem from "./OrderItem";
import axios from "axios";
import {statusEnum} from './statusEnum'
import "./Order.sass"

const Orders = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
       refreshPage(setOrders)
    }, []);

    const onStatusUpdate = (id) => {
        let order = orders.find(x => x.orderId === id)

        if (order.orderStatus === statusEnum.ToDo.name){
            order.orderStatus = statusEnum.InProgress.name;
        }
        else if (order.orderStatus === statusEnum.InProgress.name) {
            order.orderStatus = statusEnum.Done.name;
        }

        setOrders([...orders])
        axios.put("http://localhost:9191/orders/update/" + order.orderId, order)
            .then(r => console.log(r.status))
            .catch(e => console.log(e));
    };

    return (
        <div className="page-wrapper">
            <Container>
                <Row>
                    <CardDeck>
                        {orders
                            .filter((x) => {
                                return x.orderStatus !== statusEnum.Done.name;
                            })
                            .map((order, index) => (
                                <OrderItem
                                    key = {order.orderId}
                                    index={index}
                                    orderId={order.orderId}
                                    tableNum={order.tableId}
                                    orderTime={order.dateTime}
                                    orderStatus={order.orderStatus}
                                    onStatusUpdate={onStatusUpdate}
                                />
                            ))}
                    </CardDeck>
                </Row>
            </Container>
        </div>
    );
};

export default Orders;



export function refreshPage(setOrders){
    const fetchOrders = async () => {
        const result = await axios("http://localhost:9191/orders/all");
        return result.data;
    };
    fetchOrders().then((r) => setOrders(r));

    setInterval(() => {
        const fetchOrders = async () => {
            const result = await axios("http://localhost:9191/orders/all");
            return result.data;
        };
        fetchOrders().then((r) => setOrders(r));
    }, 10000);
}