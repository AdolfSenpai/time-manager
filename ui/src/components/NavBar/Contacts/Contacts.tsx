import { faG } from "@fortawesome/free-solid-svg-icons";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import styles from "src/components/NavBar/Contacts/Contacts.module.scss";

export default function Contacts() {

    return(
        <div className={styles.Contacts}>
            <a
                className={styles.ContactItem}
                href={"https://t.me/CursedUsername"}
                target={"_blank"}
                rel={"noopener noreferrer"}
            >
                <FontAwesomeIcon icon={faPaperPlane}/>
            </a>
            <a
                className={styles.ContactItem}
                href={"mailto:edsim97@gmail.com"}
            >
                <FontAwesomeIcon icon={faG}/>
            </a>
        </div>
    );
}