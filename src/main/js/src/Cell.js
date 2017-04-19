import React, {Component} from 'react';
import * as axios from 'axios';

export default class Cell extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
    }

    render() {
        return (
            <td>{this.props.name}</td>
        );
    }
}
