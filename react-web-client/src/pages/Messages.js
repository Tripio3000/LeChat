import { observer } from 'mobx-react-lite';
import React, { useContext, useEffect } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { Context } from '../index.js';
import MessageList from '../component/MessageList';
import { fetchMessages } from '../http/MessageAPI.js';

const Messages = observer(() => {
    const { listMessages } = useContext(Context)
    console.log("first")

    useEffect(() => {
        fetchMessages().then(data => listMessages.setMessages(data))
    }, [])

    return (
        <Container>
            <Row>
                <MessageList/>
            </Row>
        </Container>
    );
});

export default Messages;