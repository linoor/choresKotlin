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
                let choreTasks = response.data

                this.setState({
                    choreTasks: choreTasks
                })
            });
    }

    render() {
        let choreNames = this.state.chores.length === 0 ? ["No chores data"] : this.state.chores.map(chore => chore.name);
        let choreRows = choreNames.map(choreName => {
            let index = choreNames.indexOf(choreName);
            let people = this.state.people[index];
            return <ChoreRow name={ choreName } people={ people } weeks={this.state.weeks} />
        });

        let weekNames = ["nope"];
        if (this.state.choreTasks.length !== {}) {
            weekNames = Object.keys(this.state.choreTasks)
        }

        let weekElems = weekNames.map(week => <th>{ week }</th>);

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
