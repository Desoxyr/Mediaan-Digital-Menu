import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Layout from "../Layout/Layout";
import OrdersCurrent from "../Orders/OrdersCurrent";
import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import OrdersDone from "../Orders/OrdersDone";
import Stock from "../Stock/Stock";
import "./App.sass";

function App() {
    return (
        <Router>
            <Switch>
                <Route exact path="/">
                    <Layout title="Current">
                        <OrdersCurrent/>
                    </Layout>
                </Route>
                <Route exact path="/current-orders">
                    <Layout title="Current">
                        <OrdersCurrent/>
                    </Layout>
                </Route>
                <Route exact path="/finished-orders">
                    <Layout title="Done">
                        <OrdersDone/>
                    </Layout>
                </Route>
                <Route exact path="/stock">
                    <Layout title="Stock">
                        <Stock/>
                    </Layout>
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
