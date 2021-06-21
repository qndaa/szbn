import React from "react";
import SubscriberLayout from "../layouts/subscriberLayout";
import CourseListing from "../components/courses/courseListing";
import axios from "axios";

class EnrolledCourses extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            enrolled : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/users/enrolled/' + localStorage.getItem('id'))
            .then(res => {
                this.setState({
                    enrolled : res.data
                })
            })
    }

    render() {
        return (
            <SubscriberLayout>
                    <CourseListing courses={this.state.enrolled} perspective={'enrolledCourses'}/>
            </SubscriberLayout>
        );
    }
}

export default EnrolledCourses
