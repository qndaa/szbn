import React from "react";
import AuthorLayout from "../layouts/authorLayout";
import axios from "axios";
import {Table} from "react-bootstrap";
import SubscriberListing from "../components/subscriberListing";

class AuthorReport extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            subs : []
        }
    }

    componentDidMount() {
        axios
            .get('http://localhost:8080/report/author-subscriber/' + localStorage.getItem('id'))
            .then(res => {
                this.setState({
                    subs : res.data
                })
            })
    }

    render() {
        return (
            <AuthorLayout>
                <h5 className={'mb-4'}>Users that have finished at least 3 of my courses</h5>
                <SubscriberListing data={this.state.subs}/>
            </AuthorLayout>
        );
    }

}

export default AuthorReport
