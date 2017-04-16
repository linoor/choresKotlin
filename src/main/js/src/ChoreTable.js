import React, {Component} from 'react';
import ChoreRow from "./ChoreRow";
import * as axios from 'axios';
import {Table} from "react-bootstrap"

export default class ChoreTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            chores: [],
            weeks: [],
            people: [],
            choreTasks: [],
            rows: {}
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/chores")
            .then(response => {
                let chores = response.data._embedded.chores;

                this.setState({
                    chores: chores
                })
            });

        axios.get("http://localhost:8080/api/choreTasks/week")
            .then(response => {
                this.setState({
                    weeks: response.data
                })
            });

        axios.get("http://localhost:8080/api/choreTasks/bychore")
            .then(response => {
                let choreTasksRows = Object.values(response.data);
                let newRows = {}
                let rows = choreTasksRows.map( row => {
                    return row.map(c => {
                        let choreName = c.chore.name
                        let result = { person: c.person.name, done: c.dateDone };
                        if (newRows[choreName] === undefined) {
                            newRows[choreName] = [result]
                        } else {
                            newRows[choreName].push(result)
                        }

                        return { person: c.person.name, done: c.dateDone }
                    })
                });
                console.log(newRows)
                this.setState({
                    choreTasks: response.data
                })
            })
    }

    render() {
        let choreNames = this.state.chores.length === 0 ? ["No chores data"] : this.state.chores.map(chore => chore.name);

        let weekNames = ["nope"];
        if (this.state.weeks !== {}) {
            weekNames = Object.keys(this.state.weeks)
        }
        let weekElems = weekNames.map(week => <th>{ week }</th>);

        let choreRows = choreNames.map(choreName => {
            let index = choreNames.indexOf(choreName);
            let people = this.state.people[index];
            return <ChoreRow name={ choreName } people={ people } weeks={this.state.weeks} />
        });

        return (
            <Table striped bordered condensed hover>
                <thead>
                <tr>
                    <th>Chore Name</th>
                    { weekElems }
                </tr>
                </thead>
                <tbody>
                { choreRows }
                </tbody>
            </Table>
        );
    }
}
