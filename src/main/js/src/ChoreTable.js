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
            weekIds: []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/chores")
            .then(response => {
                let chores = response.data._embedded.chores;

                this.setState({
                    chores: chores,
                })
            });

        axios.get("http://localhost:8080/api/choreTasks/week")
            .then(response => {
                this.setState({
                    weeks: response.data,
                    weekIds: Object.values(response.data).map(chores => chores[0].week.id)
                })
            });
    }

    render() {
        let choreNames = this.state.chores.map(chore => chore.name);

        let weekNames = ["nope"];
        if (this.state.weeks !== {}) {
            weekNames = Object.keys(this.state.weeks)
        }
        let weekElems = weekNames.map(week => <th>{ week }</th>);

        let choreRows = choreNames.map(choreName => {
            return <ChoreRow name={ choreName } weekIds={this.state.weekIds}/>
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
