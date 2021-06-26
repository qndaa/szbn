import React from "react";
import {Table} from "react-bootstrap";
import axios from "axios";

class SubscriberListing extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        const subs = this.props.data.map((s, ind) => {
            return (
                <tr key={ind}>
                    <td>{ind + 1}</td>
                    <td>{s.name}</td>
                    <td>{s.surname}</td>
                    <td>{s.username}</td>
                    {this.props.perspective === 'admin' && <td>
                        <button className={'btn btn-danger'} onClick={(e) => this.unblock(s.id, e)}>Unblock</button>
                    </td>}
                </tr>
            )
        })

        return (
            <Table striped bordered hover size="sm">
                <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    {this.props.perspective === 'admin' && <th>Unblock</th>}
                </tr>
                </thead>
                <tbody>
                {subs}
                </tbody>
            </Table>
        );
    }

    unblock = (id) => {
        this.props.unblock(id)
    }

}

export default SubscriberListing
