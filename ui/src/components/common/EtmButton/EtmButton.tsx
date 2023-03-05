import { ReactNode } from "react";
import { ButtonHTMLAttributes } from "react";
import styles from "src/components/common/EtmButton/EtmButton.module.scss";

const buttonStyles = {
    primary: styles.Primary,
    blank: styles.Blank,
    warn: styles.Warn,
};

export default function EtmButton({color = "primary", children, ...props}: {
    color?: keyof typeof buttonStyles,
    children?: ReactNode,
    props?: ButtonHTMLAttributes<HTMLButtonElement>,
}) {

    return(
        <button {...props} className={`${styles.EtmButton} ${buttonStyles[color]}`}>
            {children}
        </button>
    );
}