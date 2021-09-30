import React from 'react';
import { getPriceSubTotal, getPriceTotal } from './ShoppingCart'

const products = [
    {
        id: 1,
        name: "Tomato Soup",
        price: "4.00",
        description: "This is soup made with tomatoes",
        amount: 1
    },
    {
        id: 2,
        name: "Salad",
        price: "3.00",
        description: "Salad with dressing",
        amount: 1
    },
    {
        id: 3,
        name: "Steak",
        price: "6.00",
        description: "Lorem Ipsum",
        amount: 1

    },
    {
        id: 4,
        name: "Soup of the day",
        price: "3.50",
        description: "Ask the waiter for more information",
        amount: 1
    },
];

test('Sum SubPrice', () => {

    let SubPrice = getPriceSubTotal(products);
    expect(SubPrice).toBe(16.5);
})

test('Sum Price', () => {
    const tip = 1;

    let Price = getPriceTotal(products, tip);
    expect(Price).toBe(17.5);
})
