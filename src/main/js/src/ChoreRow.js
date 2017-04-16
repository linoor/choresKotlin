import React, {Component} from 'react';
import * as axios from 'axios';

export default class ChoreRow extends Component {

    constructor(props) {
        super(props);
        this.state = {
            chores: []
        }
    }

    update() {
        axios.get("http://localhost:8080/api/choreTasks/?name=" + this.props.name)
            .then(response => {
                let data = response.data;
                this.setState({
                    chores: response.data
                })
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
        let peopleElems = [];
        let people = this.state.chores.map(choreTask => choreTask.person);
        for (let i = 0; i < this.props.weeksNum; i++) {
            let currentPerson = people[i];
            if (currentPerson !== undefined) {
                peopleElems.push(
                    <td key={currentPerson.id}>{currentPerson.name}</td>)
            } else {
                peopleElems.push(<td>None</td>)
            }
        }

        return (
            <tr>
                <td>{ this.props.name }</td>
                { peopleElems }
            </tr>
        );
    }
}
