import { useState } from "react";
import { CSSTransition } from "react-transition-group";
import styles from "src/components/AuthForm/AuthForm.module.css";
import React from "react";
import EtmInput from "src/components/common/EtmInput/EtmInput";

export default function AuthForm() {

    const [isRegistration, setIsRegistration] = useState<boolean>(false);

    return(
        <form className={styles.AuthForm}>
            <EtmInput value={"email"}/>
            <CSSTransition in={isRegistration} timeout={300}>
                <EtmInput value={"name"} />
            </CSSTransition>
            <EtmInput value={"password"}/>
            <div className={styles.Buttons}>
                <button>Sign In</button>
                <CSSTransition in={isRegistration} timeout={300}>
                    <button>Sign up</button>
                </CSSTransition>
            </div>
        </form>
    );
}