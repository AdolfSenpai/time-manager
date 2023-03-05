import { ReactNode } from "react";
import { ButtonHTMLAttributes } from "react";
import styles from "src/components/common/EtmButton/EtmButton.module.scss";

export default function EtmButton({children, ...props}: {
    children?: ReactNode,
    props?: ButtonHTMLAttributes<HTMLButtonElement>,
}) {

    return(
        <button {...props} className={styles.EtmButton}>
            {children}
        </button>
    );
}