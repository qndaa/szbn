import React from "react";
import {Button, Card} from "react-bootstrap";
import course from "../../course.jpg";

class CourseCard extends React.Component {
    render() {
        const {title, price, description} = this.props.course
        return(
            <Card>
                <Card.Img variant="top" src={course}/>
                <Card.Body>
                    <Card.Title>{title}</Card.Title>
                    <Card.Text>
                        {description}
                    </Card.Text>
                    <Card.Text>
                        <small className="text-muted">Price: {price}</small>
                    </Card.Text>
                </Card.Body>
                <Card.Footer>
                        <Button>See more</Button>
                </Card.Footer>
            </Card>
        )
    }
}

export default CourseCard