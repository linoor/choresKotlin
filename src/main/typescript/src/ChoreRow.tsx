import * as React from "react";


interface IChoreRowProps {
    name: String
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
