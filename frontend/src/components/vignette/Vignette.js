import React from 'react';
import './Style.css';

class Vignette extends React.Component {
    
    render() {
        return(
            <div className='Container'>
                <a href={`/article/${this.props.id}`} target='_blank'>
                    <img className='Image' src={this.props.imgUrl} alt={this.props.imgUrl} />
                </a>
                <div className='Text'>
                    <a target='_blank' href={`/article/${this.props.id}`}>
                        <h3>{this.props.title}</h3>
                        <span>by <b>{this.props.author}</b></span>
                        <p style={{textAlign:'left',width:'100%'}}>{this.props.caption}</p>
                    </a>
                </div>
            </div>
        );
    }
}

export default Vignette;