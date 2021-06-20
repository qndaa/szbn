import React from "react";
import {Button, Col, Form} from "react-bootstrap";
import RangeSlider from 'react-bootstrap-range-slider'
import axios from "axios";

class CourseSearchPane extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            show : false,
            title : null,
            area : null,
            author : null,
            duration : null,
            price : null,
            grade : null,
            year : null,
            popularity : 'Choose...',
            level : 'Choose...'
        }
    }

    render() {
        return (
            <>
        {
            !this.state.show &&
            <Button className={'mt-2 mb-3 ml-2'} variant="primary" onClick={() => this.setState({show: true})}>
                Search
            </Button>
        }
        {
            this.state.show &&
            <Form>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridTitle">
                        <Form.Label>Title</Form.Label>
                        <Form.Control name={'title'} value={this.state.title} placeholder="Enter Title"
                                      onChange={this.handleChange}/>
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridArea">
                        <Form.Label>Area</Form.Label>
                        <Form.Control name={'area'} value={this.state.area} placeholder="Enter Area"
                                      onChange={this.handleChange}/>
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridAuthor">
                        <Form.Label>Author</Form.Label>
                        <Form.Control name={'author'} value={this.state.author} placeholder="Enter Author"
                                      onChange={this.handleChange}/>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridGrade">
                        <Form.Label>Grade</Form.Label>
                        <Form.Control name={'grade'} type={'range'} value={this.state.grade} min={'1'} max={'5'}
                                      step={'1'} onChange={this.handleChange}/>
                        {this.state.grade}
                    </Form.Group>

                    <Form.Group as={Col} controlId="formGridPrice">
                        <Form.Label>Price</Form.Label>
                        <Form.Control name={'price'} type={'range'} value={this.state.price} min={'0'} max={'25000'}
                                      onChange={this.handleChange}/>
                        ${this.state.price}
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridYear">
                        <Form.Label>Year of Creation</Form.Label>
                        <Form.Control name={'year'} type={'number'} value={this.state.year} min={'2000'}
                                      max={new Date().getFullYear()} onChange={this.handleChange}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="formGridPopularity">
                        <Form.Label>Popularity</Form.Label>
                        <Form.Control name={'popularity'} as="select" value={this.state.popularity}
                                      defaultValue="Choose..." onChange={this.handleChange}>
                            <option>Choose...</option>
                            <option>Not popular</option>
                            <option>Popular</option>
                            <option>Highly popular</option>
                            <option>Extremely popular</option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col} controlId="formGridLevel">
                        <Form.Label>Level</Form.Label>
                        <Form.Control name={'level'} as="select" value={this.state.level} defaultValue="Choose..."
                                      onChange={this.handleChange}>
                            <option>Choose...</option>
                            <option>Beginner</option>
                            <option>Intermediate</option>
                            <option>Advanced</option>
                        </Form.Control>
                    </Form.Group>
                </Form.Row>


                <Button className={'mt-2 mb-3'} variant="primary" onClick={this.search}>
                    Submit
                </Button>
                <Button className={'mt-2 mb-3 ml-2'} variant="secondary" onClick={() => this.setState({show: false})}>
                    Cancel
                </Button>
            </Form>
        }
            </>
        )
    }

    handleChange = (e) => {
        const name = e.target.name
        const value = e.target.value
        this.setState({
            [name] : value
        })
    }

    search = () => {
        axios
            .post('http://localhost:8080/courses/search', {
                'title' : this.state.title,
                'area' : this.state.area,
                'author': this.state.author,
                'grade' : this.state.grade,
                'price' : this.state.price,
                'year' : this.state.year,
                'level' : this.state.level === 'Choose...' ? null : this.state.level.toUpperCase(),
                'popularity' : this.state.popularity === 'Choose...' ? null : this.state.popularity.replace(' ', '_').toUpperCase()
            })
            .then(res => {
                this.props.update(res.data)
            })
    }

    handleExpand = () => {
        this.setState({
            title : null,
            area : null,
            author: null,
            grade : null,
            price : null,
            year : null,
            level : 'Choose...',
            popularity : 'Choose...'
        })
    }
    
}

export default CourseSearchPane
