import React from "react";
import api from "../api/ApiUrl";
import CourseListing from "./courses/courseListing";
import {Modal, ModalBody} from "react-bootstrap";
import ModalHeader from "react-bootstrap/ModalHeader";
import Course from "../model/Course";

class MyCourses extends React.Component {

    constructor(props) {
        super(props);
        this.state = {courses : [], addCourseModal: false, areas: [], skills:[], course: new Course()};
    }



    componentDidMount = () => {

        if (localStorage.getItem('role') === 'TEACHER') {
            api.get('/courses/getCoursesByTeacher/' + localStorage.getItem('id')).then((response) => {
                for (let item in response.data) {
                    response.data[item] = {...response.data[item], selected:false}
                }
                this.setState({courses: response.data});
            });

            api.get('/areas/all').then(response => {
                for (let i in response.data) {
                    response.data[i] = {...response.data[i], selected: false};
                }
               this.setState({areas: response.data});
            });

            api.get('/skills/all').then(response => {
                for (let i in response.data) {
                    response.data[i] = {...response.data[i], selected: false};
                }
                this.setState({skills: response.data});
            });



        } else {
            this.props.history.push('/login');
        }

    }

    renderCourses = () => {
        if (this.state.courses !== null) {
            return (<CourseListing courses={this.state.courses}/>);
        }
    }

    renderAreas = () => {
        return this.state.areas.map(item => {
                return (
                    <div className={`col-4`}>
                        <input className="form-check-input" type="checkbox" id={item.id} value={item.selected} onChange={this.onChangeArea(item)}/>
                        <label className="form-check-label" htmlFor={item.id}>{item.name}</label>
                    </div>

                );
            });
    }

    onChangeArea = (item) => {
        return function () {
            item.selected = !item.selected;
            console.log(item);
        }
    }

    renderSkills = () => {
        return this.state.skills.map(item => {
            return (
               <div className={`col-4`}>
                   <input className="form-check-input" type="checkbox" id={item.id} value={item.selected} onChange={this.onChangeSkill(item)}/>
                   <label className="form-check-label" htmlFor={item.id}>{item.name}</label>
               </div>
           );
        });
    }

    onChangeSkill = (item) => {
        return function () {
            item.selected = !item.selected;
        }
    }

    renderPrecondition = () => {
        return this.state.courses.map(item => {
            return (
                <div className={`col-12`}>
                    <input className="form-check-input" type="checkbox" id={item.id} value={item.selected} onChange={this.onChangePrecondition(item)}/>
                    <label className="form-check-label" htmlFor={item.id}>{item.title}</label>
                </div>
            );
        });
    }

    onChangePrecondition = (item) => {
        return function () {
            item.selected = !item.selected;
            console.log(item);
        }
    }

    onChangeTitle = (event) => {
        this.setState({course: {...this.state.course, title: event.target.value}});
        console.log(this.state.course);
    }

    onChangeDescription = (event) => {
        this.setState({course: {...this.state.course, description: event.target.value}});
    }

    onChangeDuration = (event) => {
        this.setState({course: {...this.state.course, duration: event.target.value}})
    }

    onChangePrice = (event) => {
        this.setState({course: {...this.state.course, price: event.target.value}})
    }

    onChangeYear = (event) => {
        this.setState({course: {...this.state.course, year: event.target.value}});
    }

    onChangeLevel = (event) => {
        this.setState({course: {...this.state.course, levelOfCourse: event.target.value}});
    }


