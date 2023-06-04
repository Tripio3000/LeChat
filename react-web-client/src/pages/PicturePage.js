import React, { useEffect, useState} from 'react';
import { Image } from 'react-bootstrap'
import {useParams} from 'react-router-dom'
import { fetchPicture } from '../http/MessageAPI';

const PicturePage = () => {
    // const picture = {id: 'bcc476f7-df6f-42da-9598-b1143bc30c80', message_id: 'a4020e34-7e9e-4d5c-8895-1a5927f9e185', content_type: 'jpg', name: 'CatFile', filePath: '/Users/alex/Diploma/FileStorage/069/178/CatFile.jpg'}
    
    const [pictures, setPictures] = useState({info: []})
    const {id} = useParams()

    useEffect(() => {
        fetchPicture(id).then(data => setPictures(data))
    }, [])
    
    return (
        <div>
            <Image src={'http://localhost:8080/picture/' + id}/>
        </div>
    );
};

export default PicturePage;