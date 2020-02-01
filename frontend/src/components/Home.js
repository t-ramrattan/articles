import React from 'react';
import Vignette from './Vignette';

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            currArticles: [],
            pageNumber: -1,
            pageSize: 2,
            pages: 2
        }
        this.nextPage = this.nextPage.bind(this);
        this.prevPage = this.prevPage.bind(this);
    }

    componentDidMount() {
        this.nextPage();
    }

    nextPage() {
        fetch(`/api/articles/paginated/?index=${(this.state.pageNumber + 1) * this.state.pageSize}&size=${this.state.pageSize}`)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.setState({
                pageNumber: this.state.pageNumber + 1,
                currArticles: json
            })
        });   
    }

    prevPage() {
        fetch(`/api/articles/paginated/?index=${(this.state.pageNumber * this.state.pageSize) - this.state.pageSize}&size=${this.state.pageSize}`)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.setState({
                pageNumber: this.state.pageNumber - 1,
                currArticles: json
            })
        });   
    }

    render() {
        return (
            <div>
                <div>
                {this.state.currArticles.map((a) => {
                    return <Vignette key={a.id} {...a}/>
                })}
                </div>
                {this.state.pageNumber > 0 ? (<button onClick={this.prevPage}>prev</button>) : null }
                {this.state.pageNumber < this.state.pages - 1 ? (<button onClick={this.nextPage}>next</button>) : null }
            </div>
        )
    }

}

export default Home;