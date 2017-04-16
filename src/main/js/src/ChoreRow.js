import React, {Component} from 'react';
import * as axios from 'axios';

export default class ChoreRow extends Component {

    constructor(props) {
        super(props);
        this.state = {
            people: []
        }
    }

    update() {
        axios.get("http://localhost:8080/api/choreTasks/?name=" + this.props.name)
            .then(response => {
                let data = response.data;
                this.setState({
                    people: data.map(choreTask => {
                        return choreTask.person
                    })
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

    render() {
        let peopleElems = this.state.people.map(person => {
            return <td>{person.name}</td>
        });
        return (
            <tr>
                <td>{ this.props.name }</td>
                { peopleElems }
            </tr>
        );
    }
}
