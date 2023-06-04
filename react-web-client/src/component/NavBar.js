import React from 'react';
import Container from 'react-bootstrap/Container';
import {Button} from "react-bootstrap";
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { NavLink, useNavigate } from 'react-router-dom';
import { GENERATE_ROUTE, MESSAGE_ROUTE } from '../utils/const';


const NavBar = () => {
    const navigate = useNavigate()
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <NavLink style={{ color: 'white' }} to={MESSAGE_ROUTE}>Le chat</NavLink>
                <Nav className="ml-auto" style={{ color: 'white' }}>
                    <Button variant={"outline-light"} onClick={() => navigate(GENERATE_ROUTE)}>Generate</Button>
                </Nav>
            </Container>
        </Navbar>
    );
};

export default NavBar;