import React from "react";
import CourseCard from "./courseCard";
import {CardDeck, Card, CardGroup, Row, CardColumns} from "react-bootstrap";
import course from "./../course.jpg"

class CourseListing extends React.Component {
    render() {
        const courses = this.props.courses.map((c, idx) => <CourseCard course={c} key={idx}/>)

        return (
                <CardColumns>
                    {courses}
                </CardColumns>
        )
    }
}

export default CourseListing