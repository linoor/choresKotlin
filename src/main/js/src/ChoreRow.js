import React, {Component} from 'react';
import * as axios from 'axios';
import Cell  from "./Cell";

export default class ChoreRow extends Component {

    constructor(props) {
        super(props);
        this.state = {
            chores: [],
            elems: []
        }
    }

    update() {
        axios.get("http://localhost:8080/api/choreTasks/?name=" + this.props.name)
            .then(response => {
                let chores = response.data;

                // debugger;
                let weekIds = this.props.weekIds;
                let elems = weekIds.map(weekId => {
                    let choreForThisWeek = chores.find(chore => chore.week.id === weekId);

                    if (choreForThisWeek !== undefined) {
                        debugger;
                        return <Cell name={choreForThisWeek.person.name} />
                    } else {
                        return <Cell name="None" />
                    }
                });
                // debugger;

                this.setState({
                    chores: chores,
                    elems: elems
                });
            })
    }

    componentDidMount() {
        this.update()
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.name !== this.name) {
            this.update()
        }
    }

    // updatePerson(personId, weekId, choreName) {
    //     return (e) => {
    //        e.preventDefault();
    //        axios.put(`http://localhost:8080/api/chores/?personid=${personId}&weekid=${weekId}&chorename=${choreName}`)
    //         .then(response => {
    //             console.log(response)
    //         })
    //     }
    // }

    render() {
        let peopleElems = this.state.elems;
        let people = this.state.chores.map(choreTask => choreTask.person);

        return (
            <tr>
                <td>{ this.props.name }</td>
                { peopleElems }
            </tr>
        );
    }
}
