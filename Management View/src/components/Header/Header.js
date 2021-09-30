import "./Header.sass";
import { Nav, Navbar } from "react-bootstrap";
import { CgAdd, CgRemove } from "react-icons/cg";
import { Link } from "react-router-dom";
import React from "react";
import { GiKnifeFork, GiAppleSeeds, GiRoundTable } from "react-icons/gi";
import logo from "../../assets/m_sticky_header.png";

const Header = ({ headerTitle }) => {
  return (
    <div className="header-wrapper">
      <Navbar className="navbar" expand="lg">
        <Link to="/">
          <Navbar.Brand>
            <img src={logo} id="logo" />
            <a className="header-title">{headerTitle}</a>
          </Navbar.Brand>
        </Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ml-auto">
            <Link className="link" to="/add-dish">
              <CgAdd size={30} />
            </Link>
            <Link className="link" to="/remove-dish">
              <CgRemove size={30} />
            </Link>
            <Link className="link" to="/manage-categories">
              <GiKnifeFork size={30} />
            </Link>
            <Link className="link" to="/add-ingredient">
              <GiAppleSeeds size={30} />
            </Link>
            <Link className="link" to="/tables">
              <GiRoundTable size={30} />
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default Header;
