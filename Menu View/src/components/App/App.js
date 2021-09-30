import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Layout from "../Layout/Layout";
import ShoppingCart from "../ShoppingCart/ShoppingCart";
import Categories from "../Categories/Categories";
import "bootstrap/dist/css/bootstrap.min.css";
import React, {useState} from "react";
import DishesByCategory from "../DishesByCategory/DishesByCategory";
import "./App.sass";

function App() {
    const [tipTotal, setTipTotal] = useState(0);

    const [products] = useState([
        {
            id: 1,
            name: "Tomato Soup",
            price: "4.00",
            description: "This is soup made with tomatoes",
        },
        {
            id: 2,
            name: "Salad",
            price: "3.00",
            description: "Salad with dressing",
        },
        {
            id: 3,
            name: "Steak",
            price: "6.00",
            description: "Lorem Ipsum",
        },
        {
            id: 4,
            name: "Soup of the day",
            price: "3.50",
            description: "Ask the waiter for more information",
        },
    ]);

    return (
        <Router>
            <Switch>
                <Route exact path='/'>
                    <Layout title="Categories">
                        <Categories products={products}/>
                    </Layout>
                </Route>
                <Route exact path='/categories'>
                    <Layout title="Categories">
                        <Categories products={products}/>
                    </Layout>
                </Route>
                <Route exact path="/checkout">
                    <Layout title="Shopping Cart">
                        <ShoppingCart
                            products={products}
                            tipTotal={tipTotal}
                            setTipTotal={setTipTotal}
                        />
                    </Layout>
                </Route>
                <Route exact path='/category/**'>
                    <Layout title="Category">
                        <DishesByCategory/>
                    </Layout>
                </Route>
                <Route path="/table/**" >
                    <Layout>
                        <Categories products={products}/>
                    </Layout>
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
