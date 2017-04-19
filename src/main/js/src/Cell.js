import React, {Component} from 'react';
import * as axios from 'axios';
import {ControlLabel, FormControl, FormGroup} from "react-bootstrap";

export default class Cell extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isClicked: false
        };
        this.onClick = this.onClick.bind(this);
    }

    onClick() {
        if (!this.state.isClicked) {
            this.setState({
                isClicked: true
            })
        }
    }

    componentDidMount() {
    }

    render() {
        let name;
        if (this.state.isClicked) {
            name = <FormGroup controlId="formControlsSelect">
                <FormControl componentClass="select" placeholder="select">
                    { this.props.people.map(person => {
                        return <option value={person.name}>{person.name}</option>
                    })}
                </FormControl>
            </FormGroup>
        } else {
            name = <td onClick={this.onClick}>{this.props.name}</td>;
        }

        return name
    }
}
