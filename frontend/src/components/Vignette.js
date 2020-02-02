import React from 'react';

class Vignette extends React.Component {
    
    render() {
        return(
            <div style={{width:'200px',height:'425px',margin:'auto', padding:'10px'}}>
                <a href={`/article/${this.props.id}`}>
                    <img 
                        src={this.props.imgUrl} alt={this.props.imgUrl}
                        style={{marginLeft:'-150px'}}
                    />
                </a>
                <div style={{textAlign:'center',width:'200px'}}>
                    <a href={`/article/${this.props.id}`} style={{textDecoration:'none', color:'black'}}>
                        <h3>{this.props.title}</h3>
                        <span>by <b>{this.props.author}</b></span>
                        <p>{this.props.caption}</p>
                    </a>
                </div>
            </div>
        );
    }
}

export default Vignette;