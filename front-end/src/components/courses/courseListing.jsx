import React from "react";
import CourseCard from "./courseCard";
import {CardDeck, Card, CardGroup, Row, CardColumns} from "react-bootstrap";
import course from "../../course.jpg"
import axios from "axios";

class CourseListing extends React.Component {
    render() {
        const courses = this.props.courses.map((c, idx) => {
            if (c.deleted === false) {
                return (<CourseCard course={c} key={idx} perspective={this.props.perspective} quit={this.props.quit}/>);
            }
        })
        //const courses = this.props.courses.map((c, idx) => <CourseCard course={c} key={idx} perspective={this.props.perspective} quit={this.props.quit}/>)

        return (
                <CardColumns>
                    {courses}
                </CardColumns>
        )
    }
}

export default CourseListing
