import React from 'react';

class Vignette extends React.Component {
    
    render() {
        return(
            <div style={{margin:'auto', padding:'10px'}}>
                <a href={`/article/${this.props.id}`} target='_blank'>
                    <img 
                        style={{display:'block', width:'498px',height:'280px',marginLeft:'auto', marginRight:'auto'}}
                        src={this.props.imgUrl} alt={this.props.imgUrl}
                    />
                </a>
                <div style={{textAlign:'center',width:'100%'}}>
                    <a target='_blank' href={`/article/${this.props.id}`} style={{textDecoration:'none', color:'black'}}>
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