import React from "react";
import AdminLayout from "../layouts/adminLayout";
import {Row, Form} from "react-bootstrap";
import axios from "axios";
import SubscriberListing from "../components/subscriberListing";

class AdminReport extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            areas : [],
            area : 'Choose...',
            subs : []
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
        const areas = this.state.areas.map((a, ind) => <option key={ind}>{a.name}</option>)
        return (
            <AdminLayout>
                <Row>
                    <Form.Group>
                        <Form.Label>Course Area</Form.Label>
                        <Form.Control as="select" name={'area'} value={this.state.area} onChange={this.handleChange}>
                            <option disabled>Choose...</option>
                            {areas}
                        </Form.Control>
                    </Form.Group>
                </Row>
                {
                    this.state.area !== 'Choose...' &&
                    <Row className={'mt-3'}>
                        <h5>Here are the subscribers that have finished ten or more advanced courses in
                            the {this.state.area} area</h5>
                        <SubscriberListing data={this.state.subs}/>
                    </Row>
                }
            </AdminLayout>
        );
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })

        axios
            .get('http://localhost:8080/report/area-subscriber/' + e.target.value)
            .then(res => {
                this.setState({
                    subs : res.data
                })
            })
    }

}

export default AdminReport
