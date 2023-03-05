import React from "react";
import ClockImage from "src/assets/images/icon48.png";
import styles from "src/components/NavBar/TitleCard/TitleCard.module.css";

export default function TitleCard() {

    return (
        <div className={styles.TitleCard}>
            <img src={ClockImage} alt="clock-icon"/>
            <h1 className={styles.Title}>Ed Time Manager</h1>
        </div>
    );
}