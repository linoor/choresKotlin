import React, {Component} from 'react';
import * as axios from 'axios';
import {ControlLabel, FormControl, FormGroup} from "react-bootstrap";
import * as ReactDOM from "react-dom";

export default class Cell extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isClicked: false,
        };
        this.onClick = this.onClick.bind(this);
        this.change = this.change.bind(this);
    }

    onClick() {
        if (!this.state.isClicked) {
            this.setState({
                isClicked: true
            })
        }
    }

    componentDidMount() {
        document.addEventListener('click', this.handleClickOutside.bind(this), true);
    }

    componentWillUnmount() {
            document.removeEventListener('click', this.handleClickOutside.bind(this), true);
    }

    handleClickOutside(event) {
        const domNode = ReactDOM.findDOMNode(this);

        if ((!domNode || !domNode.contains(event.target))) {
            this.setState({
                isClicked : false
            });
        }
    }

    change(e) {
        const chosenPerson = e.target.value;
        this.setState({
            isClicked: false
        });

        axios.put(`http://localhost:8080/api/` +
            `choreTasks/${this.props.choreTask.id}/person?` + `new_person_name=${chosenPerson}`)
            .then(response => {
                console.log("person changed");
                this.props.updateChoreTasks();
            })
    }

    render() {
        let name;

        if (this.props.choreTask === undefined) { return <td>No chore task</td> }

        const personName = this.props.choreTask !== undefined && this.props.choreTask.person !== null ?
            this.props.choreTask.person.name : "None";

        if (this.state.isClicked) {
            name = <FormGroup controlId="formControlsSelect">
                <FormControl componentClass="select"
                             placeholder="select"
                             onChange={this.change}
                             defaultValue={personName}>
                    { this.props.people.map(person => {
                        return <option value={person.name}>{person.name}</option>
                    })}
                </FormControl>
            </FormGroup>
        } else {
            name = <td onClick={this.onClick}
                       className={this.props.choreTask.dateDone === null ? "danger" : "success"}>
                {personName}</td>;
        }

        return name
    }
}
