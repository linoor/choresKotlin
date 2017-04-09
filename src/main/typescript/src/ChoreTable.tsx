import * as React from "react";
import ChoreRow from "./ChoreRow";


interface IChoreTableProps {
}

interface IChoreTableState {
    choresList: String[],
    people: String[][]
}

export default class ChoreTable extends React.Component<IChoreTableProps, IChoreTableState> {

    constructor(props) {
        super(props);
        this.state = {
            choresList: [
            "clean the kitchen",
            "clean the floor",
            "clean the toilet",
            "clean the shower",
            "clean the sink in the bathroom",
            "prepare some food"
            ],
            people: [[
                "Oleńka",
                "Misiek",
                "Misiek",
                "Misiek"], ]
        };
    }

    render() {

        let choreRows = this.state.choresList.map(chore => <ChoreRow name={ chore }/>);

        let weeks: JSX.Element[] =
            [].concat.apply([],
                this.state.people.map(peopleRow  => peopleRow.map((people, i) => <th>Week {i+1}</th>)));

        return (
            <table>
                <tr>
                    <th>Chore name</th>
                    { weeks }
                </tr>
                { choreRows }
            </table>
    );
    }
}
