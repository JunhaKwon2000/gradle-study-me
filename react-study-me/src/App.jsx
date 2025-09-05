// import List from "./components/board/list"
// import StudyState from "./demo/studyState"
// import StudyEffect from "./demo/studyEffect"
// import StudyRef from "./demo/studyRef"
// import Add from "./components/board/add"
import Header from "./layout/header"
import AppRoutes from "./routes/AppRoutes"
import { BrowserRouter } from "react-router-dom"
import StudyProps from "./demo/studyProps"

function App() {

    let age = 20;
    let param = {
        id: 'junharoket',
        pw: '123qweasd'
    }

    return (
        <>  
            <BrowserRouter>
                <Header></Header>
                {/* <StudyProps age={age} name='Junha'></StudyProps> */}
                {/* <StudyProps param={param}></StudyProps> */}
                <AppRoutes></AppRoutes>
            </BrowserRouter>
        </>
    )
}

export default App
