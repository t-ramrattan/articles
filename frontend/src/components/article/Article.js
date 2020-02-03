import React from 'react';
import ReactHtmlParser from 'react-html-parser';

class Article extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            article: {}   
        }
    }

    componentDidMount() {
        fetch(`/api/article?id=${this.props.match.params.id}`)
        .then((response) => {
            return response.json();
        })
        .then((json) => {
            this.setState({
                article: json
            })
        });           
    }

    render() {
        return (
            <div style={{padding:20}}>
                <img src={this.state.article.imgUrl}/>
                <h1>{this.state.article.title}</h1>
                <p>by <b>{this.state.article.author}</b></p>
                <div>{ReactHtmlParser(this.state.article.content)}</div>
            </div>
        );
    }
}

export default Article;