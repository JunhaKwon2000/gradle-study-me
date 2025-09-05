import { Link } from "react-router-dom"

function Header() {
    return (
        <>
            <nav>
                <div>
                    <Link to="/">Home</Link>
                </div>
                <div>
                    <Link to="/notice/list">Notice</Link>
                </div>
                {/* <div>
                    <Link to="/study/param?num=10&name=Junha">Study Param</Link>
                </div> */}
                {/* <div>
                    <Link to="/study/param/10/junha">Study Param(UseParams)</Link>
                </div> */}
                {/* <div>
                    <Link to="/study/param" state={{age: 10, user: 'junharoket'}}>Study Param(state)</Link>
                </div> */}
                <div>
                    <div>
                        <Link to="/member/login">Login</Link>
                    </div>
                    <div>
                        <Link to="/member/register">Register</Link>
                    </div>
                </div>
                <div>
                    <div>
                        <Link to="/member/logout">Logout</Link>
                    </div>
                    <div>
                        <Link to="/member/detail">Detail</Link>
                    </div>
                </div>
            </nav>
        </>
    )
}
export default Header