import { useRef, useState } from "react"

function StudyRef() {
    // state - 값을 담을 수 있고, 렌더링이 새로 일어남
    const [cnt, setCnt] = useState(0)
    function increase() {
        setCnt(cnt + 1)
    }

    // ref - 값을 담을 수는 있지만, 렌더링이 일어나지는 않음
    const age = useRef(0)
    function next() {
        age.current = 3
        console.log(age.current)
    }

    return (
        <>
            <h2>Ref</h2>
            <h2>{cnt}</h2>
            <h2>{age.current}</h2>
            <button onClick={increase}>Click</button>
            <button onClick={next}>Click</button>
        </>
    )  
}

export default StudyRef