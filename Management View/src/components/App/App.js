import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Layout from "../Layout/Layout";
import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import CreateFood from "../CreateFood/CreateFood";
import ManageCategories from "../ManageCategories/ManageCategories";
import Home from "../Home/Home";
import ManageInventory from "../ManageInventory/ManageInventory";
import Tables from "../Tables/Tables";
import DishManagement from "../DishManagement/DishManagement";
import "./App.sass";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <Layout title="Manage">
            <Home />
          </Layout>
        </Route>

        <Route exact path="/add-dish">
          <Layout title="Add Dish">
            <CreateFood />
          </Layout>
        </Route>
        <Route exact path="/remove-dish">
          <Layout title="Manage Dishes">
            <DishManagement />
          </Layout>
        </Route>
        <Route exact path="/manage-categories">
          <Layout title="Manage Categories">
            <ManageCategories />
          </Layout>
        </Route>
        <Route exact path="/add-ingredient">
          <Layout title="Add Ingredient">
            <ManageInventory />
          </Layout>
        </Route>
        <Route exact path="/tables">
          <Layout title="Tables">
            <Tables />
          </Layout>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
