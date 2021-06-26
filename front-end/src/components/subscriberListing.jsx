import React from "react";
import {Table} from "react-bootstrap";

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
                </tr>
                </thead>
                <tbody>
                {subs}
                </tbody>
            </Table>
        );
    }

}

export default SubscriberListing
