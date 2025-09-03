import { useEffect, useState } from "react"

function List() {
    // 변수는 {변수명}
    const name = "Junha"
    const products = [
        { title: 'Cabbage', id: 1 },
        { title: 'Garlic', id: 2 },
        { title: 'Apple', id: 3 },
    ];
    const mappedProducts = products.map(p => <li key={p.id}>{p.title}</li>) // key 필수, 중괄호 빼주세요, 중괄호 널거면 return() 이렇게

    const [notice, setNotice] = useState([])
    const [page, setPage] = useState(0)

    
    function Next() {
        setPage(page + 1)       
    }

    function Prev() {
        setPage(page - 1)
    }

    useEffect(() => {
        fetch(`http://localhost:8080/notice?page=${page}`).then(response => response.json()).then(response => {
            const noticeList = response.content.map(l => <li key={l.boardNum}>{l.boardTitle}</li>)
            setNotice(noticeList)
        })
    }, [page])
    return (
        <>
            <h2>List Page</h2>
            <h2>{name}</h2>
            <ol>{mappedProducts}</ol>
            <ol>{notice}</ol>
            <div>
                <h3>Page: {page}</h3>
                <button onClick={Next}>Next</button>
                <button onClick={Prev}>Prev</button>
            </div>
        </>
    )
}

export default List