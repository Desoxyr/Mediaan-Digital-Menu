import React, { useState, useEffect } from "react";
import Row from "react-bootstrap/row";
import Col from "react-bootstrap/col";
import QRCode from "qrcode.react";
import "./Tables.sass";
import axios from "axios";

const tableLink = "http://localhost:3000/table/";

const Tables = () => {
  const [tables, setTables] = useState([]);

  const OnRemove = (tableId) => {
    setTables(tables.filter((i) => i.tableId !== tableId));

    axios.delete("http://localhost:9191/table/delete" + "/" + tableId);
  };

  const fetchTables = async () => {
    const result = await axios.get("http://localhost:9191/table/all");
    return result.data;
  };

  const setTable = async (table) => {
    await axios.post("http://localhost:9191/table/create", {
      tableNumber: tables.length + 1,
    });
    fetchTables().then((r) => setTables(r));
  };

  useEffect(() => {
    axios
      .get("http://localhost:9191/table/all")
      .then((res) => {
        setTables(res.data);
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const test = () => {
    console.log(tables);
  };

  return (
    <div className="table-wrapper">
      <div className="container">
        <Row>
          <Col>
            <table className="table">
              <thead className="thead-dark">
                <tr>
                  <th scope="col">Table Number</th>
                  <th scope="col">QR Code</th>
                  <th scope="col"> Manage </th>
                </tr>
              </thead>
              <tbody>
                {tables.map((tables) => (
                  <tr>
                    <td>
                      <h2>
                        <b>{tables.tableId}</b>
                      </h2>
                    </td>
                    <td>
                      <QRCode value={tableLink + tables.tableId} />
                    </td>
                    <td>
                      <button
                        type="button"
                        className="btn btn-danger"
                        onClick={() => OnRemove(tables.tableId)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            <div className="btnContainer">
              <button
                type="button"
                className="btn btn-warning"
                onClick={setTable}
              >
                Add
              </button>
            </div>
          </Col>
        </Row>
      </div>
    </div>
  );
};

export default Tables;
