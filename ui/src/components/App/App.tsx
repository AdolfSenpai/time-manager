import { useState } from "react";
import React from "react";
import styles from "src/components/App/App.module.scss";
import Content from "src/components/App/Content/Content";
import NavBar from "src/components/NavBar/NavBar";
import { User } from "src/models/User";
import "src/styles/styles.scss";

function App() {

    const [user, setUser] = useState<User | null>(null);

    return (
        <div className={styles.App}>
            <NavBar user={user}/>
            <Content user={user}/>
        </div>
    );
}

export default App;
