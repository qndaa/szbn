import React from "react";
import AdminLayout from "../layouts/adminLayout";
import {Button, Form} from "react-bootstrap";
import axios from "axios";

class AuthorRegistration extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name : '',
            surname : '',
            username : '',
            password : ''
        }
    }

    render() {
        return (
            <AdminLayout>
                <Form onSubmit={this.register}>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control name="name" placeholder="Enter name"
                                      value={this.state.name} onChange={this.handleChange}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Surname</Form.Label>
                        <Form.Control name="surname" placeholder="Enter surname"
                                      value={this.state.surname} onChange={this.handleChange}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Email address</Form.Label>
                        <Form.Control name="username" type="email" placeholder="Enter email"
                                      value={this.state.username} onChange={this.handleChange}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Password</Form.Label>
                        <Form.Control name="password" type="password" placeholder="Enter password"
                                      value={this.state.password} onChange={this.handleChange}/>
                    </Form.Group>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </AdminLayout>
        );
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    register = (e) => {
        e.preventDefault()

        const {name, surname, username, password} = this.state

        if(name.trim() === '' || surname.trim() === '' || username.trim() === '' || password.trim() === '') {
            alert('Some fields were left empty')
            return
        }

        axios
            .post('http://localhost:8080/authors', {
                name,
                surname,
                username,
                password
            })
            .then(_ => alert('success'))
    }

}

export default AuthorRegistration