    render() {
        return (
            <div className={`container`}>
                <div className={`row mt-5`}>
                    <div className={`col-4`}>

                    </div>
                    <div className={`col-4 d-flex justify-content-center`}>
                        <h1 className={`text-primary`}>My courses:</h1>
                    </div>
                    <div className={`col-4 d-flex justify-content-end`}>
                        <button className={`btn btn-success`} onClick={this.showModal} >Add course</button>
                    </div>

                </div>


                <div className={`mt-5`}>
                    {this.renderCourses()}
                </div>


                <Modal show={this.state.addCourseModal} size={`lg`} centered={true}>
                    <ModalHeader className={`d-flex justify-content-center`}>
                        <div className={`d-flex justify-content-center`}>
                            <h2 className={`text-primary`}>New course:</h2>
                        </div>
                    </ModalHeader>
                    <ModalBody>

                        <div>
                            <form className="form-control-feedback ml-5 mr-5">

                                <div className={`mt-3`}>
                                    <label htmlFor="title" className="text-dark">Title:</label>
                                    <input type="text" id="title" className={`form-control`} value={this.state.course.title} onChange={this.onChangeTitle}/>
                                    <div className="invalid-feedback">
                                        Input title!
                                    </div>
                                </div>
                                <div className={`mt-3`}>
                                    <label htmlFor="description" className="text-dark">Description:</label>
                                    <input type="text" id="description" className={`form-control`} value={this.state.course.description} onChange={this.onChangeDescription}/>
                                    <div className="invalid-feedback">
                                        Input description!
                                    </div>


                                </div>

                                <div className={`row mt-3`}>
                                    <div className={`col-6`}>
                                        <label htmlFor="duration" className="text-dark">Duration(h):</label>
                                        <input type="number" id="duration" className={`form-control`} value={this.state.course.duration} onChange={this.onChangeDuration}/>
                                        <div className="invalid-feedback">
                                            Input duration!
                                        </div>
                                    </div>
                                    <div className={`col-6`}>
                                        <label htmlFor="price" className="text-dark">Price:</label>
                                        <input type="number" id="price" className={`form-control`} value={this.state.course.price} onChange={this.onChangePrice}/>
                                        <div className="invalid-feedback">
                                            Input price!
                                        </div>
                                    </div>

                                </div>

                                <div className={`row mt-3`}>
                                    <div className={`col-6`}>
                                        <label htmlFor="year" className="text-dark">Year:</label>
                                        <input type="date" id="type" className={`form-control`} value={this.state.course.year} onChange={this.onChangeYear}/>
                                        <div className="invalid-feedback">
                                            Input year!
                                        </div>
                                    </div>
                                    <div className={`col-6`}>
                                        <label htmlFor="level" className="text-dark">Level of course:</label>
                                        <select className="form-control w-100" id="level" value={this.state.course.levelOfCourse} onChange={this.onChangeLevel}>
                                            <option value={`Choose...`}>Choose...</option>
                                            <option value={`BEGINNER`}>BEGINNER</option>
                                            <option value={`ADVANCED`}>ADVANCED</option>
                                            <option value={`INTERMEDIATE`}>INTERMEDIATE</option>
                                        </select>
                                        <div className="invalid-feedback">
                                            Input description!
                                        </div>
                                    </div>

                                </div>

                                <div className={`mt-3`}>
                                    <div>
                                        <label htmlFor="areas" className="text-dark">Areas:</label>
                                    </div>
                                    <div className="form-check form-check-inline row">
                                        {this.renderAreas()}
                                    </div>
                                </div>

                                <div className={`mt-3`}>
                                    <div>
                                        <label htmlFor="skills" className="text-dark">Skills:</label>
                                    </div>
                                    <div className="form-check form-check-inline row">
                                        {this.renderSkills()}
                                    </div>
                                </div>

                                <div className={`mt-3`}>
                                    <div>
                                        <label htmlFor="precondition" className="text-dark">Preconditions:</label>
                                    </div>
                                    <div className="form-check form-check-inline row">
                                        {this.renderPrecondition()}
                                    </div>
                                </div>

                                <div className={`mt-3`}>
                                    <button className={`btn btn-success`} onClick={this.createCourse}>Create course</button>
                                </div>
                            </form>
                        </div>
                    </ModalBody>
                </Modal>

            </div>
        )
    }

    showModal = () => {
        this.setState({addCourseModal: true});
    }

    createCourse = async (event) => {
        event.preventDefault();
        if (this.state.course.levelOfCourse !== 'Choose...') {
            for (let item of this.state.areas) {
                if (item.selected) {
                    let arr = this.state.course.courseAreas;
                    arr.push(item);
                    this.setState({course : {...this.state.course, courseAreas: arr}});
                }
            }

            for (let item of this.state.skills) {
                if (item.selected) {
                    let arr = this.state.course.skills;
                    arr.push(item);
                    this.setState({course: {...this.state.course, skills: arr}});
                }
            }

            for (let item of this.state.courses) {
                if (item.selected) {
                    let arr = this.state.course.preconditions;
                    arr.push(item);
                    this.setState({course: {...this.state.course, preconditions: arr}});
                }
            }
            const a = this.state.course.year  + 'T16:37:23';
            console.log(a);
            let course = this.state.course;
            course.year = a;
            console.log(course);
            this.setState({course: course});

            await api.post("/courses/create/" + localStorage.getItem('id'), this.state.course).then(response => {
                console.log(response);
                alert("Success");
                this.props.history.push('/myCourses');
                this.setState({course: new Course()});
                this.componentDidMount();
                this.setState({addCourseModal: false});
            })
        }

    }
}

export default MyCourses;
