import React from "react";
import AuthorLayout from "../layouts/authorLayout";
import axios from "axios";
import CourseListing from "../components/courses/courseListing";

class AuthorHome extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courses : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/courses/author/' + localStorage.getItem('id'))
            .then(res => {
                this.setState({
                    courses : res.data
                })
            })
    }

    render() {
        return (
            <AuthorLayout>
                <CourseListing courses={this.state.courses} perspective={'author'}/>
            </AuthorLayout>
        );
    }

}

export default AuthorHome
