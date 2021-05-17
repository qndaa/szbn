import React from "react";

class Login extends React.Component {

    render() {
        return (
            <div className={`container`}>
                <h1 className={`text text-dark font-weight-bold d-flex justify-content-center m-5`}>Login:</h1>

                <div className={`d-flex justify-content-center`}>
                    <form class="form-control-feedback w-50">
                        <div class="row d-flex justify-content-center mt-3">
                            <div class="col-8">
                                <div>
                                    <label for="email" class="text-dark">Email:</label>
                                    <input type="text" class="form-control" required="true" id="email"/>
                                    <div class="invalid-feedback">
                                        Correct: name@service.com
                                    </div>
                                </div>
                                <div>
                                    <label class="pt-3 text-dark" for="password">Password:</label>
                                    <input type="password" class="form-control" required="true" id="password"/>
                                    <div class="invalid-feedback">
                                        Minimum 8 characters.
                                    </div>
                                </div>

                                <button  type="submit" class="btn btn-dark mt-3 w-100">Submit</button>

                            </div>
                        </div>
                    </form>
                </div>
        </div>


        );
    }


}

export default Login;