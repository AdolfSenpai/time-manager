import React from "react";
import { InputHTMLAttributes } from "react";
import styles from "src/components/common/EtmInput/EtmInput.module.scss";

export default function EtmInput(props: InputHTMLAttributes<HTMLInputElement>) {

    return(
        <div className={styles.EtmInput}>
            <input {...props}/>
        </div>
    );
}