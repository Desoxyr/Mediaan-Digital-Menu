import React from "react";
import "./Home.sass";

const Home = () => {
  return (
    <div>
      <header class="header-home">
        <div class="home">
          <h1 class="title-home">Management</h1>
          <div class="home-buttons">
            <a
              href="http://localhost:3002/add-dish"
              class="home-button pulsate"
            >
              Add Dishes
            </a>
            <a
              href="http://localhost:3002/remove-dish"
              class="home-button pulsate"
            >
              Dishes
            </a>
            <a
              href="http://localhost:3002/add-ingredient"
              class="home-button pulsate"
            >
              Ingredients
            </a>
            <a href="http://localhost:3002/tables" class="home-button pulsate">
              Tables
            </a>
          </div>
        </div>
      </header>
    </div>
  );
};

export default Home;
