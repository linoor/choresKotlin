import * as React from "react";

interface IChoreTableProps {
}

interface IChoreTableState {
    choresList: String[],
    people: String[]
}

export default class ChoreTable extends React.Component<IChoreTableProps, IChoreTableState> {

    constructor(props) {
        super(props);
        this.state = {
            choresList: ["cleaning the room", "cleaning the bathroom"],
            people: ["Misiek", "Oleńka"]
        };
    }

    render() {
        return (
            <h1>"asdas"</h1>
    );
    }
}
