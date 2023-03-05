import { faLink } from "@fortawesome/free-solid-svg-icons";
import { faChevronRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { MouseEventHandler } from "react";
import styles from "src/components/TaskList/TaskRow/TaskRow.module.css";
import { Task } from "src/models/Task";

export default function TaskTitle(props: {
    folded: boolean,
    task: Task,
    toggleFolding: (taskId: string) => void,
}) {
    const clickChevron: MouseEventHandler = (event) => {
        event.preventDefault();
        props.toggleFolding(props.task.id);
    }

    return(
        <div className={styles.TaskTitle}>
            <div>
                <FontAwesomeIcon
                    icon={faChevronRight}
                    className={`
                        ${styles.Chevron}
                        ${(!props.folded && styles.ChevronUnfolded) || undefined}`
                    }
                    onClick={clickChevron}
                />
                <span>{props.task.name}</span>
            </div>
            {
                !!props.task.link
                && <a
                    className={styles.Link}
                    href={props.task.link}
                    target={"_blank"}
                    rel={"noopener noreferrer"}
                >
                    <FontAwesomeIcon icon={faLink} />
                </a>
            }
        </div>
    );
}