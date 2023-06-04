import React, {createContext} from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import MessageStore from './store/MessageStore';

export const Context = createContext(null)

ReactDOM.render(
    <Context.Provider value={{
      listMessages: new MessageStore(),
    }}>
        <App />
    </Context.Provider>,
  document.getElementById('root')
);


// const root = ReactDOM.createRoot(document.getElementById('root'))
// root.render(
//     <App />
// )

// ReactDOM.render(
//     <App />,
//   document.getElementById('root')
// );

