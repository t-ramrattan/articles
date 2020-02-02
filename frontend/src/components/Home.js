import React from 'react';
import Vignette from './Vignette';

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
        this.numberOfPages = this.numberOfPages.bind(this);
    }

    componentDidMount() {
        this.nextPage();
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

    numberOfPages() {

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
                <div style={{width:'200px',margin:'auto'}}>
                    {this.state.currentPage > 0 ? (<button style={{width:100}} onClick={this.prevPage}>prev</button>) : null }
                    {this.state.currentPage < this.state.totalPages - 1 ? (<button style={{width:100}} onClick={this.nextPage}>next</button>) : null }
                </div>
            </div>
        )
    }

}

export default Home;