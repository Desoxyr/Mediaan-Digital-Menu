import { Redirect } from "react-router-dom";

const Table = () => {
    //{/*window.location.pathname.split("/").pop()*/}
    sessionStorage.setItem("table", JSON.stringify(window.location.pathname.split("/").pop()));
    JSON.parse(sessionStorage.getItem("table"));
    <Redirect to="/" />
    //<QRCode value="http://localhost:3000/table/1" />
    //<QRCode value="http://localhost:3000/table/2" />
    //<QRCode value="http://localhost:3000/table/3" />
    //<QRCode value="http://localhost:3000/table/4" />
    return null;
}

export default Table;