import React from "react";
import {Button, Card, Image, Modal, Table} from "react-bootstrap";
import course from "../../course.jpg";
import axios from "axios";

class CourseCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal : false,
            allPreconditions : false,
            preconditionsFullList: []
        }
    }

    componentDidMount() {
        const {courseId} = this.props.course

        axios
            .get('http://localhost:8080/courses/preconditions/' + courseId)
            .then(res => {
                this.setState({
                    preconditionsFullList : res.data
                })
            })
    }

    render() {
        const {title, price, description, duration, courseAreas, year, levelOfCourse, preconditions, skills, popularity} = this.props.course

        return(
            <>
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
                            <td>Precondition Courses<br/><button className="btn btn-primary" onClick={this.allPreconditions}>See all</button></td>
                            <td>
                                {!this.state.allPreconditions && <ol className={'p-3'}>{preconditions.map((c, i) => <li className={'mt-2 mb-2'}
                                                                                        key={i}>{c.title}</li>)}</ol>}
                                {this.state.allPreconditions && <ol className={'p-3'}>{this.state.preconditionsFullList.map((c, i) => <li className={'mt-2 mb-2'}
                                                                                        key={i}>{c.title}</li>)}</ol>}
                            </td>
                        </tr>
                        </tbody>
                        <tr>
                            <td>Price</td>
                            <td>${price}</td>
                        </tr>
                    </Table>

                </Modal.Body>
                <Modal.Footer>
                    {this.props.perspective === 'allCourses' && <Button variant={'success'}>Buy</Button>}
                    {this.props.perspective === 'enrolledCourses' && <Button variant={'success'}>Finish</Button>}
                    {this.props.perspective === 'enrolledCourses' && <Button variant={'secondary'}>Quit</Button>}
                    <Button onClick={this.hideModal}>Close</Button>
                </Modal.Footer>
            </Modal>
            </>
        )
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

    allPreconditions = () => {
        this.setState({
            allPreconditions: !this.state.allPreconditions
        })
    }
}

export default CourseCard
