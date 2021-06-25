import React from "react";
import AuthorLayout from "../layouts/authorLayout";
import axios from "axios";
import {Table} from "react-bootstrap";

class AuthorReport extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            subs : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/report/author-subscriber/' + localStorage.getItem('id'))
            .then(res => {
                this.setState({
                    subs : res.data
                })
            })
    }

    render() {
        const subs = this.state.subs.map((s, ind) => {
            return (
                <tr key={ind}>
                    <td>{ind + 1}</td>
                    <td>{s.name}</td>
                    <td>{s.surname}</td>
                    <td>{s.username}</td>
                </tr>
            )
        })

        return (
            <AuthorLayout>
                <h5 className={'mb-4'}>Users that have finished at least 3 of my courses</h5>
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    {subs}
                    </tbody>
                </Table>
            </AuthorLayout>
        );
    }

}

export default AuthorReport
