import React from "react";
import {Container, Nav, Navbar} from "react-bootstrap";
import {NavLink} from "react-router-dom";

class AuthorLayout extends React.Component {

    logout = () => {
        localStorage.setItem("id", null);
        localStorage.setItem("role", null);
    }

    render() {
        return(
            <Container fluid className={'p-0'}>
                <Navbar collapseOnSelect expand="lg" bg={'primary'} variant="dark">
                    <Navbar.Brand as={NavLink} to='/author-home'>Courses</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link as={NavLink} to="/author-report">Report</Nav.Link>
                    </Nav>
                    <Nav className="mr-auto">
                        <Nav.Link as={NavLink} to="/author-report">My Courses</Nav.Link>
                    </Nav>
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

export default AuthorLayout
