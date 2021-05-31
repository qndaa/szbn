import React from "react";
import UserModel from "../model/UserModel"
import UserModelValidation from "../model/UserModelValidation";
import axios from "axios";
import apiUrl from '../api/ApiUrl'

class Registration extends React.Component {


    constructor(props) {
        super(props);
        this.state = {user: new UserModel(), userValidation: new UserModelValidation()};
    }

    onChangeNameInput = (event) => {
        this.setState({user : {...(this.state.user), name: event.target.value} });
        this.setState({userValidation: new UserModelValidation()});
    }

    onChangeSurnameInput = (event) => {
        this.setState({user : {...(this.state.user), surname: event.target.value} });
        this.setState({userValidation: new UserModelValidation()});
    }

    onChangeEmailInput = (event) => {
        this.setState({user : {...(this.state.user), username: event.target.value} });
        this.setState({userValidation: new UserModelValidation()});
    }


    onChangePasswordInput = (event) => {
        this.setState({user : {...(this.state.user), password: event.target.value} });
        this.setState({userValidation: new UserModelValidation()});
    }


    onChangeConfirmPasswordInput = (event) => {
        this.setState({user : {...(this.state.user), confirmPassword: event.target.value} });
        this.setState({userValidation: new UserModelValidation()});
    }

    onSubmitClick = async (event) => {
        event.preventDefault();
        if (await this.isFormValid()) {

            const response = await apiUrl.post('/users/registration', this.state.user);
            if (response.status === 200) {
                this.setState({user: new UserModel(), userValidation: new UserModelValidation()});
                alert('Registration success!');
            } else {
                alert("Wrong data!");
            }
        }
    }

    isFormValid = async () => {
        const validName = await this.isValidName(this.state.user.name);
        const validSurname = await this.isValidSurname(this.state.user.surname);
        const validEmail = await this.isValidEmail(this.state.user.username);
        const validPassword = await this.isValidPassword(this.state.user.password);
        const validConfirmPassword = await this.isValidPassword(this.state.user.confirmPassword);
        return validName && validSurname && validEmail && validPassword && validConfirmPassword;
    }

    isValidName = (name) => {
        if (name.length > 4) {
            this.setState({userValidation : {...this.state.userValidation, validName: 'is-valid'}});
            return true;
        } else {
            this.setState({userValidation : {...this.state.userValidation, validName: 'is-invalid'}});
            return false;
        }
    }

    isValidSurname = (surname) => {
        if (surname.length > 4) {
            this.setState({userValidation : {...this.state.userValidation, validSurname: 'is-valid'}});
            return true;
        } else {
            this.setState({userValidation : {...this.state.userValidation, validSurname: 'is-invalid'}});
            return false;
        }
    }

    isValidEmail = (email) => {
        if (email.length > 4) {
            this.setState({userValidation : {...this.state.userValidation, validEmail: 'is-valid'}});
            return true;
        } else {
            this.setState({userValidation : {...this.state.userValidation, validEmail: 'is-invalid'}});
            return false;
        }
    }

    isValidPassword = (password) => {
        if (password.length > 4) {
            this.setState({userValidation : {...this.state.userValidation, validPassword: 'is-valid'}});
            return true;
        } else {
            this.setState({userValidation : {...this.state.userValidation, validPassword: 'is-invalid'}});
            return false;
        }
    }

    isValidConfirmPassword = (pass1, pass2) => {
        if (pass1 === pass2) {
            this.setState({userValidation : {...this.state.userValidation, validConfirmPassword: 'is-valid'}});
            return true;
        } else {
            this.setState({userValidation : {...this.state.userValidation, validConfirmPassword: 'is-invalid'}});
            return false;
        }
    }


    render() {
        return (
            <div className={`container`}>
                <h1 className={`text text-dark font-weight-bold d-flex justify-content-center m-5`}>Registration:</h1>

                <div className={`d-flex justify-content-center pt-4`}>
                    <form class="form-control-feedback w-50">
                        <div className={`row`}>

                            <div className={`col-6`}>
                                <label htmlFor="firstName" className="text-dark">First name:</label>
                                <input type="text" id="firstName" className={`form-control ` + this.state.userValidation.validName} onChange={this.onChangeNameInput} value={this.state.user.name}/>
                                <div className="invalid-feedback">
                                    Input name!
                                </div>
                            </div>
                            <div className={`col-6`}>
                                <label htmlFor="lastName" className="text-dark">Last name:</label>
                                <input type="text" id="lastName" className={`form-control ` + this.state.userValidation.validSurname} onChange={this.onChangeSurnameInput} value={this.state.user.surname}/>
                                <div className="invalid-feedback">
                                    Input surname!
                                </div>
                            </div>
                        </div>


                        <div className={`row mt-3`}>
                            <div className={`col-4`}>
                                <label htmlFor="email" className="text-dark">Username:</label>
                                <input type="text" id="email" className={`form-control ` + this.state.userValidation.validEmail} onChange={this.onChangeEmailInput} value={this.state.user.username}/>
                                <div className="invalid-feedback">
                                    Wrong username!
                                </div>

                            </div>

                            <div className={`col-4`}>
                                <label htmlFor="password" className="text-dark">Password:</label>
                                <input type="password" id="password" className={`form-control ` + this.state.userValidation.validPassword} onChange={this.onChangePasswordInput} value={this.state.user.password}/>
                                <div className="invalid-feedback">
                                    Wrong password!
                                </div>
                            </div>

                            <div className={`col-4`}>
                                <label for="confirmPassword" class="text-dark" >Confirm password:</label>
                                <input type="password" id="confirmPassword" className={`form-control ` + this.state.userValidation.validConfirmPassword} onChange={this.onChangeConfirmPasswordInput} value={this.state.user.confirmPassword}/>
                                <div class="invalid-feedback">
                                    Password is not correct!
                                </div>

                            </div>
                        </div>

                        <div className={`row mt-5`}>
                            <button className={`btn btn-dark ml-3 mr-3 w-100`} onClick={this.onSubmitClick}>Submit</button>
                        </div>

                    </form>
                </div>



            </div>
        );
    }

}

export default Registration;