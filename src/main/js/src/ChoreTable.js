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
            people: []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/chores")
            .then(response => {
                let chores = response.data._embedded.chores
                this.setState({
                    choreNames: chores
                })
            })
            .catch(error => {
                console.log(error)
            });

        axios.get("http://localhost:8080/api/weeks")
            .then(response => {
                this.setState({
                    weeks: response.data._embedded.weeks
                })
            })
            .catch(error => {
                console.log(error)
            })
    }

    render() {
        let choreNames = this.state.chores.length === 0 ? ["No chores data"] : this.state.chores.map(chore => chore.name);
        console.log(`printing chore names : ${choreNames}`)
        let choreRows = choreNames.map(choreName => {
            let index = choreNames.indexOf(choreName);
            let people = this.state.people[index];
            return <ChoreRow name={ choreName } people={ people } weeks={this.state.weeks} />
        });

        let weeks = this.state.weeks.map(week => {
            let from = new Date(week.dateFrom);
            let to = new Date(week.dateTo);
            let monthNames = ["January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                ];

            let dateToStr = (date) => date.getFullYear() + "-" + (monthNames[date.getMonth() + 1]) + "-" + date.getDate();
            return `${dateToStr(from)} ------ ${dateToStr(to)}`
        });

        let weekElems = weeks.map(week => <th>{ week }</th>);

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
