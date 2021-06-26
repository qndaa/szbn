import React from "react";
import {Container, Nav, Navbar} from "react-bootstrap";
import {NavLink} from "react-router-dom";

class SubscriberLayout extends React.Component {

    logout = () => {
        localStorage.setItem("id", null);
        localStorage.setItem("role", null);
    }

    renderCategoryOfUser = () => {
        if (localStorage.getItem('role') === 'SUBSCRIBER') {
            return (<label className={`text-white mt-2 ml-5 pl-2`}>CATEGORY OF USER: {this.props.category}</label>);
        }
    }


    render() {
        return(
            <Container fluid className={'p-0'}>
                <Navbar collapseOnSelect expand="lg" bg={'primary'} variant="dark">
                    <Navbar.Brand as={NavLink} to='/courses'>Courses</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link as={NavLink} to="/enrolled">Enrolled courses</Nav.Link>
                        <Nav.Link as={NavLink} to="/finished">Finished courses</Nav.Link>
                    </Nav>

                    {this.renderCategoryOfUser()}
                    <Navbar.Collapse id="responsive-navbar-nav">
                        <Nav className="mr-auto"/>
                        <Nav>
                            {this.renderMyCoursesNavLin()}
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

    renderMyCoursesNavLin() {
        if (localStorage.getItem('role') === 'TEACHER') {
            return (<Nav.Link href={`/myCourses`} ><button className={`btn btn-primary`}>My courses</button></Nav.Link>);
        }

    }
}

export default SubscriberLayout
