import * as React from "react";
import ChoreRow from "./ChoreRow";
import * as axios from 'axios';
import { Table } from "react-bootstrap"


interface IChoreTableProps {
}

interface IChoreTableState {
    choresList?: String[],
    people?: String[][]
}

export default class ChoreTable extends React.Component<IChoreTableProps, IChoreTableState> {
    constructor(props) {
        super(props);
        this.state = {
            choresList: [],
            people: [[
                "Oleńka",
                "Misiek",
                "Misiek",
                "Misiek"], ]
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/chores")
            .then(response => {
                console.log("chorelist")
                console.log(response)
               this.setState({
                   choresList: response.data["_embedded"].chores.map(e => e.name)
               })
            })
            .catch(error => {
                console.log("ERROR BIATCH")
              console.log(error)
        });

        axios.get("http://localhost:8080/api/choreTasks/byweek")
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
    }

    render() {

        let choreRows = this.state.choresList.map(chore => <ChoreRow name={ chore }/>);

        let weeks: JSX.Element[] =
            [].concat.apply([],
                this.state.people.map(peopleRow  => peopleRow.map((people, i) => <th>Week {i+1}</th>)));

        return (
            <Table striped bordered condensed hover>
                <thead>
                <tr>
                    <th>Chore Name</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                </tr>
                </thead>
                <tbody>
                { choreRows }
                </tbody>
            </Table>
    );
    }
}
