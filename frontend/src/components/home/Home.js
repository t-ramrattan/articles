import React from 'react';
import Vignette from '../vignette/Vignette';
import './Style.css'

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            entries: [],
            currentPage: -1,
            pageSize: 10,
            totalPages: 1
        }
        this.nextPage = this.nextPage.bind(this);
        this.prevPage = this.prevPage.bind(this);
    }

    componentDidMount() {
        this.nextPage();
    }

    componentWillUpdate() {
        window.scrollTo(0,0);
    }

    nextPage() {
        fetch(`/api/paginated?page=${(this.state.currentPage + 1)}&size=${this.state.pageSize}`)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.setState({
                ...json
            })
        });   
    }

    prevPage() {
        fetch(`/api/paginated?page=${(this.state.currentPage - 1)}&size=${this.state.pageSize}`)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.setState({
                ...json
            })
        });   
    }

    render() {
        return (
            <div>
                <div>
                {this.state.entries.map((a) => {
                    return <Vignette key={a.id} {...a}/>
                })}
                </div>
                <div className='NavigationContainer'>
                    <i className={`arrow left ${this.state.currentPage > 0 ? 'PrevButton' : 'Hidden'}`} onClick={this.prevPage}></i>
                    <i className={`arrow right ${this.state.currentPage < this.state.totalPages - 1 ? 'NextButton' : 'Hidden'}`} onClick={this.nextPage}></i>
                </div>
            </div>
        )
    }

}

export default Home;