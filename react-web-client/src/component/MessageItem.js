import React from 'react';
import { Row, Col, Grid, Card } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import { MESSAGE_ROUTE } from '../utils/const.js';
import { Container } from 'react-bootstrap';

const MessageItem = ({ message }) => {
    const navigate = useNavigate()
    return (
        <Container>
            <Row className="mt-3">
                <Col onClick={() => navigate(MESSAGE_ROUTE + '/' + message.id)} >
                    <Card style={{ width: '50rem', cursor: 'pointer' }} border={"light"}>
                        <div>
                            {message.message}
                        </div>
                    </Card>
                </Col>
                <Col>
                    <Card border={"light"}>
                        {message.numSteps}
                    </Card>
                </Col>
                <Col>
                    {message.progress}
                </Col>
            </Row>
        </Container>

    );
};

export default MessageItem;




{/* <Container>
<Row className="justify-content-md-center" onClick={() => navigate(MESSAGE_ROUTE + '/' + message.id)}>
    <Col>
        {message.message}
    </Col>
    <Col>
        {message.progress}
    </Col>
    <Col>
        {message.numSteps}
    </Col>
</Row>
</Container> */}