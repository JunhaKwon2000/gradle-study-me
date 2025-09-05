import { useRef, useState } from "react"
import { useNavigate } from 'react-router-dom';

function Add() {

    // 방법 1 - state를 쓰는 방법
    // const [add, setAdd] = useState({
    //     boardTitle: "",
    //     boardWriter: "",
    //     boardContent: "",
    // })

    // function inputChange(e) {
    //     setAdd((prevState) => ({
    //         ...prevState,
    //         [e.target.name]:e.target.value
    //     }))
    // }

    // 방법2 - form을 쓰는 방법
    // 이 방법은 먼저 아래 input을 form으로 감싼 다음에 써주세요
    // 그리고 그 form에 submit이벤트를 아래 함수로 걸어주세요 - 태그 속성중에서 onSubmit이 있어요
    // 이런 방법은 넘겨줘야할 변수가 너무 많을 경우 FormData로 보내는 것이 더 좋을 수도 있어요
    // function send(e) {
    //     e.preventDefault();
    //     const form = new FormData(e.target);
    //     fetch(...)
    // }

    // 방법 3 - FormData 자체를 fetch body에 넣어주세요
    // function send(e) {
    //     e.preventDefault();
    //     const form = new FormData(e.target);
    //     fetch(...body: form)
    // }

    // 방법3 - URLSearchParams, Json 등 기존 방법
    // useRef를 사용해서 구현했어요
    const title = useRef("")
    const writer = useRef("")
    const content = useRef("")

    // React Router 을 사용해서 페이지를 이동할 때
    const navigate = useNavigate()

    const [files, setFiles] = useState([]);

    const fileIdx = useRef(0)
    
    // File Plus
    function addFile() {
        if (files.length >= 5) {
            window.alert('Maximum 5 files');
            return;
        }
        const idx = fileIdx.current++;
        setFiles(prev => [...prev, { idx }]);
    }
    
   function fileDelete(idx) {
        setFiles(prev => prev.filter(f => f.idx !== idx));
    }

    // function write() {
    //     // n.current.focus()
    //     // console.log(n.current.value) // n.current은 태그 전체를 가져옴
    //     const param = {
    //         boardTitle: title.current.value,
    //         boardWriter: writer.current.value,
    //         boardContent: content.current.value
    //     }
        
    //     fetch('http://localhost:8080/notice/write', {
    //         method: 'POST',
    //         headers: { 'Content-Type': 'application/json' },
    //         body: JSON.stringify(param)
    //     })
    //     .then(response => response.json())
    //     .then(response => {
    //         console.log(response)
    //         navigate('/notice/list')
    //     })
    // }

    function send(e){
        e.preventDefault();
        const formdata = new FormData(e.target) //<form></form>
        fetch("http://localhost:8080/api/notice/write", {
            method:"POST",
            body:formdata,
            headers: {
                
            }
        })
        .then(r=>r.json())
        .then(r=>{
            if(r==true){
                console.log("등록")
                navigate('/notice/list');
            }
        })
    }

    return (
        <>
            <h1>Add Page</h1>
            <form onSubmit={send}>
                <div>
                    <div>Title</div>
                    <input type="text" ref={title} name="boardTitle" />
                </div>
                <div>
                    <div>Writer</div>
                    <input type="text" ref={writer} name="boardWriter"/>
                </div>
                <div>
                    <div>Content</div>
                    <textarea ref={content} name="boardContent"></textarea>
                </div>
                <div>
                    {files.map(f => 
                        <div key={f.idx}>
                            <input type="file" name="attaches" />
                            <button type="button" onClick={() => fileDelete(f.idx)}>Delete</button>
                        </div>
                    )}
                </div>
                <div>
                    <button type="button" onClick={addFile}>File +</button>
                </div>
                <button>Add</button>
            </form>
        </>
    )
}
export default Add