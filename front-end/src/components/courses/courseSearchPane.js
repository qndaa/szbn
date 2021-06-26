import React from "react";
import {Button, Col, Form} from "react-bootstrap";
import RangeSlider from 'react-bootstrap-range-slider'
import axios from "axios";
import {withRouter} from "react-router-dom";

class CourseSearchPane extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            show : false,
            title : null,
            area : null,
            author : null,
            duration : null,
            price : 15000,
            grade : null,
            year : null,
            popularity : 'Choose...',
            level : 'Choose...',
            areas : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/areas')
            .then(res => {
                this.setState({
                    areas: res.data
                })
            })
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
                        <Form.Control name={'area'} as="select" value={this.state.area}
                                      defaultValue="Choose..." onChange={this.handleChange}>
                            <option>Choose...</option>
                            {this.state.areas.map((a, ind) => <option key={ind}>{a.name}</option>)}
                        </Form.Control>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridPrice">
                        <Form.Label>Price (less than)</Form.Label>
                        <Form.Control name={'price'} type={'range'} value={this.state.price} min={'0'} max={'25000'}
                                      onChange={this.handleChange}/>
                        ${this.state.price}
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridYear">
                        <Form.Label>Year of Creation (after year)</Form.Label>
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

    search = async () => {
        if(this.state.title === '' || this.state.area === 'Choose...' || this.state.year == null || this.state.popularity === 'Choose...' || this.state.level === 'Choose...') {
            alert('Some fields were left empty')
            return
        }

        await axios
            .get('http://localhost:8080/users/subscriber/' + localStorage.getItem('id'))
            .then(res => {
                if(res.data) {
                    localStorage.setItem("id", null);
                    localStorage.setItem("role", null);
                    this.props.history.push('/login');
                }
            })

        await axios
            .post('http://localhost:8080/courses/search', {
                'userId' : localStorage.getItem('id'),
                'title' : this.state.title,
                'area' : this.state.area,
                'author': this.state.author,
                'grade' : this.state.grade,
                'price' : this.state.price,
                'year' : this.state.year + '-10-10',
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

export default withRouter(CourseSearchPane)
