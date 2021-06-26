import React from "react";
import CourseListing from "../components/courses/courseListing";
import axios from "axios";
import AdminLayout from "../layouts/adminLayout";

class AdminHome extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courses : []
        }
    }

    componentDidMount = async () => {
        if (localStorage.getItem("id") === "null") {
            this.props.history.push('/login');
            return;
        }

        axios
            .get('http://localhost:8080/courses')
            .then(res => {
                this.setState({
                    courses : res.data
                })
            })
    }

    render() {
        return (
            <AdminLayout>
                <CourseListing courses={this.state.courses} perspective={'admin'}/>
            </AdminLayout>
        )
    }
}

export default AdminHome
