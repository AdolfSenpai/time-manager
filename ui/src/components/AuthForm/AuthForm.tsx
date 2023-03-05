import { useState } from "react";
import { CSSTransition } from "react-transition-group";
import styles from "src/components/AuthForm/AuthForm.module.scss";
import React from "react";
import EtmButton from "src/components/common/EtmButton/EtmButton";
import EtmInput from "src/components/common/EtmInput/EtmInput";

export default function AuthForm() {

    const [isRegistration, setIsRegistration] = useState<boolean>(false);



    return(
        <form className={styles.AuthForm}>
            <EtmInput type="text" placeholder={"Email"}/>
            <CSSTransition in={isRegistration} timeout={300} unmountOnExit>
                <EtmInput type="text" placeholder="Name" />
            </CSSTransition>
            <EtmInput type="text" placeholder="Password" />
            <div className={styles.Buttons}>
                <CSSTransition in={!isRegistration} timeout={300} unmountOnExit>
                    <EtmButton>Sign In</EtmButton>
                </CSSTransition>
                <EtmButton color="blank">Sign Up</EtmButton>
            </div>
        </form>
    );
}