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
            choreTasks: []
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

        let rows = [];
        let chores = Object.values(this.state.choreTasks);
        for (let i = 0; i < chores.length; i++) {
            let newRow = [];
            let choreTask = chores[i];
            for (let j = 0; j < choreTask.length; j++) {
                newRow.push(choreTask[j].person)
                console.log("newRow=")
                console.log(newRow)
            }
            rows.push(newRow)
        }
        console.log(rows);

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
