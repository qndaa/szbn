import React from "react";
import SubscriberLayout from "../layouts/subscriberLayout";
import CourseListing from "../components/courses/courseListing";
import axios from "axios";

class FinishedCourses extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            finished : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/users/finished/' + localStorage.getItem('id'))
            .then(res => {
                this.setState({
                    finished : res.data
                })
            })
    }

    render() {
        return (
            <SubscriberLayout>
                <CourseListing courses={this.state.finished} perspective={'finishedCourses'}/>
            </SubscriberLayout>
        );
    }
}

export default FinishedCourses
