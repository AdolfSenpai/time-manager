import { CSSTransition } from "react-transition-group";
import descriptionTransitions from "src/components/TaskList/TaskRow/TaskDescription/DescriptionTransitions.module.css";
import styles from "src/components/TaskList/TaskRow/TaskRow.module.css";

export default function TaskDescription(props: {
    text: string | undefined,
    folded: boolean
}) {
    return(

        <CSSTransition
            in={!props.folded}
            timeout={200}
            classNames={{...descriptionTransitions}}
            unmountOnExit
        >
            <div className={styles.TaskDescription}>
                { props.text || <em>No description</em> }
            </div>
        </CSSTransition>
    );
}