import { useNavigate } from "react-router-dom"
import { useEffect } from "react"

export default function Logout() {
    // sessionStorage.removeItem("Authorization")
    localStorage.removeItem("Authorization")
    const navigate = useNavigate();

    useEffect(()=>{
        navigate("/")
    })

}