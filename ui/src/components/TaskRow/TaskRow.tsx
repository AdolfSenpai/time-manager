import TaskDescription from "src/components/TaskRow/TaskDescription/TaskDescription";
import styles from "src/components/TaskRow/TaskRow.module.css";
import TaskTitle from "src/components/TaskRow/TaskTitle/TaskTitle";
import { Task } from "src/models/Task";

export default function TaskRow(props: {
    task: Task,
    folded: boolean,
    toggleFolding: (taskId: string) => void,
}) {

    return (
        <div className={styles.Task}>
            <TaskTitle folded={props.folded} task={props.task} toggleFolding={props.toggleFolding} />
            <TaskDescription folded={props.folded} text={props.task.description} />
        </div>
    );
}