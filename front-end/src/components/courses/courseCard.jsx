import React from "react";
import {Button, Card, Image, Modal, Table} from "react-bootstrap";
import course from "../../course.jpg";
import api from "../../api/ApiUrl";
import axios from "axios";
import SubscriberListing from "../subscriberListing";
import {withRouter} from "react-router-dom";

class CourseCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showModal : false,
            addCourseModal: false,
            showSubsModal: false,
            allPreconditions : false,
            courseSubs : [],
            teacher: {
                name: '',
                surname: '',
                username: ''
            },
            preconditionsFullList: [],
            warning: 'NO',
            discountForCategory : 0
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

        axios
            .get('http://localhost:8080/courses/teacher/' + courseId)
            .then(res => {
                this.setState({
                    teacher : res.data
                })
            });


        if(localStorage.getItem('role') === 'TEACHER') {
            axios
                .get('http://localhost:8080/report/course-subscriber/' + courseId)
                .then(res => {
                    this.setState({
                        courseSubs : res.data
                    })
                })
        }
    }

    renderSpecialDiscount = () => {
        if (this.props.course.discountByEnrollment !== 0) {
            return (<small className="ml-4 text-danger">    Special discount: {this.props.course.discountByEnrollment} %</small>);
        }
    }

    renderTrSpecialDiscount = () => {
        if (this.props.course.discountByEnrollment !== 0) {
            return (
                <tr>
                    <td>Course special discount</td>
                    <td>{this.props.course.discountByEnrollment} %</td>
                </tr>
            );
        }
    }

    renderDiscountForCategory = () => {
        return (
            <tr>
                <td>Discount for category</td>
                <td>{this.state.discountForCategory} %</td>
            </tr>
        );
    }

    renderNewPrice = () => {
        if (this.state.discountForCategory !== 0 || this.props.course.discountByEnrollment !== 0) {
            let newPrice = this.props.course.price;
            let dis1 = this.state.discountForCategory / 100;
            let dis2 = this.props.course.discountByEnrollment / 100;
            newPrice = newPrice - (newPrice * dis1);
            console.log(newPrice)
            newPrice = newPrice - (newPrice * dis2);
            console.log(newPrice)

            return (
                <tr>
                    <td>Price with discount</td>
                    <td className={`text-danger`}>${newPrice}</td>
                </tr>
            );
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
                        {this.renderSpecialDiscount()}

                    </Card.Text>


                </Card.Body>
                <Card.Footer>
                        <Button onClick={this.showModal}>See more</Button>
                        {this.props.perspective === 'author' && <Button className={'btn btn-success ml-2'} onClick={this.showSubsModal}>See subscribers</Button>}
                </Card.Footer>
            </Card>

            {/*    <Modal show={this.state.showModal} size="lg" centered>*/}
            {/*        <Modal.Header>*/}
            {/*            <Image src={course} fluid/>*/}
            {/*        </Modal.Header>*/}
            {/*        <Modal.Body>*/}
            {/*            <h4 className={'pb-3'}>{title}</h4>*/}
            {/*            <Table striped bordered hover>*/}
            {/*                <tbody>*/}
            {/*                <tr>*/}
            {/*                    <td>Description</td>*/}
            {/*                    <td>{description}</td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Duration</td>*/}
            {/*                    <td>{duration}h</td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Created in</td>*/}
            {/*                    <td>{new Date(year).getFullYear()}</td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Level</td>*/}
            {/*                    <td>{levelOfCourse.toLowerCase()}</td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Popularity</td>*/}
            {/*                    <td>{popularity.replace('_', ' ').toLowerCase()}</td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Areas</td>*/}
            {/*                    <td><ul className={'p-3'}>{courseAreas.map((a, i) => <li className={'mt-2 mb-2'} key={i}>{a.name}</li>)}</ul></td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Skills</td>*/}
            {/*                    <td><ul className={'p-3'}>{skills.map((s, i) => <li className={'mt-2 mb-2'} key={i}>{s.name}</li>)}</ul></td>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>Precondition Courses</td>*/}
            {/*                    <td><ol className={'p-3'}>{preconditions.map((c, i) => <li className={'mt-2 mb-2'} key={i}>{c.title}</li>)}</ol></td>*/}
            {/*                </tr>*/}
            {/*                </tbody>*/}
            {/*                <tr>*/}
            {/*                    <td>Price</td>*/}
            {/*                    <td>${price}</td>*/}
            {/*                </tr>*/}
            {/*            </Table>*/}

            {/*        </Modal.Body>*/}
            {/*        <Modal.Footer>*/}
            {/*            {this.renderDeleteButton(courseId)}*/}
            {/*            {this.renderBuyButton(courseId)}*/}
            {/*            <Button onClick={this.hideModal}>Close</Button>*/}
            {/*        </Modal.Footer>*/}
            {/*    </Modal>*/}


            {/*</div>*/}
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
                            <td>Author</td>
                            <td>{this.state.teacher.name + ' ' + this.state.teacher.surname + ' (' + this.state.teacher.username + ')'}</td>
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

                        {this.renderTrSpecialDiscount()}

                        {this.renderDiscountForCategory ()}

                        {this.renderNewPrice()}
                    </Table>

                </Modal.Body>
                <Modal.Footer>
                    {this.renderWarning()}
                    {this.renderDeleteButton(courseId)}
                    {this.props.perspective === 'allCourses' && this.state.warning === 'NO' && <Button variant={'success'} onClick={this.buyCourse}>Buy</Button>}

                    {this.props.perspective === 'enrolledCourses' && <Button variant={'success'}>Finish</Button>}
                    {this.props.perspective === 'enrolledCourses' && <Button variant={'secondary'} onClick={this.quit}>Quit</Button>}
                    <Button onClick={this.hideModal}>Close</Button>
                </Modal.Footer>
            </Modal>

            <Modal show={this.state.showSubsModal} size="lg" centered>
                <Modal.Header>
                    Subscribers
                </Modal.Header>
                <Modal.Body>
                    <SubscriberListing data={this.state.courseSubs}/>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.hideSubsModal}>Close</Button>
                </Modal.Footer>
            </Modal>

            </div>
        )
    }

    buyCourse = () => {
        api.get('/courses/buy/'+ localStorage.getItem('id') + "/" +  this.props.course.courseId).then(response => {
            alert("Success!");
            this.props.history.push('/enrolled');
        })
    }

    renderDeleteButton = (id) => {
        if (localStorage.getItem('role') === 'TEACHER' || localStorage.getItem('role') === 'ADMINISTRATOR') {
            return (<Button className={`btn btn-danger`} onClick={this.deleteCourse(id)}>Delete</Button>);
        }
    }

    renderWarning = () => {
        console.log(this.state.warning)
        if (this.state.warning === 'YES') {
            return (<p className={`text-danger`}>NO FINISHED ALL PRECONDITION</p>);
        }
    }
    deleteCourse = (id) => {
        return () => {
            console.log(id);
            api.delete('/courses/' + id).then(response => {
                alert("Course deleted!");
                //this.props.history.push('/myCourses');

                // this.props.history.push('/myCourses');
                // this.setState({
                //     showModal : false
                // })
                // this.componentDidMount()
                window.location.reload()
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

        api.get("/courses/hasPrecondition/" + localStorage.getItem('id') + "/" +  this.props.course.courseId).then(response => {
            console.log(response);
            if (response.data === 'NO_PRECONDITION') {
                this.setState({warning: "YES"});
            }
        })

        api.get('/users/discount/'+ localStorage.getItem('id') + "/" +  this.props.course.courseId).then(response => {
            console.log(response);
            this.setState({discountForCategory : response.data});
        })


    }

    hideModal = () => {
        this.setState({
            showModal: false
        })
        window.location.reload()
    }

    showSubsModal = () => {
        this.setState({
            showSubsModal: true
        })
    }

    hideSubsModal = () => {
        this.setState({
            showSubsModal: false
        })
    }

    allPreconditions = () => {
        this.setState({
            allPreconditions: !this.state.allPreconditions
        })
    }

    quit = () => {
        const {courseId} = this.props.course
        this.props.quit(courseId)
        this.hideModal()
    }
}

export default withRouter(CourseCard)
