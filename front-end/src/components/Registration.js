import React from "react";

class Registration extends React.Component {

    render() {
        return (
            <div className={`container`}>
                <h1 className={`text text-dark font-weight-bold d-flex justify-content-center m-5`}>Registration:</h1>

                <div className={`d-flex justify-content-center pt-4`}>
                    <form class="form-control-feedback w-50">
                        <div className={`row`}>

                            <div className={`col-6`}>
                                <label htmlFor="firstName" className="text-dark">First name:</label>
                                <input type="text" id="firstName" className="form-control"/>
                                <div className="invalid-feedback">
                                    Input name!
                                </div>
                            </div>
                            <div className={`col-6`}>
                                <label htmlFor="lastName" className="text-dark">Last name:</label>
                                <input type="text" id="lastName" className="form-control"/>
                                <div className="invalid-feedback">
                                    Input surname!
                                </div>
                            </div>
                        </div>


                        <div className={`row mt-3`}>
                            <div className={`col-4`}>
                                <label htmlFor="email" className="text-dark">Email:</label>
                                <input type="text" id="email" className="form-control"/>
                                <div className="invalid-feedback">
                                    Wrong email!
                                </div>

                            </div>

                            <div className={`col-4`}>
                                <label htmlFor="password" className="text-dark">Password:</label>
                                <input type="password" id="password" className="form-control"/>
                                <div className="invalid-feedback">
                                    Wrong password!
                                </div>
                            </div>

                            <div className={`col-4`}>
                                <label for="confirmPassword" class="text-dark" >Confirm password:</label>
                                <input type="password" id="confirmPassword" class="form-control"/>
                                <div class="invalid-feedback">
                                    Password is not correct!
                                </div>

                            </div>
                        </div>

                        <div className={`row mt-5`}>
                            <button className={`btn btn-dark ml-3 mr-3 w-100`}>Submit</button>
                        </div>

                    </form>
                </div>



            </div>
        );
    }

}

export default Registration;