import React, { Component } from 'react';

export default class ChoreRow extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <tr>
                <td>{ this.props.name }</td>
            </tr>
    );
    }
}
