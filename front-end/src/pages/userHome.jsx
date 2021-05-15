import React from "react";
import CourseListing from "../components/courses/courseListing";
import CourseSearchPane from "../components/courses/courseSearchPane";
import SubscriberLayout from "../layouts/subscriberLayout";

class UserHome extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courses : []
        }
    }

    componentDidMount() {
        this.setState({
            courses : [
                {
                    courseId : '123',
                    name : 'AI Basics',
                    price : '1230'
                },
                {
                    courseId : '124',
                    name : 'AI Advanced',
                    price : '1235'
                },
                {
                    courseId : '124',
                    name : 'AI Advanced',
                    price : '1235'
                },
                {
                    courseId : '124',
                    name : 'AI Advanced',
                    price : '1235'
                },
                {
                    courseId : '124',
                    name : 'AI Advanced',
                    price : '1235'
                },
                {
                    courseId : '124',
                    name : 'AI Advanced',
                    price : '1235'
                }
            ]
        })
    }

    render() {
        return (
            <SubscriberLayout>
                <CourseSearchPane/>
                <CourseListing courses={this.state.courses}/>
            </SubscriberLayout>
        )
    }
}

export default UserHome