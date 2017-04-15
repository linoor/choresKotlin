import * as React from "react";
import { Person } from "./ChoreTable"


interface IChoreRowProps {
    name: String
    people: Person[]
}

interface IChoreRowState {
}

export default class ChoreRow extends React.Component<IChoreRowProps, IChoreRowState> {

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
