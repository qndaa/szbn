import React from "react";
import {Button, Card, Image, Modal, Table} from "react-bootstrap";
import course from "../../course.jpg";
import api from "../../api/ApiUrl";

class CourseCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal : false,
            addCourseModal: false
        }
    }

    render() {
        const {title, price, description, duration, courseAreas, year, levelOfCourse, preconditions, skills, popularity} = this.props.course
        const {courseId} = this.props.course
        return(
            <div>
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
                        <Button onClick={this.showModal}>See more</Button>
                </Card.Footer>
            </Card>

                <Modal show={this.state.showModal} size="lg" centered>
                    <Modal.Header>
                        <Image src={course} fluid/>
                    </Modal.Header>
                    <Modal.Body>
                        <h4 className={'pb-3'}>{title}</h4>
                        <Table striped bordered hover>
                            <tbody>
                            <tr>
                                <td>Description</td>
                                <td>{description}</td>
                            </tr>
                            <tr>
                                <td>Duration</td>
                                <td>{duration}h</td>
                            </tr>
                            <tr>
                                <td>Created in</td>
                                <td>{new Date(year).getFullYear()}</td>
                            </tr>
                            <tr>
                                <td>Level</td>
                                <td>{levelOfCourse.toLowerCase()}</td>
                            </tr>
                            <tr>
                                <td>Popularity</td>
                                <td>{popularity.replace('_', ' ').toLowerCase()}</td>
                            </tr>
                            <tr>
                                <td>Areas</td>
                                <td><ul className={'p-3'}>{courseAreas.map((a, i) => <li className={'mt-2 mb-2'} key={i}>{a.name}</li>)}</ul></td>
                            </tr>
                            <tr>
                                <td>Skills</td>
                                <td><ul className={'p-3'}>{skills.map((s, i) => <li className={'mt-2 mb-2'} key={i}>{s.name}</li>)}</ul></td>
                            </tr>
                            <tr>
                                <td>Precondition Courses</td>
                                <td><ol className={'p-3'}>{preconditions.map((c, i) => <li className={'mt-2 mb-2'} key={i}>{c.title}</li>)}</ol></td>
                            </tr>
                            </tbody>
                            <tr>
                                <td>Price</td>
                                <td>${price}</td>
                            </tr>
                        </Table>

                    </Modal.Body>
                    <Modal.Footer>
                        {this.renderDeleteButton(courseId)}
                        {this.renderBuyButton(courseId)}
                        <Button onClick={this.hideModal}>Close</Button>
                    </Modal.Footer>
                </Modal>


            </div>
        )
    }

    renderDeleteButton = (id) => {
        if (localStorage.getItem('role') === 'TEACHER') {
            return (<Button className={`btn btn-danger`} onClick={this.deleteCourse(id)}>Delete</Button>);
        }
    }

    deleteCourse = (id) => {
        return function () {
            console.log(id);
            api.delete('/courses/' + id).then(response => {
                alert("Course deleted!");
                this.props.history.push('/myCourses');
            });
        }
    }

    renderBuyButton = (id) => {
        if (localStorage.getItem('role') === 'SUBSCRIBER') {
            return (<Button className={`btn btn-success`} >Buy</Button>);
        }
    }

    showModal = () => {
        this.setState({
            showModal: true
        })
    }

    hideModal = () => {
        this.setState({
            showModal: false
        })
    }
}

export default CourseCard
