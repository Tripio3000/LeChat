import { observer } from 'mobx-react-lite';
import React, { useContext } from 'react';
import { ListGroup, Row } from 'react-bootstrap';
import { Context } from '../index.js';
import { useNavigate } from "react-router-dom"
import { MESSAGE_ROUTE } from '../utils/const.js';
import MessageItem from './MessageItem.js';

const MessageList = observer(() => {
    const navigate = useNavigate()


    const { listMessages } = useContext(Context)
    console.log(listMessages)

    return (
        <Row className="d-flex">
        {listMessages.messages.map(m =>
            <MessageItem key={m.id} message={m}/>
        )}
    </Row>
    );

});

export default MessageList;


// return (
//     <ListGroup className={"mt-3"}>
//         {listMessages.messages.map(m =>
//             <ListGroup.Item
//                 style={{ cursor: 'pointer' }}
//                 key={m.id}
//                 onClick={() => navigate(MESSAGE_ROUTE + '/' + m.id)}
//             >
//                 {m.message}
//             </ListGroup.Item>
//         )}
//     </ListGroup>
// );