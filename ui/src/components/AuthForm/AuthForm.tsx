import { useState } from "react";
import { CSSTransition } from "react-transition-group";
import styles from "src/components/AuthForm/AuthForm.module.scss";
import React from "react";
import EtmButton from "src/components/common/EtmButton/EtmButton";
import EtmInput from "src/components/common/EtmInput/EtmInput";
import { faEye } from "@fortawesome/free-solid-svg-icons";
import { faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export default function AuthForm() {

    const [isRegistration, setIsRegistration] = useState<boolean>(false);
    const [isPasswordShown, setIsPasswordShown] = useState<boolean>(false);
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [name, setName] = useState<string>("");

    return(
        <form className={styles.AuthForm}>
            <EtmInput type="text" placeholder={"Email"}/>
            <CSSTransition in={isRegistration} timeout={300} unmountOnExit>
                <EtmInput type="text" placeholder="Name" />
            </CSSTransition>
            <div>
                <EtmInput type="password" placeholder="Password" />
                {
                    ( isPasswordShown && <FontAwesomeIcon icon={faEyeSlash}/> )
                    || <FontAwesomeIcon icon={faEye}/>
                }
            </div>
            <div className={styles.Buttons}>
                <CSSTransition in={!isRegistration} timeout={300} unmountOnExit>
                    <EtmButton>Sign In</EtmButton>
                </CSSTransition>
                <EtmButton color="blank">Sign Up</EtmButton>
            </div>
        </form>
    );
}