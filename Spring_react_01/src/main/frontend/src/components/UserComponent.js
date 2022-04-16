import React from 'react';
import UserService from "../services/UserService";

class UserComponent extends React.Component {


    constructor(props) {
        super(props)
        this.state= {
            users:[]
        }
    }

    componentDidMount() {
        UserService.getUsers().then((response) => {
            this.setState({users: response.data})
        });
    }

    render(){
        return(
            <div>
                <h1 className= "text-center"> User List </h1>
                <table className= "table table-striped">
                    <thread>
                        <tr>
                            <td> User Id</td>
                            <td> User First Name</td>
                            <td> User Lask Name</td>
                            <td> User Email Id</td>

                        </tr>

                    </thread>
                    <tbody>
                    {
                        this.state.users.map(
                            user =>
                                <tr key = {user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.email}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
    )
                    }
}

export default UserComponent
