import React, {Component} from 'react';
import ReactTable from "react-table-6";
import "react-table-6/react-table.css"
import AddCurrency from './AddCurrency';

class Currencies extends Component {
    constructor(props) {
        super(props);
        this.state = {currencies: []};
    }

    componentDidMount() {
        this.fetchCurrencies();
    }

    onDelClick = (id) => {
        const jwtToken = sessionStorage.getItem("jwt");
        if (window.confirm('Are you sure to delete currency?')) {
            fetch('http://localhost:8080/currencies/' + id, {
                method: 'DELETE',
                headers: new Headers({
                    "Authorization": jwtToken,
                    "Content-Type": "application/json"
                })
            }).then(res => this.fetchCurrencies())
                .catch(err => console.error(err));
        }
    };

    addCurrency(currency) {
        const jwtToken = sessionStorage.getItem("jwt");
        fetch('http://localhost:8080/currencies/', {
            method: 'POST',
            headers: {
                "Authorization": jwtToken,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(currency)
        })
            .then(res => this.fetchCurrencies())
            .catch(err => console.log(err))
    }

    updateCurrency(currency) {
        const jwtToken = sessionStorage.getItem("jwt");
        fetch('http://localhost:8080/currencies', {
            method: 'PUT',
            headers: {
                "Authorization": jwtToken,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(currency)
        })
            .then(res => this.fetchCurrencies())
            .catch(err => console.log(err))
    }

    editable = (cell) => {
        return (
            <div style={{backgroundColor: "#fafafa"}} contentEditable suppressContentEditableWarning onBlur={e => {
                const curr = [...this.state.currencies];
                curr[cell.index][cell.column.id] = e.target.innerHTML;
                this.setState({currencies: curr});
            }}
                 dangerouslySetInnerHTML={{__html: this.state.currencies[cell.index][cell.column.id]}}
            />
        );
    };


    render() {

        const columns = [{
            Header: 'Currency',
            accessor: 'name',
            Cell: this.editable
        }, {
            Header: 'Code',
            accessor: 'code',
            Cell: this.editable
        }, {
            Header: 'Amount',
            accessor: 'amount'
        }, {
            Header: 'Rate',
            accessor: 'rate'
        }, {
            sortable: false,
            filterable: false,
            width: 100,
            Cell: row => (
                <div>
                    <button onClick={() => this.onDelClick(row.original.id)}>Delete</button>
                </div>
            )
        }, {
            sortable: false,
            filterable: false,
            width: 100,
            Cell: row => (
                <div>
                    <button onClick={() => this.updateCurrency(row.original)}>Save</button>
                </div>
            )
        }

            ,];

        return (
            <div>
                <AddCurrency addCurrency={this.addCurrency} fetchCurrencies={this.fetchCurrencies}/>
                <ReactTable data={this.state.currencies} columns={columns} filterable={true}/>
            </div>
        );
    }

    fetchCurrencies = () => {
        const jwtToken = sessionStorage.getItem("jwt");
        fetch('http://localhost:8080/currencies',
            {headers: {"Authorization": jwtToken, "Content-Type": "application/json"}}
        )
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    currencies: responseData
                })
            })
            .catch(err => console.error("error: " + err));
    }
}

export default Currencies;