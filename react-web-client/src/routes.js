import Generate from "./pages/Generate";
import Messages from "./pages/Messages";
import PicturePage from "./pages/PicturePage";
import { GENERATE_ROUTE, MESSAGE_ROUTE } from "./utils/const";

  export const publicRoutes =  [
    {
        path: GENERATE_ROUTE,
        Component: Generate
    },
    {
        path: MESSAGE_ROUTE,
        Component: Messages
    },
    {
        path: MESSAGE_ROUTE + '/:id',
        Component: PicturePage 
    },
  ]