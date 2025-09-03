import { useEffect, useState } from "react"

function StudyEffect() {
  
  const [count, setCount] = useState(0)
  
  // 렌더링 할 때 마다 실행(Mount, status 변경될 때 실행 - 오로지 콜백 함수만 넣어줄 때)
  useEffect(() => {
    console.log("Effect 1")
  })

  // 빈 배열(= 의존성 배열)을 넣어주면 마운트(첫 렌더링)일 때만 실행함
  useEffect(() => {
    console.log("Effect 2")
  }, [])

  // state 값을 넣으면 state 값이 바뀔 때 함 + Mount할 때
  useEffect(() => {
    console.log("Effect 3")
  }, [count])

  useEffect(() => {
    // 클린업 코드
    // 해당 컴포넌트가 소멸(=unmount)될 때 실행하고 싶은 것을 작성(if 문 같은 걸로 컴포넌트를 뺄 때 등등)
    // 다른 곳에 그냥 return () => {} 해도 됨
    return () => {

    }
  })

  function countPlus() {
    setCount(count + 1)
  }

  return (
    <>
    <h2>Use Effect</h2>
    <h2>Count: {count}</h2>
    <button onClick={countPlus}>Next</button>
    </>
  )
}

export default StudyEffect