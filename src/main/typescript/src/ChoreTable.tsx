import * as React from "react";
import ChoreRow from "./ChoreRow";
import * as axios from 'axios';
import {Table} from "react-bootstrap"


interface IChoreTableProps {
}

export class Person {
    name: String

    constructor(name: String) {
        this.name = name
    }
}

interface IChoreTableState {
    people: { choreTask: String, people: Person[] }
    choreNames: String[]
}

export default class ChoreTable extends React.Component<IChoreTableProps, IChoreTableState> {
    constructor(props) {
        super(props);
        this.state = {
            people: [],
            choreNames: ["No chores"]
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/choreTasks/bychore")
            .then(response => {
                let chores = response.data;
                let choreTasksByChore = chores.map()
                console.log(chores);
                this.setState({
                    choreNames: Object.keys(chores).map((choreName, chore) => choreName.toString())
                })
            })
            .catch(error => {
                console.log(error)
            })
    }

    render() {
        let choreRows = this.state.choreNames.map(choreName => {
            let index = this.state.choreNames.indexOf(choreName);
            let people = this.state.people[index];
            return <ChoreRow name={ choreName } people={ people }/>
        });

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
