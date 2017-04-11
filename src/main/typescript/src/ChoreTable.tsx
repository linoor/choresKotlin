import * as React from "react";
import ChoreRow from "./ChoreRow";
import * as axios from 'axios';


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

    componentDidMount() {
        axios.get("http://localhost:8080/api/choreTasks/byweek")
            .then(response => {
                console.log(response)
            })
            .catch(error => {
                console.log(error)
            })
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
