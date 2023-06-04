import React from 'react'
import {Routes, Route, Navigate} from 'react-router-dom'
import { publicRoutes } from '../routes';
import { MESSAGE_ROUTE } from '../utils/const';

const AppRouter = () => {
    return (
        <Routes>
            {publicRoutes.map(({path, Component}) =>
                <Route key={path} path={path} element={<Component/>} exact/>
            )}
            <Route path='*' element={<Navigate to={MESSAGE_ROUTE}/>} />
        </Routes>
    );
};

export default AppRouter;