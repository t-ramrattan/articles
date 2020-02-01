import React from 'react';
import Vignette from './Vignette';

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            articles: [],
            pageNumber: 0,
            pageSize: 10
        }
    }

    componentDidMount() {
        fetch(`/api/articles/paginated/?index=${this.state.pageNumber}&size=${this.state.pageSize}`)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.setState({
                articles: json
            })
        })
    }

    render() {
        return (
            <div>
                <div>
                {this.state.articles.map((a) => {
                    return <Vignette key={a.id} {...a}/>
                })}
                </div>
                {this.state.pageNumber > 0 ? (<button>prev</button>) : null }
                {this.state.pageNumber < this.state.articles.length ? (<button>next</button>) : null }
            </div>
        )
    }

}

export default Home;