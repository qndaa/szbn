import React from "react";
import AdminLayout from "../layouts/adminLayout";
import SubscriberListing from "../components/subscriberListing";
import axios from "axios";

class AdminBlockedUsers extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            blocked : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/users/block-info')
            .then(res => {
                this.setState({
                    blocked : res.data
                })
            })
    }

    render() {
        return (
            <AdminLayout>
                <SubscriberListing data={this.state.blocked} perspective={'admin'} unblock={this.unblock}/>
            </AdminLayout>
        );
    }

    unblock = (id) => {
        axios
            .get('http://localhost:8080/users/block-unblock/' + id)
            .then(_ => {
                this.setState({
                    blocked : this.state.blocked.filter(s => s.id !== id)
                })
            })
    }
}

export default AdminBlockedUsers
