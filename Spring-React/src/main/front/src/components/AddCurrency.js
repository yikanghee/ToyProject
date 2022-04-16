import React, {Component} from 'react';
import SkyLight from 'react-skylight';

class AddCurrency extends Component {

    constructor(props) {
        super(props);
        this.state = {name: '', code: '', amount: '', rate: ''};
    }

    handleChange = (event) => {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    };

    handleSubmit = (event) => {
        event.preventDefault();
        var currency = {name: this.state.name, code: this.state.code, amount: this.state.amount, rate: this.state.rate};
        this.props.addCurrency(currency);
        this.refs.addDialog.hide();
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="addDialog">
                    <h3>Add Currency</h3>
                    <form>
                        <input type="text" placeholder="Name" name="name" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Code" name="code" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Amount" name="amount" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Rate" name="rate" onChange={this.handleChange}/><br/>
                        <button onClick={this.handleSubmit}>Add</button>
                    </form>
                </SkyLight>
                <div>
                    <button style={{'margin': '10px'}}
                            onClick={() => this.refs.addDialog.show()}>Add currency
                    </button>
                </div>
            </div>
        )
    }


}

export default AddCurrency;