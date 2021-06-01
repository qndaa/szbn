import React from "react";
import CourseListing from "../components/courses/courseListing";
import CourseSearchPane from "../components/courses/courseSearchPane";
import SubscriberLayout from "../layouts/subscriberLayout";
import axios from "axios";
import apiUrl from "../api/ApiUrl";

class UserHome extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            courses : [],
            category: ''
        }
    }

    componentDidMount = async () => {

        if (localStorage.getItem("id") === "null") {
            this.props.history.push('/login');
            return;
        }

        await apiUrl.get('/users/updateCategory/' + localStorage.getItem("id"))
            .then((response) => {
            console.log(response);
            this.setState({category: response.data});
        })


        axios
            .get('http://localhost:8080/courses')
            .then(res => {
                this.setState({
                    courses : res.data
                })
            })
        // this.setState({
        //     courses : [
        //         {
        //             courseId : '123',
        //             name : 'AI Basics',
        //             price : '1230'
        //         },
        //         {
        //             courseId : '124',
        //             name : 'AI Advanced',
        //             price : '1235'
        //         },
        //         {
        //             courseId : '124',
        //             name : 'AI Advanced',
        //             price : '1235'
        //         },
        //         {
        //             courseId : '124',
        //             name : 'AI Advanced',
        //             price : '1235'
        //         },
        //         {
        //             courseId : '124',
        //             name : 'AI Advanced',
        //             price : '1235'
        //         },
        //         {
        //             courseId : '124',
        //             name : 'AI Advanced',
        //             price : '1235'
        //         }
        //     ]
        // })
    }

    render() {
        return (
            <SubscriberLayout category={this.state.category}>
                <CourseSearchPane update={this.updateCourses} />
                <CourseListing courses={this.state.courses}/>
            </SubscriberLayout>
        )
    }

    updateCourses = (courses) => {
        this.setState({
            courses : courses
        })
    }
}

export default UserHome