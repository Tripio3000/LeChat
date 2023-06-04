import React, { useState } from 'react';
import { Container } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/Button";
import { generate } from '../http/MessageAPI';

// import axios from "axios";

const Generate = () => {
    const [message, setMessage] = useState('')
    const [batchSize, setBatchSize] = useState('')
    const [numSteps, setNumSteps] = useState('')
    const [seed, setSeed] = useState('')

    const click = async () => {
        const response = await generate(message, batchSize, numSteps, seed)
        console.log(response)
    }

    return (
        <Container className='d-flex flex-column'>
            <Form.Control type="text" placeholder="Запрос" className='mt-5' value={message} onChange={e => setMessage(e.target.value)}/>
            <Form.Control type="number" placeholder="Количество изображений" className='mt-3' value={batchSize} onChange={e => setBatchSize(e.target.value)}/>
            <Form.Control type="number" placeholder="Количество итераций" className='mt-3' value={numSteps} onChange={e => setNumSteps(e.target.value)}/>
            <Form.Control type="number" placeholder="Seed" className='mt-3' value={seed} onChange={e => setSeed(e.target.value)}/>


            <Button variant="primary" className='mt-5' onClick={click}>Запуск</Button>{' '}
        </Container>
    );
    // const [messages, setMessages] = useState([]);
    // const [value, setValue] = useState('');

    // const sendMessage = async () => {
    //     await axios.post('http://localhost:8080/generate', {
    //         message: value
    //     })
    // }

    // return (
    //     <div className="center">
    //         <div>
    //             <div className="form">
    //                 <input value={value} onChange={e => setValue(e.target.value)} type="text"/>
    //                 <button onClick={sendMessage}>Отправить</button>
    //             </div>
    //             <div className="messages">
    //                 {messages.map(mess =>
    //                     <div className="message" key={mess.id}>
    //                         {mess.message}
    //                     </div>
    //                 )}
    //             </div>
    //         </div>
    //     </div>
    // );
};

export default Generate;