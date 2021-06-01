import React from "react";
import {Container, Nav, Navbar, NavLink, Row} from "react-bootstrap";

class SubscriberLayout extends React.Component {

    logout = () => {
        localStorage.setItem("id", null);
        localStorage.setItem("role", null);
    }

    render() {
        return(
            <Container fluid className={'p-0'}>
                <Navbar collapseOnSelect expand="lg" bg={'primary'} variant="dark">
                    <Navbar.Brand as={NavLink} to='/'>Courses</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link href="#home">Home</Nav.Link>
                        <Nav.Link href="#features">Features</Nav.Link>
                        <Nav.Link href="#pricing">Pricing</Nav.Link>
                    </Nav>

                    <label className={`text-white mt-2 ml-5 pl-2`}>CATEGORY OF USER: {this.props.category}</label>

                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="mr-auto"/>
                        <Nav>
                            <Nav.Link href={`/login`} ><button onClick={this.logout} className={`btn btn-primary`}>Log out</button></Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                <Container className={'p-5'}>
                {this.props.children}
                </Container>
            </Container>
        )
    }
}

export default SubscriberLayout