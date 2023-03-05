import EtmInput from "src/components/common/EtmInput/EtmInput";

export default function UserCard() {

    return(
        <div>
            <EtmInput value={"name"}/>
            <EtmInput value={"pass"}/>
        </div>
    );
}