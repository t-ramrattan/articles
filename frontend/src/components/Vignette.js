import React from 'react';

class Vignette extends React.Component {
    
    render() {
        return(
            <div style={{width:200,height:100,margin:'auto'}}>
                <p>{this.props.title}</p>
                <p>{this.props.author}</p>
                <p>{this.props.caption}</p>
            </div>
        );
    }
}

export default Vignette;