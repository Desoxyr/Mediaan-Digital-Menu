import React, {useState, useEffect} from "react";
import {CardDeck, Container, Row} from "react-bootstrap";
import OrderItem from "./OrderItem";
import axios from "axios";
import {statusEnum} from './statusEnum'
import "./Order.sass"
import {refreshPage} from "./OrdersCurrent";

const Orders = () => {

    const [orders, setOrders] = useState([]);

    useEffect(() => {
       refreshPage(setOrders)
    }, []);

    return (
        <div className="page-wrapper">
            <Container>
                <Row>
                    <CardDeck>
                        {orders.filter((x) => x.orderStatus === statusEnum.Done.name)
                            .map((order, index) => (
                                <OrderItem
                                    key={order.orderId}
                                    index={index}
                                    orderId={order.orderId}
                                    title={order.name}
                                    tableNum={order.tableId}
                                    image={order.image}
                                    orderTime={order.dateTime}
                                    orderStatus={order.orderStatus}
                                />
                                )
                            )}
                    </CardDeck>
                </Row>
            </Container>
        </div>
    );
};

export default Orders;
