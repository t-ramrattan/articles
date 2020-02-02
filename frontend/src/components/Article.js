import React from 'react';

class Article extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            article: null   
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
            <h2>{this.props.match.params.id}</h2>
        );
    }
}

export default Article;