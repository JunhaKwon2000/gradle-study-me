import { BrowserRouter, Route, Routes } from "react-router-dom";
import List from "../components/board/list";
import Index from "../components";
import Add from "../components/board/add";
import StudyParam from "../demo/studyParam";
import Login from "../components/member/login";
import Logout from "../components/member/logout";

export default function AppRoutes() {
    return (
        <>
            <Routes>
                <Route path="/" element={<Index></Index>}></Route>
                <Route path="/notice/">
                    <Route path="list" element={<List></List>}></Route>
                    <Route path="add" element={<Add></Add>}></Route>
                </Route>
                <Route path="/study/param" element={<StudyParam></StudyParam>}></Route>
                <Route path="/study/param/:num/:name" element={<StudyParam></StudyParam>}></Route>
                <Route path="/member/">
                    <Route path="login" element={<Login></Login>}></Route>
                    <Route path="logout" element={<Logout></Logout>}></Route>
                </Route>
            </Routes>
        </>
    )
}