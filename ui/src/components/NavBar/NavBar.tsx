import React from "react";
import Contacts from "src/components/NavBar/Contacts/Contacts";
import styles from "src/components/NavBar/NavBar.module.css";
import TitleCard from "src/components/NavBar/TitleCard/TitleCard";

export default function NavBar() {

    return (
        <nav className={styles.NavBar}>
            <TitleCard />
            <div>
                TODO: CURRENT TASK, CREATE LIST, STATISTICS
            </div>
            <Contacts />
        </nav>
    );
}