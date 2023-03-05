import axios from "axios";

export default class AuthService {

    async signUp(email: string, login: string, password: string) {

        let response = await axios.post(
            "/api/register",
            {
                email,
                password,
                name: login
            }
        );
    }

    async signIn(email: string, password: string) {

        let response = await axios.post(
            "/api/register",
            {
                email,
                password,
            }
        );
    }
}