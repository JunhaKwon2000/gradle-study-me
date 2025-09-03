import { useState } from "react"
function StudyState() {
    const [count, setCount] = useState(0)
    function increase() {
        setCount(count + 1) // 렌더링 진행(이 태그 딱 하나만)
    }
    return (
        <>
        <h2>Study State</h2>
        <h3>{count}</h3>
        <button onClick={increase}>Click</button>
        </>
    )
}

export default StudyState