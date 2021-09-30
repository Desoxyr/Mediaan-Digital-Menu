import "./Header.sass";
import { Nav, Navbar } from "react-bootstrap";
import { MdRestaurantMenu } from "react-icons/md";
import { AiOutlineShoppingCart } from "react-icons/ai";
import { Link } from "react-router-dom";
import logo from "../../assets/m_sticky_header.png"

import React from "react";

const Header = ({ headerTitle }) => {
  return (
    <div className="header-wrapper">
      <Navbar className="navbar" expand="lg">
        <Link to="/">
          <Navbar.Brand>
            <img src={logo} id="logo" alt="logo" />
            <a className="header-title">
              {headerTitle}
            </a>
          </Navbar.Brand>
        </Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            <Link className="link" to="/checkout">
              <AiOutlineShoppingCart size={30} />
            </Link>
            <Link className="link" to="/categories">
              <MdRestaurantMenu id="icon" size={30} />
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default Header;
